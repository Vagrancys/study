package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;

import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMileageContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedEditPresenter;
import com.vagrancy.study.presenter.knowledge.KnowledgeMileagePresenter;

/**
 * @author Vagrancy
 * @date 2021/2/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程活动
 */
public class KnowledgeMileageActivity extends BaseView<KnowledgeMileagePresenter, KnowledgeMileageContract.View<KnowledgeMileage>> {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public KnowledgeMileageContract.View<KnowledgeMileage> getContract() {
        return new KnowledgeMileageContract.View<KnowledgeMileage>() {
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
            public void onSuccess(KnowledgeMileage object) {

            }

            @Override
            public void onError(int message) {

            }
        };
    }

    @Override
    public KnowledgeMileagePresenter getPresenter() {
        return new KnowledgeMileagePresenter();
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
