package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedEditPresenter;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶model
 */
public class KnowledgeAdvancedEditModel extends BaseModel<KnowledgeAdvancedEditPresenter, KnowledgeAdvancedEditContract.Model<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeAdvancedEditModel(KnowledgeAdvancedEditPresenter knowledgeAdvancedEditPresenter) {
        super(knowledgeAdvancedEditPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeAdvancedEditContract.Model<Knowledge> getContract() {
        return new KnowledgeAdvancedEditContract.Model<Knowledge>() {
            @Override
            public void queryKnowledge(long knowledge_id) {
                Knowledge knowledge = knowLedgeRequest.query(knowledge_id);
                mPresenter.getContract().responseKnowledgeResult(knowledge);
            }

            @Override
            public void queryKnowledgeAdvanced(long knowledge_id) {
                KnowledgeAdvanced knowledgeAdvanced = knowLedgeRequest.queryKnowledgeAdvancedByKnowledgeId(knowledge_id);
                mPresenter.getContract().responseKnowledgeAdvancedResult(knowledgeAdvanced);
            }

            @Override
            public void updateKnowledge(Knowledge knowledge) {
                boolean result = knowLedgeRequest.update(knowledge);
                mPresenter.getContract().responseUpdateResult(result);
            }

            @Override
            public void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
                boolean result = knowLedgeRequest.updateOneKnowledgeAdvanced(knowledgeAdvanced);
                mPresenter.getContract().responseUpdateResult(result);
            }
        };
    }
}
