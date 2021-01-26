package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识移动协议
 */
public interface KnowledgeMoveChildContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledgeClassByChildIdAll(long knowledge_id);
        void moveKnowledgeChildByClassIdAll(long knowledge_id, Long selectChild);
    }

    //知识抽象view
    interface View<T>{
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();

        void onSuccess(int message);

        void onSuccess(List<T> object);

        void onError(int message);
    }

    //知识抽象presenter
    interface Presenter<T>{
        void responseResult(List<T> object);
        void queryKnowledgeClassByChildIdAll(long knowledge_class);
        void responseMoveResult(boolean result);
        void moveKnowledgeChildByClassIdAll(long knowledge_id, Long selectChild);
    }
}
