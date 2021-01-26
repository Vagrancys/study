package com.vagrancy.study.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vagrancy.study.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础view实现类
 */
public abstract class BaseView<P extends BasePresenter,IBaseContract> extends AppCompatActivity{
    private Unbinder unbinder;
    public P mPresenter;

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);

        initView(savedInstanceState);
        initToolbar();
        //弱引用
        mPresenter = getPresenter();

        if(mPresenter != null){
            //绑定
            mPresenter.bindView(this);
        }
        initData();
    }

    public abstract IBaseContract getContract();

    public abstract P getPresenter();

    public abstract void initToolbar();

    public abstract void initView(Bundle save);

    public abstract void initData();

    //如果presenter层出现了异常,需要告知view层
    public void error(Exception e){

    }

    public static class OpenActivity{
        private static OpenActivity instance;
        private static Intent mIntent;
        private static boolean IsAnim = true;
        private static Activity mActivity;

        public static OpenActivity init(Activity activity){
            mIntent = new Intent();
            mActivity = activity;
            instance = new OpenActivity();
            return instance;
        }

        public OpenActivity putActivity(Class clazz){
            mIntent.setClass(mActivity,clazz);
            return instance;
        }

        public OpenActivity isAnim(boolean isAnim){
            IsAnim = isAnim;
            return instance;
        }

        public OpenActivity putIntent(String type,long key){
            mIntent.putExtra(type,key);
            return instance;
        }

        public void launchActivity(){
            mActivity.startActivity(mIntent);
            if(IsAnim){
                mActivity.overridePendingTransition(R.anim.screen_enter_anim,R.anim.screen_no_anim);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        mPresenter.unBindView();

        if(unbinder != null){
            unbinder.unbind();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.screen_no_anim,R.anim.screen_exit_anim);
    }
}
