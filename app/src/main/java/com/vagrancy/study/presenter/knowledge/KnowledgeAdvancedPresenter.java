package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedContract;
import com.vagrancy.study.model.knowledge.KnowledgeAdvancedModel;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.module.knowledge.activity.KnowledgeAdvancedActivity;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶presenter层
 */
public class KnowledgeAdvancedPresenter extends BasePresenter<KnowledgeAdvancedActivity, KnowledgeAdvancedModel, KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced>> {
    @Override
    public KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced> getContract() {
        return new KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced>() {
            @Override
            public void responseResult(KnowledgeAdvanced object) {
                if(object != null){
                    getView().getContract().onSuccess(object);
                }else{
                    //添加知识
                    getView().getContract().onInsert();
                    getView().getContract().onFail(R.string.knowledge_query_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void queryKnowledgeAdvanced(long knowledge_id) {
                getModel().getContract().queryKnowledgeAdvanced(knowledge_id);
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
            public void insertKnowledgeAdvanced(long knowledge_id) {
                getModel().getContract().insertKnowledgeAdvanced(knowledge_id);
            }

            @Override
            public void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
                getModel().getContract().updateKnowledgeAdvanced(knowledgeAdvanced);
            }

            @Override
            public void responseUpdateResult(KnowledgeAdvanced newKnowledgeAdvanced) {
                if(newKnowledgeAdvanced == null){
                    getView().getContract().onFail(R.string.knowledge_update_fail);
                }else{
                    getView().getContract().onUpdate();
                }
                getView().getContract().onFinish();
            }

        };
    }

    @Override
    public KnowledgeAdvancedModel getModel() {
        return new KnowledgeAdvancedModel(this);
    }
}
