package com.vagrancy.study.module.knowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseActivity;
import com.vagrancy.study.common.base.BaseModelView;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.common.base.IBaseView;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.adapter.KnowLedgeExamineAdapter;
import com.vagrancy.study.module.knowledge.view.KnowledgeView;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.DaoUtilsStore;
import com.vagrancy.study.utils.ToastUtils;
import com.vagrancy.study.wedget.KnowLedgeExamineDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识查看
 */
public class KnowLedgeExamineActivity extends BaseActivity<KnowledgePresenter,KnowledgeView<Knowledge>> implements KnowLedgeExamineDialog.OnDialogClickListener{
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.common_operate)
    ImageView commonOperate;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Knowledge> knowledge = new ArrayList<>();
    private KnowLedgeExamineAdapter knowLedgeExamineAdapter;
    private KnowLedgeExamineDialog mDialog;
    private int mPosition;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_examine;
    }

    @Override
    public KnowledgePresenter getPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public KnowledgeView<Knowledge> getModelView() {
        return new KnowledgeView<Knowledge>(){
            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                if(knowledge.size() >0){
                    knowledge.remove(mPosition);
                }else{
                    knowLedgeExamineAdapter.setEmpty(false);
                }
                if(mDialog.isShowing()){
                    mDialog.dismiss();
                }
            }

            @Override
            public void onFail(int message) {
                knowLedgeExamineAdapter.setEmpty(true);
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {
                knowLedgeExamineAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onSuccess(List<Knowledge> object) {
                knowledge.clear();
                knowledge.addAll(object);
                knowLedgeExamineAdapter.setEmpty(false);
            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.queryAll();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    public void initToolbar() {
        commonOperate.setImageResource(R.drawable.common_examine_normal);
        commonTitle.setText(getResources().getText(R.string.knowledge_examine_title));
    }

    @Override
    public void initView(Bundle save) {
        knowLedgeExamineAdapter = new KnowLedgeExamineAdapter(getBaseContext(),knowledge);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(knowLedgeExamineAdapter);
        mDialog = new KnowLedgeExamineDialog(this);
        mDialog.setOnDialogClickListener(this);
        knowLedgeExamineAdapter.setOnItemClickListener(position -> {
            if(knowLedgeExamineAdapter.isItem(position)){
                mDialog.setPosition(position);
                mDialog.show();
            }
        });
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        //TODO  4.等级 5.说明 6.进阶 7.分类 8.搜索
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getPresenter().queryAll();
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().queryAll();
    }

    @OnClick({R.id.common_back,R.id.common_operate})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_operate:
                Toast.makeText(getBaseContext(),R.string.function_no_open,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClose() {
        mDialog.dismiss();
    }

    @Override
    public void onDelete(int position) {
        mPosition = position;
        getPresenter().delete(knowledge.get(position));
    }

    @Override
    public void onUpdate(int position) {
        openActivity(KnowLedgeUpdateActivity.class,ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id());
    }

    @Override
    public void onLook(int position) {
        openActivity(KnowLedgeLookActivity.class, ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id());
    }

    @Override
    public void onTidy() {
        openActivity(KnowledgeTidyActivity.class);
    }

    @Override
    public void onAdvanced(int position) {

    }

    @Override
    public void onMileage(int position) {

    }
}
