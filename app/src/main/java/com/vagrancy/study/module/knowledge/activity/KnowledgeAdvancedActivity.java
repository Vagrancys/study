package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedPresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;

import java.util.Locale;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶功能
 */
public class KnowledgeAdvancedActivity extends BaseView<KnowledgeAdvancedPresenter, KnowledgeAdvancedContract.View<KnowledgeAdvanced>> {
    @BindView(R.id.advanced_h1)
    TextView advancedH1;
    @BindView(R.id.advanced_count)
    TextView advancedCount;
    @BindView(R.id.advanced_name)
    TextView advancedName;
    @BindView(R.id.advanced_size)
    TextView advancedSize;
    @BindView(R.id.advanced_quality)
    TextView advancedQuality;
    @BindView(R.id.advanced_master)
    TextView advancedMaster;
    @BindView(R.id.advanced_seniority)
    TextView advancedSeniority;
    @BindView(R.id.advanced_cancel)
    TextView advancedCancel;
    @BindView(R.id.advanced_determine)
    TextView advancedDetermine;
    private long knowledge_id;
    private KnowledgeAdvanced knowledgeAdvanced;
    private String[] mAdvancedName = {
            "新手","普通","精英","大师","宗师"
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_advanced;
    }

    @Override
    public KnowledgeAdvancedContract.View<KnowledgeAdvanced> getContract() {
        return new KnowledgeAdvancedContract.View<KnowledgeAdvanced>() {
            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(int message) {
                mPresenter.getContract().queryKnowledgeAdvanced(knowledge_id);
            }

            @Override
            public void onSuccess(KnowledgeAdvanced object) {
                knowledgeAdvanced = object;
                initViewData(knowledgeAdvanced);
            }

            @Override
            public void onError(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onInsert() {
                mPresenter.getContract().insertKnowledgeAdvanced(knowledge_id);
            }

            @Override
            public void onUpdate() {
                mPresenter.getContract().queryKnowledgeAdvanced(knowledge_id);
            }
        };
    }

    //初始化进阶知识
    private void initViewData(KnowledgeAdvanced object) {
        //进阶知识数量要求
        advancedSize.setText(String.format(Locale.CHINA,"数量 %d/%d", object.getAdvanced_size(), object.getAdvanced_size_ask()));
        //进阶知识次数
        advancedCount.setText(String.format(Locale.CHINA,"进阶%d次", object.getAdvanced_count()));
        //进阶知识标题
        advancedH1.setText(object.getAdvanced_nickname());
        //个人精通等级
        advancedMaster.setText(String.format(Locale.CHINA,"精通 %d/%d", object.getAdvanced_master(), object.getAdvanced_master_ask()));
        //进阶知识质量
        advancedQuality.setText(String.format(Locale.CHINA,"质量 %d/%d",object.getAdvanced_quality(),object.getAdvanced_quality_ask()));
        //进阶知识资格
        advancedSeniority.setText(String.format(Locale.CHINA,"资格 %d/%d",object.getAdvanced_seniority(),object.getAdvanced_seniority_ask()));
        //进阶知识称呼
        advancedName.setText(mAdvancedName[object.getAdvanced_now()-1]);
        if(object.getAdvanced_now() >= 5){
            advancedDetermine.setText(R.string.knowledge_advanced_full_hint);
            advancedDetermine.setClickable(false);
        }
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
        advancedCancel.setOnClickListener(v->{
            finish();
        });
        advancedDetermine.setOnClickListener(v->{
            checkAdvanced();
        });
    }

    private void checkAdvanced() {
        if(knowledgeAdvanced.getAdvanced_size() < knowledgeAdvanced.getAdvanced_size_ask()){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_size_hint);
            return;
        }
        if(knowledgeAdvanced.getAdvanced_quality() < knowledgeAdvanced.getAdvanced_quality_ask()){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_quality_hint);
            return;
        }
        if(knowledgeAdvanced.getAdvanced_seniority() < knowledgeAdvanced.getAdvanced_seniority_ask()){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_seniority_hint);
            return;
        }
        if(knowledgeAdvanced.getAdvanced_master() < knowledgeAdvanced.getAdvanced_master_ask()){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_master_hint);
            return;
        }
        mPresenter.getContract().updateKnowledgeAdvanced(knowledgeAdvanced);
    }

    @Override
    public void initData() {
        mPresenter.getContract().queryKnowledgeAdvanced(knowledge_id);
    }
}
