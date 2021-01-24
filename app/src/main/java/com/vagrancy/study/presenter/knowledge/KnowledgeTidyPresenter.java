package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeLookContract;
import com.vagrancy.study.common.contract.knowledge.KnowledgeTidyContract;
import com.vagrancy.study.model.knowledge.KnowledgeLookModel;
import com.vagrancy.study.model.knowledge.KnowledgeTidyModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeLookActivity;
import com.vagrancy.study.module.knowledge.activity.KnowledgeTidyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识整理presenter层
 */
public class KnowledgeTidyPresenter extends BasePresenter<KnowledgeTidyActivity,
        KnowledgeTidyModel, KnowledgeTidyContract.Presenter<KnowledgeClass>> {

    @Override
    public KnowledgeTidyContract.Presenter<KnowledgeClass> getContract() {
        return new KnowledgeTidyContract.Presenter<KnowledgeClass>() {

            @Override
            public void queryKnowledgeGroupAll() {
                getModel().getContract().queryKnowledgeGroupAll();
            }

            /**
             * 删除知识分类
             * @param object
             */
            @Override
            public void deleteKnowledgeClass(KnowledgeClass object) {
                getModel().getContract().deleteKnowledgeClass(object);
            }

            /**
             * 更新知识分类
             * @param object
             */
            @Override
            public void updateKnowledgeClass(KnowledgeClass object) {
                getModel().getContract().updateKnowledgeClass(object);
            }

            /**
             *  添加知识分类
             * @param object
             */
            @Override
            public void insertKnowLedgeClass(KnowledgeClass object) {
                getModel().getContract().insertKnowLedgeClass(object);
            }

            @Override
            public void responseInsertResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_insert_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_insert_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void responseUpdateResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_update_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_update_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void responseDeleteResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_delete_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_delete_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void responseKnowledgeResult(List<List<Knowledge>> object) {
                getView().getContract().queryKnowLedgeAll(object);
            }

            @Override
            public void responseResult(List<KnowledgeClass> object) {
                if(object == null){
                    getView().getContract().onError(R.string.knowledge_query_error);
                }else if(object.size() <= 0){
                    getView().getContract().onFail(R.string.knowledge_query_fail);
                }else{
                    getView().getContract().queryKnowledgeClassAll(object);
                    getModel().getContract().queryKnowledgeByClassIdAll(object);
                }
                getView().getContract().onFinish();
            }
        };
    }

    @Override
    public KnowledgeTidyModel getModel() {
        return new KnowledgeTidyModel(this);
    }
}