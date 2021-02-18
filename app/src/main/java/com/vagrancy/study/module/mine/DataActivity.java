package com.vagrancy.study.module.mine;

import android.os.Bundle;

import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.mine.DataContract;
import com.vagrancy.study.model.mine.entity.DataTime;
import com.vagrancy.study.presenter.mine.DataPresenter;

/**
 * @author Vagrancy
 * @date 2021/2/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据页面
 */
public class DataActivity extends BaseView<DataPresenter, DataContract.View<DataTime>> {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public DataContract.View<DataTime> getContract() {
        return new DataContract.View<DataTime>() {
        };
    }

    @Override
    public DataPresenter getPresenter() {
        return new DataPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {

    }

    @Override
    public void initData() {

    }
}
