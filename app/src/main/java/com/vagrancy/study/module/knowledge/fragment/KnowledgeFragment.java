package com.vagrancy.study.module.knowledge.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseFragmentView;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.common.activity.MainActivity;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeExamineActivity;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;
import com.vagrancy.study.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/2/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class KnowledgeFragment extends BaseFragmentView<KnowledgePresenter, KnowledgeContract.View<Knowledge>> {
    public static KnowledgeFragment newInstance(){
        return new KnowledgeFragment();
    }

    @BindView(R.id.knowledge_edit)
    EditText knowledgeEdit;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {

    }

    @Override
    public KnowledgeContract.View<Knowledge> getContract() {
        return new KnowledgeContract.View<Knowledge>() {
            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getContext(),message);
                knowledgeEdit.setText(null);
            }

            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getContext(),message);
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    public KnowledgePresenter getPresenter() {
        return new KnowledgePresenter();
    }

    @OnClick({R.id.knowledge_save,R.id.knowledge_examine})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.knowledge_save:
                knowledgeSave();
                break;
            case R.id.knowledge_examine:
                OpenActivity.init(getActivity())
                        .putActivity(KnowLedgeExamineActivity.class)
                        .launchActivity();
                break;
        }
    }

    /**
     * 保存知识
     */
    private void knowledgeSave(){
        String save = knowledgeEdit.getText().toString();
        if(TextUtils.isEmpty(save)){
            ToastUtils.showToast(getContext(),R.string.knowledge_save_empty);
            return;
        }
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledge_content(save);
        knowledge.setKnowledge_state(1);
        knowledge.setKnowledge_save_time(System.currentTimeMillis());
        mPresenter.getContract().insertKnowledge(knowledge);
    }
}
