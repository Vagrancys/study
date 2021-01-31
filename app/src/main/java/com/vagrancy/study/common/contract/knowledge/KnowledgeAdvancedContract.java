package com.vagrancy.study.common.contract.knowledge;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶协议
 */
public interface KnowledgeAdvancedContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledgeAdvanced(long knowledge_id);

        void insertKnowledgeAdvanced();
    }

    //知识抽象view
    interface View<T>{
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();

        void onSuccess(int message);

        void onSuccess(T object);

        void onError(int message);
    }

    //知识抽象presenter
    interface Presenter<T>{
        void responseResult(T object);

        void queryKnowledgeAdvanced(long knowledge_id);
    }
}
