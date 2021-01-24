package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeInsertChildContract;
import com.vagrancy.study.model.knowledge.KnowledgeInsertChildModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.activity.KnowledgeInsertChildActivity;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识分类子集添加presenter层
 */
public class KnowledgeInsertChildPresenter extends BasePresenter<KnowledgeInsertChildActivity,
        KnowledgeInsertChildModel, KnowledgeInsertChildContract.Presenter<Knowledge>> {

    @Override
    public KnowledgeInsertChildContract.Presenter<Knowledge> getContract() {
        return new KnowledgeInsertChildContract.Presenter<Knowledge>() {
            /**
             * 查询所有分组
             */
            @Override
            public void queryKnowledgeChildAll() {
                getModel().getContract().queryKnowledgeChildAll();
            }

            /**
             * 修改知识子项的父id
             * @param knowledgeId 知识子项合集
             * @param knowledge_id  止水id
             */
            @Override
            public void moveKnowledgeChildAll(List<Long> knowledgeId, long knowledge_id) {
                getModel().getContract().moveKnowledgeChildAll(knowledgeId, knowledge_id);
            }

            @Override
            public void responseResult(List<Knowledge> knowledge) {
                if(knowledge == null){
                    getView().getContract().onError(R.string.knowledge_query_error);
                }else if(knowledge.size() <= 0){
                    getView().getContract().onFail(R.string.knowledge_query_empty);
                }else {
                    getView().getContract().onSuccess(knowledge);
                }
                getView().getContract().onFinish();
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
        };
    }

    @Override
    public KnowledgeInsertChildModel getModel() {
        return new KnowledgeInsertChildModel(this);
    }
}