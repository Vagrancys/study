package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeInsertChildContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.adapter.KnowledgeInsertChildAdapter;
import com.vagrancy.study.presenter.knowledge.KnowledgeInsertChildPresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识添加子项
 */
public class KnowledgeInsertChildActivity extends BaseView<KnowledgeInsertChildPresenter, KnowledgeInsertChildContract.View<Knowledge>> {
    @BindView(R.id.insert_child_recycler)
    RecyclerView recyclerView;
    private List<Knowledge> knowLedges = new ArrayList<>();
    private KnowledgeInsertChildAdapter mAdapter;
    //知识id
    private long knowledge_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_insert_child;
    }

    @Override
    public KnowledgeInsertChildContract.View<Knowledge> getContract() {
        return new KnowledgeInsertChildContract.View<Knowledge>(){
            @Override
            public void onSuccess(List<Knowledge> object) {
                knowLedges.clear();
                knowLedges.addAll(object);
                mAdapter.initChild(knowLedges);
                if(knowLedges.size() > 0){
                    mAdapter.setEmpty(false);
                }else{
                    mAdapter.setEmpty(true);
                }
            }

            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onError(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                mPresenter.getContract().queryKnowledgeChildAll();
            }

            @Override
            public void onFinish() {
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public KnowledgeInsertChildPresenter getPresenter() {
        return new KnowledgeInsertChildPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mAdapter = new KnowledgeInsertChildAdapter(getBaseContext(),knowLedges);
        mAdapter.setIsFoot(false);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        //获取子项
        mPresenter.getContract().queryKnowledgeChildAll();
    }

    @OnClick({R.id.insert_child_determine,R.id.insert_child_cancel})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.insert_child_determine:
                List<Long> knowledgeId = mAdapter.getSelectChild();
                //移动选中的子项
                mPresenter.getContract().moveKnowledgeChildAll(knowledgeId,knowledge_id);
                //移动子项
                break;
            case R.id.insert_child_cancel:
                //取消
                finish();
                //取消
                break;
        }
    }
}
