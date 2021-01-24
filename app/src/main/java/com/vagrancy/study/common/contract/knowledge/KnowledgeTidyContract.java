package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识整理协议
 */
public interface KnowledgeTidyContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledgeGroupAll();
        void deleteKnowledgeClass(T object);
        void updateKnowledgeClass(T object);
        void insertKnowLedgeClass(T object);
        void queryKnowledgeByClassIdAll(List<T> object);
    }

    //知识抽象view
    interface View<T>{
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();

        void onSuccess(int message);

        void onSuccess();

        void queryKnowLedgeAll(List<List<Knowledge>> knowLedge);

        void queryKnowledgeClassAll(List<KnowledgeClass> knowledge);

        void onError(int message);
    }

    //知识抽象presenter
    interface Presenter<T>{
        void queryKnowledgeGroupAll();
        void deleteKnowledgeClass(T object);
        void updateKnowledgeClass(T object);
        void insertKnowLedgeClass(T object);

        void responseInsertResult(boolean result);
        void responseUpdateResult(boolean result);
        void responseDeleteResult(boolean result);
        void responseKnowledgeResult(List<List<Knowledge>> object);
        void responseResult(List<T> object);
    }
}
