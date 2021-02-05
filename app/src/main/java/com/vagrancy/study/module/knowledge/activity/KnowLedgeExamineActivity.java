package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseNiceDialog;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeExamineContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.adapter.KnowLedgeExamineAdapter;
import com.vagrancy.study.presenter.knowledge.KnowledgeExaminePresenter;
import com.vagrancy.study.utils.CommonUtils;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;
import com.vagrancy.study.wedget.KnowLedgeExamineDialog;
import com.vagrancy.study.wedget.NiceDialog;
import com.vagrancy.study.wedget.NiceViewHolder;
import com.vagrancy.study.wedget.ViewConvertListener;

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
public class KnowLedgeExamineActivity extends BaseView<KnowledgeExaminePresenter, KnowledgeExamineContract.View<Knowledge>>
        implements KnowLedgeExamineDialog.OnDialogClickListener{
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
    public KnowledgeExamineContract.View<Knowledge> getContract() {
        return new KnowledgeExamineContract.View<Knowledge>(){

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
            public void onError(int message) {
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
    public KnowledgeExaminePresenter getPresenter() {
        return new KnowledgeExaminePresenter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getContract().queryKnowledgeAll();
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            mPresenter.getContract().queryKnowledgeAll();
        });
    }

    @Override
    public void initData() {
        mPresenter.getContract().queryKnowledgeAll();
    }

    @OnClick({R.id.common_back,R.id.common_operate})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_operate:
                NiceDialog.init().setLayoutId(R.layout.dialog_operate_select)
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {
                                LinearLayout dialogTidy = holder.getView(R.id.dialog_tidy);
                                dialogTidy.setOnClickListener(v->{
                                    OpenActivity.init(KnowLedgeExamineActivity.this)
                                            .putActivity(KnowledgeTidyActivity.class)
                                            .launchActivity();
                                    dialog.dismiss();
                                });
                                ImageView dialogClose = holder.getView(R.id.dialog_close);
                                dialogClose.setOnClickListener(v->{
                                    dialog.dismiss();
                                });
                            }
                        })
                        .setMargin(CommonUtils.dp2px(getBaseContext(),R.dimen.width_44dp))
                        .show(getSupportFragmentManager());
                break;
        }
    }

    //关闭流程
    @Override
    public void onClose() {
        mDialog.dismiss();
    }

    //删除流程
    @Override
    public void onDelete(int position) {
        mPosition = position;
        mPresenter.getContract().deleteKnowledge(knowledge.get(position));
    }

    //更新流程
    @Override
    public void onUpdate(int position) {
        OpenActivity.init(KnowLedgeExamineActivity.this)
                .putActivity(KnowLedgeUpdateActivity.class)
                .putIntent(ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id())
                .launchActivity();
    }

    //查看流程
    @Override
    public void onLook(int position) {
        OpenActivity.init(KnowLedgeExamineActivity.this)
                .putActivity(KnowLedgeLookActivity.class)
                .putIntent(ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id())
                .launchActivity();
    }

    //进阶流程
    @Override
    public void onAdvanced(int position) {
        OpenActivity.init(KnowLedgeExamineActivity.this)
                .putActivity(KnowledgeAdvancedEditActivity.class)
                .putIntent(ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id())
                .launchActivity();
    }

    //里程
    @Override
    public void onMileage(int position) {
        OpenActivity.init(KnowLedgeExamineActivity.this)
                .putActivity(KnowledgeMileageActivity.class)
                .putIntent(ConstantsUtils.KNOWLEDGE_ID,knowledge.get(position).get_id())
                .launchActivity();
    }
}
