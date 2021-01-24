package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeExamineContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeExaminePresenter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class KnowledgeExamineModel  extends BaseModel<KnowledgeExaminePresenter, KnowledgeExamineContract.Model<Knowledge>>{
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeExamineModel(KnowledgeExaminePresenter knowledgeExaminePresenter) {
        super(knowledgeExaminePresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeExamineContract.Model<Knowledge> getContract() {
        return new KnowledgeExamineContract.Model<Knowledge>() {
            @Override
            public void queryKnowledgeAll() {
                List<Knowledge> knowledge = knowLedgeRequest.queryAll();
                mPresenter.getContract().responseResult(knowledge);
            }

            @Override
            public void deleteKnowledge(Knowledge knowledge) {
                boolean result = knowLedgeRequest.deleteKnowledge(knowledge);
                mPresenter.getContract().responseResult(result);
            }
        };
    }
}
