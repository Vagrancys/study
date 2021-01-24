package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeInsertChildContract;
import com.vagrancy.study.common.contract.knowledge.KnowledgeLookContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeInsertChildPresenter;
import com.vagrancy.study.presenter.knowledge.KnowledgeLookPresenter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识详情model层
 */
public class KnowledgeLookModel extends BaseModel<KnowledgeLookPresenter, KnowledgeLookContract.Model<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeLookModel(KnowledgeLookPresenter knowledgeLookPresenter) {
        super(knowledgeLookPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeLookContract.Model<Knowledge> getContract() {
        return new KnowledgeLookContract.Model<Knowledge>() {
            @Override
            public void queryKnowledge(long key) {
                Knowledge knowledge = knowLedgeRequest.query(key);
                mPresenter.getContract().responseResult(knowledge);
            }
        };
    }
}