package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMoveChildContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeMoveChildPresenter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识子类移动model
 */
public class KnowledgeMoveChildModel extends BaseModel<KnowledgeMoveChildPresenter, KnowledgeMoveChildContract.Model<KnowledgeClass>>{
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeMoveChildModel(KnowledgeMoveChildPresenter knowledgeMoveChildPresenter) {
        super(knowledgeMoveChildPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeMoveChildContract.Model<KnowledgeClass> getContract() {
        return new KnowledgeMoveChildContract.Model<KnowledgeClass>() {

            @Override
            public void queryKnowledgeClassByChildIdAll(long knowledge_class) {
                List<KnowledgeClass> knowledgeClass = knowLedgeRequest.queryClassByChildIdAll(knowledge_class);
                mPresenter.getContract().responseResult(knowledgeClass);
            }

            @Override
            public void moveKnowledgeChildByClassIdAll(long knowledge_id, Long selectChild) {
                boolean result = knowLedgeRequest.moveKnowledgeChildByClassId(knowledge_id,selectChild);
                mPresenter.getContract().responseMoveResult(result);
            }
        };
    }
}
