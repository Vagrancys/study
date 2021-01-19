package com.vagrancy.study.common.base;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础视图层
 */
public interface IBaseView<T> {
    void onSuccess();
    void onSuccess(T object);
    void onSuccess(int message);
    void onFail(int message);
    void onFail();
    void onError(int message);
    void onFinish();
    void onSuccess(List<T> object);
}
