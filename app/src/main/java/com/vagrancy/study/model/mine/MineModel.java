package com.vagrancy.study.model.mine;

import com.vagrancy.study.common.base.BaseModel;
import com.vagrancy.study.common.contract.mine.MineContract;
import com.vagrancy.study.model.mine.entity.Mine;
import com.vagrancy.study.presenter.mine.MinePresenter;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 中心model层
 */
public class MineModel extends BaseModel<MinePresenter, MineContract.Model<Mine>> {
    public MineModel(MinePresenter minePresenter) {
        super(minePresenter);
    }

    @Override
    public MineContract.Model<Mine> getContract() {
        return new MineContract.Model<Mine>() {
        };
    }
}
