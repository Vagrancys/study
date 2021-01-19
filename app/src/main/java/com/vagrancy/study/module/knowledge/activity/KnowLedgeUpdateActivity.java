package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseActivity;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.view.KnowledgeView;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识更新
 */
public class KnowLedgeUpdateActivity extends BaseActivity<KnowledgePresenter, KnowledgeView<Knowledge>> {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_operate)
    ImageView commonOperate;
    @BindView(R.id.knowledge_edit)
    EditText knowledgeEdit;
    private long knowledge_id;
    private Knowledge knowledge;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_update;
    }

    @Override
    public KnowledgePresenter getPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public KnowledgeView<Knowledge> getModelView() {
        return new KnowledgeView<Knowledge>(){
            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onSuccess(Knowledge object) {
                knowledge = object;
                knowledgeEdit.setText(knowledge.getKnowledge_content());
            }

            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }
        };
    }

    @Override
    public void initToolbar() {
        commonOperate.setImageResource(R.drawable.common_update_normal);
        commonTitle.setText(getResources().getText(R.string.knowledge_update_title));
    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
    }

    @OnClick({R.id.common_operate,R.id.common_back})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.common_operate:
                String content = knowledgeEdit.getText().toString();
                if(TextUtils.isEmpty(content)){
                    ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_empty);
                    return;
                }
                if(content.equals(knowledge.getKnowledge_content())){
                    ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_equals);
                    return;
                }
                knowledge.setKnowledge_content(content);
                mPresenter.update(knowledge);
                break;
            case R.id.common_back:
                finish();
                break;
        }
    }

    @Override
    public void initData() {
        mPresenter.query(knowledge_id);
    }
}
