package com.vagrancy.study.common.base;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础model层
 */
public abstract class BaseModel<P extends BasePresenter,IBaseContract> {
    protected P mPresenter;
    public BaseModel(P p){
        this.mPresenter = p;
    }

    public abstract IBaseContract getContract();
}
