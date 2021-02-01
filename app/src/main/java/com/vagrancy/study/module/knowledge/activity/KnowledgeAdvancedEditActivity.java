package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;

import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedEditPresenter;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶编辑页面
 */
public class KnowledgeAdvancedEditActivity extends BaseView<KnowledgeAdvancedEditPresenter, KnowledgeAdvancedEditContract.View<Knowledge>> {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public KnowledgeAdvancedEditContract.View<Knowledge> getContract() {
        return new KnowledgeAdvancedEditContract.View<Knowledge>() {
            @Override
            public void onFail(int message) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(int message) {

            }

            @Override
            public void onSuccess(Knowledge object) {

            }

            @Override
            public void onError(int message) {

            }
        };
    }

    @Override
    public KnowledgeAdvancedEditPresenter getPresenter() {
        return new KnowledgeAdvancedEditPresenter();
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
