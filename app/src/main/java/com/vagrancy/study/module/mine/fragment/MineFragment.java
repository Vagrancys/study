package com.vagrancy.study.module.mine.fragment;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseFragmentView;
import com.vagrancy.study.common.contract.mine.MineContract;
import com.vagrancy.study.model.mine.MineModel;
import com.vagrancy.study.model.mine.entity.Mine;
import com.vagrancy.study.presenter.mine.MinePresenter;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 中心fragment层
 */
public class MineFragment extends BaseFragmentView<MinePresenter,MineContract.View<Mine>> {
    public static MineFragment newInstance(){
        return new MineFragment();
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    public MineContract.View<Mine> getContract() {
        return new MineContract.View<Mine>() {
        };
    }

    @Override
    public MinePresenter getPresenter() {
        return new MinePresenter();
    }
}
