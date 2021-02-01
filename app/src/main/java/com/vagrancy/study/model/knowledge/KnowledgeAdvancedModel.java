package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedPresenter;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶model层
 */
public class KnowledgeAdvancedModel extends BaseModel<KnowledgeAdvancedPresenter, KnowledgeAdvancedContract.Model<KnowledgeAdvanced>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeAdvancedModel(KnowledgeAdvancedPresenter knowledgeAdvancedPresenter) {
        super(knowledgeAdvancedPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeAdvancedContract.Model<KnowledgeAdvanced> getContract() {
        return new KnowledgeAdvancedContract.Model<KnowledgeAdvanced>() {
            @Override
            public void queryKnowledgeAdvanced(long knowledge_id) {
                KnowledgeAdvanced knowledgeAdvanced = knowLedgeRequest.queryKnowledgeAdvanced(knowledge_id);
                mPresenter.getContract().responseResult(knowledgeAdvanced);
            }

            @Override
            public void insertKnowledgeAdvanced(long knowledge_id) {
                boolean result = knowLedgeRequest.insertKnowledgeAdvanced(knowledge_id);
                mPresenter.getContract().responseInsertResult(result);
            }

            @Override
            public void updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
                KnowledgeAdvanced newKnowledgeAdvanced = knowLedgeRequest.updateKnowledgeAdvanced(knowledgeAdvanced);
                mPresenter.getContract().responseUpdateResult(newKnowledgeAdvanced);
            }
        };
    }
}
