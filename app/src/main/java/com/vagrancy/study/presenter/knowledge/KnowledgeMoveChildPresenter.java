package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMoveChildContract;
import com.vagrancy.study.model.knowledge.KnowledgeMoveChildModel;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.activity.KnowledgeMoveChildActivity;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class KnowledgeMoveChildPresenter extends BasePresenter<KnowledgeMoveChildActivity, KnowledgeMoveChildModel, KnowledgeMoveChildContract.Presenter<KnowledgeClass>> {
    @Override
    public KnowledgeMoveChildContract.Presenter<KnowledgeClass> getContract() {
        return new KnowledgeMoveChildContract.Presenter<KnowledgeClass>() {
            @Override
            public void responseResult(List<KnowledgeClass> knowledgeClass) {
                if(knowledgeClass == null){
                    getView().getContract().onError(R.string.knowledge_query_error);
                }else if(knowledgeClass.size() <= 0){
                    getView().getContract().onFail(R.string.knowledge_query_empty);
                }else {
                    getView().getContract().onSuccess(knowledgeClass);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void queryKnowledgeClassByChildIdAll(long knowledge_class) {
                getModel().getContract().queryKnowledgeClassByChildIdAll(knowledge_class);
            }

            @Override
            public void responseMoveResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_move_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_move_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void moveKnowledgeChildByClassIdAll(long knowledge_id, Long selectChild) {

            }
        };
    }

    @Override
    public KnowledgeMoveChildModel getModel() {
        return new KnowledgeMoveChildModel(this);
    }
}
