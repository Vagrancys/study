package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeContract;
import com.vagrancy.study.model.knowledge.KnowledgeModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.fragment.KnowledgeFragment;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识presenter层
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeFragment, KnowledgeModel, KnowledgeContract.Presenter<Knowledge>> {
    @Override
    public KnowledgeContract.Presenter<Knowledge> getContract() {
        return new KnowledgeContract.Presenter<Knowledge>() {
            @Override
            public void insertKnowledge(Knowledge object) {
                getModel().getContract().insertKnowledge(object);
            }

            @Override
            public void responseResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess( R.string.knowledge_save_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_save_fail);
                }
                getView().getContract().onFinish();
            }
        };
    }

    @Override
    public KnowledgeModel getModel() {
        return new KnowledgeModel(this);
    }
}
