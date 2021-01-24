package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeUpdateContract;
import com.vagrancy.study.model.knowledge.KnowledgeUpdateModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeUpdateActivity;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识更新presenter层
 */
public class KnowledgeUpdatePresenter extends BasePresenter<KnowLedgeUpdateActivity, KnowledgeUpdateModel, KnowledgeUpdateContract.Presenter<Knowledge>> {
    @Override
    public KnowledgeUpdateContract.Presenter<Knowledge> getContract() {
        return new KnowledgeUpdateContract.Presenter<Knowledge>() {

            @Override
            public void queryKnowledge(long key) {
                getModel().getContract().queryKnowledge(key);
            }

            @Override
            public void updateKnowledge(Knowledge object) {
                getModel().getContract().updateKnowledge(object);
            }

            @Override
            public void responseResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess(R.string.knowledge_update_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_update_fail);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void responseResult(Knowledge object) {
                getView().getContract().onSuccess(object);
            }
        };
    }

    @Override
    public KnowledgeUpdateModel getModel() {
        return new KnowledgeUpdateModel(this);
    }
}
