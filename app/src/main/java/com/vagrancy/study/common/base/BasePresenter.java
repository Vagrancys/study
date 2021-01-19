package com.vagrancy.study.common.base;

import android.util.Log;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 实现基础类
 */
public class BasePresenter<O,V extends IBaseView<O>> implements IBasePresenter<O,V>{

    @Override
    public void bindView(V view) {

    }

    @Override
    public void insert(O object) {

    }

    @Override
    public void delete(O object) {

    }

    @Override
    public void query(long key) {

    }

    @Override
    public void update(O object) {

    }
}
