package com.vagrancy.study.common.contract.common;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 首页协议
 */
public interface MainContract {
    //知识抽象model
    interface Model<T>{
        void insertKnowledge(T object);
    }

    //知识抽象view
    interface View<T>{
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();

        void onSuccess(int message);
    }

    //知识抽象presenter
    interface Presenter<T>{

        void insertKnowledge(T object);
        void responseResult(boolean result);
    }
}
