package com.vagrancy.study.common.base;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 实现视图
 */
public class BaseViewAdapter<T,VH extends IViewAdapter.CommonViewHolder> extends IViewAdapter<T,VH>{
    public BaseViewAdapter(Context context, int layoutId, List<T> list) {
        super(context, layoutId, list);
    }

    @Override
    public VH onCreateViewHolder(View view) {
        return null;
    }

    @Override
    public CommonViewHolder onCreateHeadHolder(View view) {
        return new EmptyViewHolder(view);
    }

    @Override
    public CommonViewHolder onCreateFootHolder(View view) {
        return new EmptyViewHolder(view);
    }

    @Override
    public CommonViewHolder onCreateEmptyHolder(View view) {
        return new EmptyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(int position, VH vh, T t) {

    }

    @Override
    public void onBindHeadHolder(int position, CommonViewHolder holder) {

    }

    @Override
    public void onBindFootHolder(int position, CommonViewHolder holder) {

    }

    @Override
    public void onBindEmptyHolder(int position, CommonViewHolder holder) {

    }

    /**
     * 空布局
     */
    public static class EmptyViewHolder extends CommonViewHolder{
        public EmptyViewHolder(View view){
            super(view);
        }
    }
}
