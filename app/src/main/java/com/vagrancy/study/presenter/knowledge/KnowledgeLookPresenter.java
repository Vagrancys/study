package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeInsertChildContract;
import com.vagrancy.study.common.contract.knowledge.KnowledgeLookContract;
import com.vagrancy.study.model.knowledge.KnowledgeInsertChildModel;
import com.vagrancy.study.model.knowledge.KnowledgeLookModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeLookActivity;
import com.vagrancy.study.module.knowledge.activity.KnowledgeInsertChildActivity;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识详情presenter层
 */
public class KnowledgeLookPresenter extends BasePresenter<KnowLedgeLookActivity,
        KnowledgeLookModel, KnowledgeLookContract.Presenter<Knowledge>> {

    @Override
    public KnowledgeLookContract.Presenter<Knowledge> getContract() {
        return new KnowledgeLookContract.Presenter<Knowledge>() {

            @Override
            public void queryKnowledge(long key) {
                getModel().getContract().queryKnowledge(key);
            }

            @Override
            public void responseResult(Knowledge knowledge) {
                if(knowledge == null ){
                    getView().getContract().onFail(R.string.knowledge_query_error);
                }else{
                    getView().getContract().onSuccess(knowledge);
                }
                getView().getContract().onFinish();
            }
        };
    }

    @Override
    public KnowledgeLookModel getModel() {
        return new KnowledgeLookModel(this);
    }
}