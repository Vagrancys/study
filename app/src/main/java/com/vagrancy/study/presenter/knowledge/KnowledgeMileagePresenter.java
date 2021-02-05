package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMileageContract;
import com.vagrancy.study.model.knowledge.KnowledgeMileageModel;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.module.knowledge.activity.KnowledgeMileageActivity;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/2/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程presenter
 */
public class KnowledgeMileagePresenter extends BasePresenter<KnowledgeMileageActivity, KnowledgeMileageModel, KnowledgeMileageContract.Presenter<KnowledgeMileage>> {
    @Override
    public KnowledgeMileageContract.Presenter<KnowledgeMileage> getContract() {
        return new KnowledgeMileageContract.Presenter<KnowledgeMileage>() {

            @Override
            public void responseResult(List<KnowledgeMileage> object) {
                if(object == null){
                    getView().getContract().onError(R.string.knowledge_query_error);
                }else if(object.size() <= 0){
                    getView().getContract().onFail(R.string.knowledge_query_fail);
                }else{
                    getView().getContract().onSuccess(object);
                }
                getView().getContract().onFinish();
            }

            @Override
            public void queryKnowledgeMileage(long knowledge_id) {
                getModel().getContract().queryKnowledgeMileage(knowledge_id);
            }
        };
    }

    @Override
    public KnowledgeMileageModel getModel() {
        return new KnowledgeMileageModel(this);
    }
}