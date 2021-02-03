package com.vagrancy.study.model.knowledge;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMileageContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.module.knowledge.activity.KnowledgeMileageActivity;
import com.vagrancy.study.presenter.knowledge.KnowledgeMileagePresenter;

/**
 * @author Vagrancy
 * @date 2021/2/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程model
 */
public class KnowledgeMileageModel extends BaseModel<KnowledgeMileagePresenter,KnowledgeMileageContract.Model<KnowledgeMileage>>{
    private KnowLedgeRequest knowLedgeRequest;
    public KnowledgeMileageModel(KnowledgeMileagePresenter knowledgeMileagePresenter) {
        super(knowledgeMileagePresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public KnowledgeMileageContract.Model<KnowledgeMileage> getContract() {
        return new KnowledgeMileageContract.Model<KnowledgeMileage>() {

        };
    }
}
