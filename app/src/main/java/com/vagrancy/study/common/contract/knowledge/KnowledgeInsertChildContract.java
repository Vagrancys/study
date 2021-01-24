package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识分类添加协议接口
 */
public interface KnowledgeInsertChildContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledgeChildAll();

        void moveKnowledgeChildAll(List<Long> knowledgeId,long knowledge_id);
    }

    //知识抽象view
    interface View<T>{

        void onError(int message);
        //成功携带 提示语
        void onSuccess(int message);
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();
        //成功携带数组entity
        void onSuccess(List<T> object);
    }

    //知识抽象presenter
    interface Presenter<T>{
        //查询知识
        void queryKnowledgeChildAll();

        void moveKnowledgeChildAll(List<Long> knowledgeId,long knowledge_id);

        void responseResult(List<T> object);

        void responseMoveResult(boolean result);
    }
}
