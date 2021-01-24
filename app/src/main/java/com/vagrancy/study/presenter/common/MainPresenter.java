package com.vagrancy.study.presenter.common;

import android.widget.Toast;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.common.MainModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.common.activity.MainActivity;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 首页presenter
 */
public class MainPresenter extends BasePresenter<MainActivity, MainModel, MainContract.Presenter<Knowledge>> {
    @Override
    public MainContract.Presenter<Knowledge> getContract() {
        return new MainContract.Presenter<Knowledge>() {

            @Override
            public void insertKnowledge(Knowledge object) {
                getModel().getContract().insertKnowledge(object);
            }

            @Override
            public void responseResult(boolean result) {
                if(result){
                    getView().getContract().onSuccess( R.string.knowledge_save_success);
                }else{
                    getView().getContract().onFail(R.string.knowledge_save_fail);
                }
                getView().getContract().onFinish();
            }
        };
    }

    @Override
    public MainModel getModel() {
        return new MainModel(this);
    }
}
