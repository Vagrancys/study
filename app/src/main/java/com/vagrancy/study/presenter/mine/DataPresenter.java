package com.vagrancy.study.presenter.mine;

import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.mine.DataContract;
import com.vagrancy.study.model.mine.DataModel;
import com.vagrancy.study.model.mine.entity.DataTime;
import com.vagrancy.study.module.mine.DataActivity;

/**
 * @author Vagrancy
 * @date 2021/2/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据presenter类
 */
public class DataPresenter extends BasePresenter<DataActivity, DataModel, DataContract.Presenter<DataTime>> {
    @Override
    public DataContract.Presenter<DataTime> getContract() {
        return new DataContract.Presenter<DataTime>() {
        };
    }

    @Override
    public DataModel getModel() {
        return new DataModel(this);
    }
}
