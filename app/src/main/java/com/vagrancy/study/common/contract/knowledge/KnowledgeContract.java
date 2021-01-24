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
         //查询单个知识
         void queryOneKnowledge(long key);
         //更新知识
         void updateKnowledge(Knowledge knowledge);
         //删除
         void deleteKnowledge(T object);

         //查询所有知识
         void queryKnowledgeAll();
     }

     //知识抽象view
     interface View<T>{
         //成功
         void onSuccess();
         //成功 携带entity
         void onSuccess(T object);
         //成功携带 提示语
         void onSuccess(int message);
         //失败携带提示语
         void onFail(int message);
         //失败
         void onFail();
         //错误携带提示语
         void onError(int message);
         //结束
         void onFinish();
         //成功携带数组entity
         void onSuccess(List<T> object);
     }

     //知识抽象presenter
     interface Presenter<T>{
         //添加
         void insertKnowledge(T object);

         //更新
         void updateKnowledge(T object);

         void responseResult(T t);

         void queryOneKnowledge(long key);

         void insertResult(boolean result);

         void deleteResult(boolean result);

         void updateResult(boolean result);

         void responseResult(List<T> knowledge);

         //查询所有
         void queryKnowledgeAll();
         //删除所有
         void deleteKnowledge(T knowledge);

     }
}
