package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
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
        return null;
    }
}
