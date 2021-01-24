package com.vagrancy.study.module.common.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeExamineActivity;
import com.vagrancy.study.presenter.common.MainPresenter;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.DaoUtilsStore;
import com.vagrancy.study.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseView<MainPresenter, MainContract.View<Knowledge>> {
    @BindView(R.id.knowledge_edit)
    EditText knowledgeEdit;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.View<Knowledge> getContract() {
        return new MainContract.View<Knowledge>() {
            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                knowledgeEdit.setText(null);
            }
        };
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
    //1.分类 排序 升级
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.knowledge_save,R.id.knowledge_examine})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.knowledge_save:
                knowledgeSave();
                break;
            case R.id.knowledge_examine:
                openActivity(KnowLedgeExamineActivity.class);
                break;
        }
    }

    /**
     * 保存知识
     */
    private void knowledgeSave(){
        String save = knowledgeEdit.getText().toString();
        if(TextUtils.isEmpty(save)){
            Toast.makeText(getBaseContext(),R.string.knowledge_save_empty,Toast.LENGTH_SHORT).show();
            return;
        }
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledge_content(save);
        knowledge.setKnowledge_state(1);
        knowledge.setKnowledge_save_time(System.currentTimeMillis());
        mPresenter.getContract().insertKnowledge(knowledge);
    }
}