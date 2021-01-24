package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeExamineContract;
import com.vagrancy.study.common.contract.knowledge.KnowledgeInsertChildContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeExaminePresenter;
import com.vagrancy.study.presenter.knowledge.KnowledgeInsertChildPresenter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识分类添加子集model层
 */
public class KnowledgeInsertChildModel extends BaseModel<KnowledgeInsertChildPresenter, KnowledgeInsertChildContract.Model<Knowledge>>{
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeInsertChildModel(KnowledgeInsertChildPresenter knowledgeInsertChildPresenter) {
        super(knowledgeInsertChildPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeInsertChildContract.Model<Knowledge> getContract() {
        return new KnowledgeInsertChildContract.Model<Knowledge>() {
            @Override
            public void queryKnowledgeChildAll() {
                List<Knowledge> knowledge = knowLedgeRequest.queryChildAll();
                mPresenter.getContract().responseResult(knowledge);
            }

            @Override
            public void moveKnowledgeChildAll(List<Long> knowledgeId, long knowledge_id) {
                boolean result = knowLedgeRequest.moveKnowledgeChildAll(knowledgeId, knowledge_id);
                mPresenter.getContract().responseMoveResult(result);
            }
        };
    }
}
