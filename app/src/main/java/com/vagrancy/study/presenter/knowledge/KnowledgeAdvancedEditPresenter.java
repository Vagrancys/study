package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.KnowledgeAdvancedEditModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.module.knowledge.activity.KnowledgeAdvancedEditActivity;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶编辑presenter
 */
public class KnowledgeAdvancedEditPresenter extends BasePresenter<KnowledgeAdvancedEditActivity, KnowledgeAdvancedEditModel, KnowledgeAdvancedEditContract.Presenter<Knowledge>> {
    @Override
    public KnowledgeAdvancedEditContract.Presenter<Knowledge> getContract() {
        return new KnowledgeAdvancedEditContract.Presenter<Knowledge>() {
            @Override
            public void responseKnowledgeAdvancedResult(KnowledgeAdvanced object) {
                if(object == null){
                    getView().getContract().onFail(R.string.knowledge_query_fail);
                }else{
                    getView().getContract().onSuccess(object);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void queryKnowledge(long knowledge_id) {
                getModel().getContract().queryKnowledge(knowledge_id);
            }

            @Override
            public void responseKnowledgeResult(Knowledge knowledge) {
                if(knowledge == null){
                    getView().getContract().onFail(R.string.knowledge_query_fail);
                }else{
                    getView().getContract().onSuccess(knowledge);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void queryKnowledgeAdvanced(long knowledge_id) {
                getModel().getContract().queryKnowledgeAdvanced(knowledge_id);
            }

            @Override
            public void updateKnowledge(Knowledge knowledge) {
                getModel().getContract().updateKnowledge(knowledge);
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
            public void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
                getModel().getContract().updateKnowledgeAdvanced(knowledgeAdvanced);
            }
        };
    }

    @Override
    public KnowledgeAdvancedEditModel getModel() {
        return new KnowledgeAdvancedEditModel(this);
    }
}
