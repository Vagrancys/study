package com.vagrancy.study.presenter.mine;

import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.mine.MineContract;
import com.vagrancy.study.model.mine.MineModel;
import com.vagrancy.study.model.mine.entity.Mine;
import com.vagrancy.study.module.mine.fragment.MineFragment;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 中心presenter层
 */
public class MinePresenter extends BasePresenter<MineFragment, MineModel, MineContract.Presenter<Mine>> {
    @Override
    public MineContract.Presenter<Mine> getContract() {
        return new MineContract.Presenter<Mine>() {
        };
    }

    @Override
    public MineModel getModel() {
        return new MineModel(this);
    }
}
