package com.vagrancy.study.common.contract.knowledge;

import com.vagrancy.study.common.base.IBaseContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识协议接口
 */
public interface KnowledgeContract extends IBaseContract {
     //知识抽象model
     interface Model<T>{
         //添加知识
         void insertKnowledge(T object);
     }

     //知识抽象view
     interface View<T>{
         //成功携带 提示语
         void onSuccess(int message);
         //失败携带提示语
         void onFail(int message);
         //结束
         void onFinish();
     }

     //知识抽象presenter
     interface Presenter<T>{
         //添加
         void insertKnowledge(T object);

         void responseResult(boolean result);
     }
}
