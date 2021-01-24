package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeLookContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.presenter.knowledge.KnowledgeLookPresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.TimeUtils;
import com.vagrancy.study.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识查看
 */
public class KnowLedgeLookActivity extends BaseView<KnowledgeLookPresenter, KnowledgeLookContract.View<Knowledge>>{
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_operate)
    ImageView commonOperate;
    @BindView(R.id.look_degree)
    TextView lookDegree;
    @BindView(R.id.look_time)
    TextView lookTime;
    @BindView(R.id.look_details)
    TextView lookDetails;
    private long knowledge_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_look;
    }

    @Override
    public KnowledgeLookContract.View<Knowledge> getContract() {
        return new KnowledgeLookContract.View<Knowledge>() {
            @Override
            public void onSuccess(Knowledge object) {
                lookDetails.setText(object.getKnowledge_content());
                lookTime.setText(TimeUtils.getTimeText(object.getKnowledge_save_time()));
                lookDegree.setText(""+object.getKnowledge_state());
            }

            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    public KnowledgeLookPresenter getPresenter() {
        return new KnowledgeLookPresenter();
    }

    @Override
    public void initToolbar() {
        commonOperate.setImageResource(R.drawable.common_examine_normal);
        commonTitle.setText(getResources().getText(R.string.knowledge_look_title));
    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
    }

    @OnClick({R.id.common_back})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.common_back:
                finish();
                break;
        }
    }

    @Override
    public void initData() {
        mPresenter.getContract().queryKnowledge(knowledge_id);
    }
}
