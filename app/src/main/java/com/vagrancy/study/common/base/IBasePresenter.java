package com.vagrancy.study.common.base;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:  基础封装Presenter
 */
public interface IBasePresenter<O,V> {
    void bindView(V view);
    void insert(O object);
    void delete(O object);
    void query(long key);
    void update(O object);
    void unBindView();
}
