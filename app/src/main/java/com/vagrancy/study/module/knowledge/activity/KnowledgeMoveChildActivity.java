package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMoveChildContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.adapter.KnowledgeInsertChildAdapter;
import com.vagrancy.study.module.knowledge.adapter.KnowledgeMoveChildAdapter;
import com.vagrancy.study.presenter.knowledge.KnowledgeMoveChildPresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/25
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识子集移动
 */
public class KnowledgeMoveChildActivity extends BaseView<KnowledgeMoveChildPresenter, KnowledgeMoveChildContract.View<KnowledgeClass>> {
    @BindView(R.id.move_child_recycler)
    RecyclerView recyclerView;
    private List<KnowledgeClass> knowLedgeClass = new ArrayList<>();
    private KnowledgeMoveChildAdapter mAdapter;
    //知识id
    private long knowledge_id;
    private long knowledge_class;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_move_child;
    }

    @Override
    public KnowledgeMoveChildContract.View<KnowledgeClass> getContract() {
        return new KnowledgeMoveChildContract.View<KnowledgeClass>() {
            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                mPresenter.getContract().queryKnowledgeClassByChildIdAll(knowledge_class);
            }

            @Override
            public void onSuccess(List<KnowledgeClass> object) {
                Log.e("test","test"+object.get(0).getKnowledge_class_name());
                knowLedgeClass.clear();
                knowLedgeClass.addAll(object);
                mAdapter.initChild(knowLedgeClass);
                if(knowLedgeClass.size() > 0){
                    mAdapter.setEmpty(false);
                }else{
                    mAdapter.setEmpty(true);
                }
            }

            @Override
            public void onError(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }
        };
    }

    @Override
    public KnowledgeMoveChildPresenter getPresenter() {
        return new KnowledgeMoveChildPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
        knowledge_id = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_ID,0);
        knowledge_class = getIntent().getLongExtra(ConstantsUtils.KNOWLEDGE_CLASS_ID,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mAdapter = new KnowledgeMoveChildAdapter(getBaseContext(),knowLedgeClass);
        mAdapter.setIsFoot(false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(position -> {
            mAdapter.clearSelectChild();
            mAdapter.getSelectAll().put(knowLedgeClass.get(position).getKnowledge_class_id(),true);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void initData() {
        //获取子项
        mPresenter.getContract().queryKnowledgeClassByChildIdAll(knowledge_class);
    }

    @OnClick({R.id.move_child_determine,R.id.move_child_cancel})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.move_child_determine:
                //移动选中的子项
                knowledge_class = mAdapter.getSelectChild();
                mPresenter.getContract().moveKnowledgeChildByClassId(knowledge_id,knowledge_class);
                //移动子项
                break;
            case R.id.move_child_cancel:
                //取消
                finish();
                //取消
                break;
        }
    }
}
