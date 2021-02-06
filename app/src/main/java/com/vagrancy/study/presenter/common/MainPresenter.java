package com.vagrancy.study.presenter.common;

import android.widget.Toast;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.common.MainModel;
import com.vagrancy.study.model.common.entity.Main;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.common.activity.MainActivity;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 首页presenter
 */
public class MainPresenter extends BasePresenter<MainActivity, MainModel, MainContract.Presenter<Main>> {
    @Override
    public MainContract.Presenter<Main> getContract() {
        return new MainContract.Presenter<Main>() {
        };
    }

    @Override
    public MainModel getModel() {
        return new MainModel(this);
    }
}
