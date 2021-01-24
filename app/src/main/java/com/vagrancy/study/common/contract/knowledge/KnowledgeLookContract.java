package com.vagrancy.study.common.contract.knowledge;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识详情协议
 */
public interface KnowledgeLookContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledge(long key);
    }

    //知识抽象view
    interface View<T>{
        //成功携带 提示语
        void onSuccess(T object);
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();
    }

    //知识抽象presenter
    interface Presenter<T>{
        //查询知识
        void queryKnowledge(long key);

        void responseResult(T object);
    }
}
