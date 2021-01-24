package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.Knowledge;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识查看协议接口
 */
public interface KnowledgeExamineContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledgeAll();

        void deleteKnowledge(T knowledge);
    }

    //知识抽象view
    interface View<T>{
        //成功携带 提示语
        void onSuccess(int message);
        //失败携带提示语
        void onFail(int message);
        //错误携带提示语
        void onError(int message);
        //结束
        void onFinish();
        //成功携带数组entity
        void onSuccess(List<T> object);
    }

    //知识抽象presenter
    interface Presenter<T>{
        //查询知识
        void queryKnowledgeAll();

        void responseResult(List<T> object);

        void responseResult(boolean result);

        void deleteKnowledge(T knowledge);
    }
}
