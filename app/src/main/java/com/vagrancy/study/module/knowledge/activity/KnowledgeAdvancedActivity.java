package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedPresenter;
import com.vagrancy.study.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶功能
 */
public class KnowledgeAdvancedActivity extends BaseView<KnowledgeAdvancedPresenter, KnowledgeAdvancedContract.View<KnowledgeAdvanced>> {
    private long knowledge_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_advanced;
    }

    @Override
    public KnowledgeAdvancedContract.View<KnowledgeAdvanced> getContract() {
        return new KnowledgeAdvancedContract.View<KnowledgeAdvanced>() {
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
            public void onSuccess(KnowledgeAdvanced object) {

            }

            @Override
            public void onError(int message) {

            }
        };
    }

    @Override
    public KnowledgeAdvancedPresenter getPresenter() {
        return new KnowledgeAdvancedPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
    }

    @Override
    public void initData() {
        mPresenter.getContract().queryKnowledgeAdvanced(knowledge_id);
    }
}
