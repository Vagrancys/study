package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedEditPresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.TimeUtils;
import com.vagrancy.study.utils.ToastUtils;

import java.util.Locale;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶编辑页面
 */
public class KnowledgeAdvancedEditActivity extends BaseView<KnowledgeAdvancedEditPresenter, KnowledgeAdvancedEditContract.View<Knowledge>> {

    @BindView(R.id.knowledge_save)
    ImageView advancedSave;
    @BindView(R.id.advanced_dialog)
    ImageView advancedDialog;
    @BindView(R.id.knowledge_edit)
    EditText knowledgeEdit;
    @BindView(R.id.advanced_size)
    TextView advancedSize;
    @BindView(R.id.advanced_quality)
    TextView advancedQuality;
    @BindView(R.id.advanced_master)
    TextView advancedMaster;
    @BindView(R.id.advanced_seniority)
    TextView advancedSeniority;
    private Knowledge knowledge;
    private KnowledgeAdvanced knowledgeAdvanced;
    private long knowledge_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_advanced_edit;
    }

    @Override
    public KnowledgeAdvancedEditContract.View<Knowledge> getContract() {
        return new KnowledgeAdvancedEditContract.View<Knowledge>() {
            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                knowledgeEdit.setText(getResources().getString(R.string.knowledge_advanced_empty_hint));
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onSuccess(Knowledge object) {
                knowledge = object;
                knowledgeEdit.setText(knowledge.getKnowledge_content());
            }

            @Override
            public void onError(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onSuccess(KnowledgeAdvanced object) {
                knowledgeAdvanced = object;
                advancedSize.setText(String.format(Locale.CHINA,"数量 %d/%d", object.getAdvanced_size(), object.getAdvanced_size_ask()));
                //个人精通等级
                advancedMaster.setText(String.format(Locale.CHINA,"精通 %d/%d", object.getAdvanced_master(), object.getAdvanced_master_ask()));
                //进阶知识质量
                advancedQuality.setText(String.format(Locale.CHINA,"质量 %d/%d",object.getAdvanced_quality(),object.getAdvanced_quality_ask()));
                //进阶知识资格
                advancedSeniority.setText(String.format(Locale.CHINA,"资格 %d/%d",object.getAdvanced_seniority(),object.getAdvanced_seniority_ask()));
            }
        };
    }

    @Override
    public KnowledgeAdvancedEditPresenter getPresenter() {
        return new KnowledgeAdvancedEditPresenter();
    }

    @Override
    public void initToolbar() {
        advancedSave.setOnClickListener(v->{
            initAdvancedData();
        });
        advancedDialog.setOnClickListener(v->{
            OpenActivity.init(KnowledgeAdvancedEditActivity.this)
                    .putActivity(KnowledgeAdvancedActivity.class)
                    .putIntent(ConstantsUtils.KNOWLEDGE_ID,knowledge_id)
                    .launchActivity();
        });
    }

    //处理进阶知识并保存
    private void initAdvancedData() {
        String knowledgeStr = knowledgeEdit.getText().toString();
        //判断是否为空
        if(TextUtils.isEmpty(knowledgeStr)){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_empty);
            return;
        }
        //判断是否相等
        if(knowledgeStr.equals(knowledge.getKnowledge_content())){
            ToastUtils.showToast(getBaseContext(),R.string.knowledge_advanced_equals);
            return;
        }
        knowledge.setKnowledge_content(knowledgeStr);
        knowledge.setKnowledge_save_time(System.currentTimeMillis());
        mPresenter.getContract().updateKnowledge(knowledge);
        knowledgeAdvanced.setAdvanced_size(knowledgeStr.length());
        mPresenter.getContract().updateKnowledgeAdvanced(knowledgeAdvanced);
    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
    }

    @Override
    public void initData() {
        mPresenter.getContract().queryKnowledge(knowledge_id);
        mPresenter.getContract().queryKnowledgeAdvanced(knowledge_id);
    }
}
