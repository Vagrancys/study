package com.vagrancy.study.model.mine;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.mine.DataContract;
import com.vagrancy.study.model.mine.entity.DataTime;
import com.vagrancy.study.model.mine.request.DataRequest;
import com.vagrancy.study.presenter.mine.DataPresenter;

/**
 * @author Vagrancy
 * @date 2021/2/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据model层
 */
public class DataModel extends BaseModel<DataPresenter, DataContract.Model<DataTime>> {
    private DataRequest dataRequest;
    public DataModel(DataPresenter dataPresenter) {
        super(dataPresenter);
        dataRequest = DataRequest.getInstance();
    }

    @Override
    public DataContract.Model<DataTime> getContract() {
        return new DataContract.Model<DataTime>() {
        };
    }
}
