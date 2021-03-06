package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶编辑协议
 */
public interface KnowledgeAdvancedEditContract {
    //知识抽象model
    interface Model<T>{

        void queryKnowledge(long knowledge_id);

        void queryKnowledgeAdvanced(long knowledge_id);

        void updateKnowledge(T knowledge);

        void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced);
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

        void onSuccess(KnowledgeAdvanced knowledgeAdvanced);
    }

    //知识抽象presenter
    interface Presenter<T>{
        void responseKnowledgeAdvancedResult(KnowledgeAdvanced object);

        void queryKnowledge(long knowledge_id);

        void responseKnowledgeResult(T knowledge);

        void queryKnowledgeAdvanced(long knowledge_id);

        void updateKnowledge(T knowledge);

        void responseUpdateResult(boolean result);

        void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced);
    }
}
