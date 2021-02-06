package com.vagrancy.study.common.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vagrancy.study.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础fragmentView层
 */
public abstract class BaseFragmentView<P extends BasePresenter,IBaseContract> extends Fragment {
    private Activity context;
    private View parentView;
    private Unbinder binder;
    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @LayoutRes
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(context,getLayoutId(),null);
        binder = ButterKnife.bind(this,parentView);
        initView();
        //弱引用
        mPresenter = getPresenter();

        if(mPresenter != null){
            //绑定
            mPresenter.bindView(this);
        }
        return parentView;
    }

    protected abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData(){

    }

    public abstract IBaseContract getContract();

    public abstract P getPresenter();

    public static class OpenActivity{
        private static BaseView.OpenActivity instance;
        private static Intent mIntent;
        private static boolean IsAnim = true;
        private static Activity mActivity;

        public static BaseView.OpenActivity init(Activity activity){
            mIntent = new Intent();
            mActivity = activity;
            instance = new BaseView.OpenActivity();
            return instance;
        }

        public BaseView.OpenActivity putActivity(Class clazz){
            mIntent.setClass(mActivity,clazz);
            return instance;
        }

        public BaseView.OpenActivity isAnim(boolean isAnim){
            IsAnim = isAnim;
            return instance;
        }

        public BaseView.OpenActivity putIntent(String type, long key){
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

    @Nullable
    @Override
    public Activity getContext() {
        return context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(binder != null){
            binder.unbind();
        }
    }
}
