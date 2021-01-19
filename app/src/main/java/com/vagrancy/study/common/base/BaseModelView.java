package com.vagrancy.study.common.base;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础view实现类
 */
public class BaseModelView<O> implements IBaseView<O>{
    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(O object) {

    }

    @Override
    public void onSuccess(int message) {

    }

    @Override
    public void onFail(int message) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(int message) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onSuccess(List<O> object) {

    }
}
