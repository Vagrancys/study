package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识model层
 */
public class KnowledgeModel extends BaseModel<KnowledgePresenter, KnowledgeContract.Model<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeModel(KnowledgePresenter knowledgePresenter) {
        super(knowledgePresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeContract.Model<Knowledge> getContract() {
        return new KnowledgeContract.Model<Knowledge>() {
            @Override
            public void insertKnowledge(Knowledge object) {
                boolean result = knowLedgeRequest.insertKnowledge(object);
                mPresenter.getContract().responseResult(result);
            }
        };
    }
}
