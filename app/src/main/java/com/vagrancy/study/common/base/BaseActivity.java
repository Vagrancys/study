package com.vagrancy.study.common.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vagrancy.study.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2021/1/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础活动类
 */
public abstract class BaseActivity<P extends BasePresenter,V extends BaseModelView> extends AppCompatActivity {
    private Unbinder unbinder;
    public P mPresenter;
    public V mView;
    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        mPresenter = getPresenter();
        initToolbar();
        if(mPresenter != null){
            mView = getModelView();
            mPresenter.bindView(mView);
        }
        initData();
    }

    public abstract P getPresenter();

    public abstract V getModelView();

    public abstract void initToolbar();

    public abstract void initView(Bundle save);

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    public void openActivity(Class clazz){
        openActivity(clazz,true,"",0);
    }

    public void openActivity(Class clazz,String type,long key){
        openActivity(clazz,true,type,key);
    }

    public void openActivity(Class clazz,boolean isAnim,String type,long key){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(type,key);
        startActivity(intent);
        if(isAnim){
            overridePendingTransition(R.anim.screen_enter_anim,R.anim.screen_no_anim);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.screen_no_anim,R.anim.screen_exit_anim);
    }
}
