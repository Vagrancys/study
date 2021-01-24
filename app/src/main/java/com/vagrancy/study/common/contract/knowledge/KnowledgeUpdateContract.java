package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识更新协议
 */
public interface KnowledgeUpdateContract {
    //知识抽象model
    interface Model<T>{
        void queryKnowledge(long key);
        void updateKnowledge(T object);
    }

    //知识抽象view
    interface View<T>{
        //失败携带提示语
        void onFail(int message);
        //结束
        void onFinish();

        void onSuccess(int message);

        void onSuccess(T object);
    }

    //知识抽象presenter
    interface Presenter<T>{
        void queryKnowledge(long key);
        void updateKnowledge(T object);
        void responseResult(boolean result);
        void responseResult(T object);
    }
}
