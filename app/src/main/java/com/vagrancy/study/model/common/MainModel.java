package com.vagrancy.study.model.common;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.presenter.common.MainPresenter;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 首页model层
 */
public class MainModel extends BaseModel<MainPresenter, MainContract.Model<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    public MainModel(MainPresenter mainPresenter) {
        super(mainPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public MainContract.Model<Knowledge> getContract() {
        return new MainContract.Model<Knowledge>() {
            @Override
            public void insertKnowledge(Knowledge object) {
                boolean result = knowLedgeRequest.insertKnowledge(object);
                mPresenter.getContract().responseResult(result);
            }
        };
    }
}
