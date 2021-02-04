package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeMileageContract;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.module.knowledge.adapter.KnowledgeMileageAdapter;
import com.vagrancy.study.presenter.knowledge.KnowledgeAdvancedEditPresenter;
import com.vagrancy.study.presenter.knowledge.KnowledgeMileagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/2/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程活动
 */
public class KnowledgeMileageActivity extends BaseView<KnowledgeMileagePresenter, KnowledgeMileageContract.View<KnowledgeMileage>> {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.common_back)
    ImageView commonBack;
    private List<KnowledgeMileage> knowledgeMileages = new ArrayList<>();
    private KnowledgeMileageAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_mileage;
    }

    @Override
    public KnowledgeMileageContract.View<KnowledgeMileage> getContract() {
        return new KnowledgeMileageContract.View<KnowledgeMileage>() {
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
            public void onSuccess(KnowledgeMileage object) {

            }

            @Override
            public void onError(int message) {

            }
        };
    }

    @Override
    public KnowledgeMileagePresenter getPresenter() {
        return new KnowledgeMileagePresenter();
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(v->{
            finish();
        });
    }

    @Override
    public void initView(Bundle save) {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

            }
        });
        mAdapter = new KnowledgeMileageAdapter(getBaseContext(),knowledgeMileages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }
}
