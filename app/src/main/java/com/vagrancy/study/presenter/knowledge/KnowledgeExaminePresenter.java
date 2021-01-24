package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeExamineContract;
import com.vagrancy.study.model.knowledge.KnowledgeExamineModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeExamineActivity;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识查看presenter层
 */
public class KnowledgeExaminePresenter extends BasePresenter<KnowLedgeExamineActivity,
        KnowledgeExamineModel,KnowledgeExamineContract.Presenter<Knowledge>> {

    @Override
    public KnowledgeExamineContract.Presenter<Knowledge> getContract() {
        return new KnowledgeExamineContract.Presenter<Knowledge>() {
            @Override
            public void queryKnowledgeAll() {
                getModel().getContract().queryKnowledgeAll();
            }

            @Override
            public void responseResult(List<Knowledge> knowledge) {
                if(knowledge == null){
                    getView().getContract().onError(R.string.knowledge_query_error);
                }else if(knowledge.size() <=0){
                    getView().getContract().onFail(R.string.knowledge_query_empty);
                }else{
                    getView().getContract().onSuccess(knowledge);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void responseResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_delete_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_delete_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void deleteKnowledge(Knowledge knowledge) {
                getModel().getContract().deleteKnowledge(knowledge);
            }
        };
    }

    @Override
    public KnowledgeExamineModel getModel() {
        return new KnowledgeExamineModel(this);
    }
}
