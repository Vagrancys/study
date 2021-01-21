package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseActivity;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.adapter.KnowledgeInsertChildAdapter;
import com.vagrancy.study.module.knowledge.view.KnowledgeView;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;

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
public class KnowledgeInsertChildActivity extends BaseActivity<KnowledgePresenter, KnowledgeView<Knowledge>> {
    @BindView(R.id.insert_child_recycler)
    RecyclerView recyclerView;
    private List<Knowledge> knowLedges = new ArrayList<>();
    private KnowledgeInsertChildAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_insert_child;
    }

    @Override
    public KnowledgePresenter getPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public KnowledgeView<Knowledge> getModelView() {
        return new KnowledgeView<Knowledge>(){
            @Override
            public void onSuccess(List<Knowledge> object) {

            }

            @Override
            public void onFinish() {
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mAdapter = new KnowledgeInsertChildAdapter(getBaseContext(),knowLedges);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        //获取子项
    }

    @OnClick({R.id.insert_child_determine,R.id.insert_child_cancel})
    public void onClickViewed(View view){
        switch (view.getId()){
            case R.id.insert_child_determine:
                //移动子项
                break;
            case R.id.insert_child_cancel:
                //取消
                break;
        }
    }
}
