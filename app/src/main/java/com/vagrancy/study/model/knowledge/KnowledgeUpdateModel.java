package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeUpdateContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeUpdatePresenter;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识更新model层
 */
public class KnowledgeUpdateModel extends BaseModel<KnowledgeUpdatePresenter,KnowledgeUpdateContract.Model<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeUpdateModel(KnowledgeUpdatePresenter knowledgeUpdatePresenter) {
        super(knowledgeUpdatePresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeUpdateContract.Model<Knowledge> getContract() {
        return new KnowledgeUpdateContract.Model<Knowledge>() {

            @Override
            public void queryKnowledge(long key) {
                Knowledge knowledge = knowLedgeRequest.query(key);
                mPresenter.getContract().responseResult(knowledge);
            }

            @Override
            public void updateKnowledge(Knowledge object) {
                boolean result = knowLedgeRequest.update(object);
                mPresenter.getContract().responseResult(result);
            }
        };
    }
}
