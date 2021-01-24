package com.vagrancy.study.common.base;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 实现基础类
 */
public abstract class BasePresenter<V,M extends BaseModel,IBaseContract>{
    protected M m;
    //绑定view层弱引用
    private WeakReference<V> vWeakReference;
    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public BasePresenter(){
        m = getModel();
    }

    public void unBindView(){
        if(vWeakReference != null){
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    //获取view P --->V
    public V getView(){
        if(vWeakReference != null){
            return vWeakReference.get();
        }
        return null;
    }

    //获取子类具体契约
    public abstract IBaseContract getContract();

    public abstract M getModel();
}
