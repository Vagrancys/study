package com.vagrancy.study.model.common;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.common.entity.Main;
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
public class MainModel extends BaseModel<MainPresenter, MainContract.Model<Main>> {
    private KnowLedgeRequest knowLedgeRequest;
    public MainModel(MainPresenter mainPresenter) {
        super(mainPresenter);
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public MainContract.Model<Main> getContract() {
        return new MainContract.Model<Main>() {
        };
    }
}
