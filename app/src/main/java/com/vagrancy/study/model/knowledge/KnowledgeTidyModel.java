package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.knowledge.KnowledgeLookContract;
import com.vagrancy.study.common.contract.knowledge.KnowledgeTidyContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.knowledge.KnowledgeLookPresenter;
import com.vagrancy.study.presenter.knowledge.KnowledgeTidyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识整理model层
 */
public class KnowledgeTidyModel extends BaseModel<KnowledgeTidyPresenter, KnowledgeTidyContract.Model<KnowledgeClass>> {
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeTidyModel(KnowledgeTidyPresenter knowledgeTidyPresenter) {
        super(knowledgeTidyPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeTidyContract.Model<KnowledgeClass> getContract() {
        return new KnowledgeTidyContract.Model<KnowledgeClass>() {
            @Override
            public void queryKnowledgeGroupAll() {
                List<KnowledgeClass> knowledgeClasses = knowLedgeRequest.queryGroupAll();
                mPresenter.getContract().responseResult(knowledgeClasses);
            }

            @Override
            public void deleteKnowledgeClass(KnowledgeClass object) {
                boolean result = knowLedgeRequest.deleteKnowledgeClass(object);
                mPresenter.getContract().responseDeleteResult(result);
            }

            @Override
            public void updateKnowledgeClass(KnowledgeClass object) {
                boolean result = knowLedgeRequest.updateKnowledgeClass(object);
                mPresenter.getContract().responseUpdateResult(result);
            }

            @Override
            public void insertKnowLedgeClass(KnowledgeClass object) {
                boolean result = knowLedgeRequest.insertKnowledgeClass(object);
                mPresenter.getContract().responseInsertResult(result);
            }

            @Override
            public void queryKnowledgeByClassIdAll(List<KnowledgeClass> object) {
                List<List<Knowledge>> knowledge = new ArrayList<>();
                for (KnowledgeClass knowledgeClass : object) {
                    List<Knowledge> knowledgeList = knowLedgeRequest.queryByClassIdAll(knowledgeClass.getKnowledge_class_id());
                    if(knowledgeList != null && knowledgeList.size() > 0){
                        knowledge.add(knowledgeList);
                    }
                }
                if(knowledge.size() > 0){
                    mPresenter.getContract().responseKnowledgeResult(knowledge);
                }

            }

            @Override
            public void deleteKnowledge(Knowledge object) {
                boolean result = knowLedgeRequest.deleteClassByKnowledge(object);
                mPresenter.getContract().responseDeleteResult(result);
            }
        };
    }
}
