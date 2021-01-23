package com.vagrancy.study.module.knowledge.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseActivity;
import com.vagrancy.study.common.base.BaseNiceDialog;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.adapter.TidyExpandableAdapter;
import com.vagrancy.study.module.knowledge.view.KnowledgeView;
import com.vagrancy.study.presenter.knowledge.KnowledgePresenter;
import com.vagrancy.study.utils.ConstantsUtils;
import com.vagrancy.study.utils.ToastUtils;
import com.vagrancy.study.wedget.KnowLedgeSelectDialog;
import com.vagrancy.study.wedget.KnowLedgeTidyDialog;
import com.vagrancy.study.wedget.NiceDialog;
import com.vagrancy.study.wedget.NiceViewHolder;
import com.vagrancy.study.wedget.ViewConvertListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识分类活动
 */
public class KnowledgeTidyActivity extends BaseActivity<KnowledgePresenter, KnowledgeView<List<Knowledge>>>{
    private static String TAG = "knowledgeTidyActivity";
    @BindView(R.id.expandable)
    ExpandableListView expandable;
    @BindView(R.id.common_operate)
    ImageView commonOperate;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private TidyExpandableAdapter mAdapter;
    private int mPosition;
    private List<List<Knowledge>> knowledge = new ArrayList<>();
    private List<KnowledgeClass> knowledgeClasses = new ArrayList<>();
    private KnowLedgeTidyDialog mDialog;
    private KnowLedgeSelectDialog mSelectDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_tidy;
    }

    @Override
    public KnowledgePresenter getPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public KnowledgeView<List<Knowledge>> getModelView() {
        return new KnowledgeView<List<Knowledge>>(){
            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                mDialog.dismiss();
                mSelectDialog.dismiss();
                mPresenter.queryGroupAll();
            }

            @Override
            public void onSuccess() {
                Log.e("onFinish","viewType ="+7);
            }

            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {
                swipeRefreshLayout.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void queryChildKnowLedge(List<List<Knowledge>> knowLedge) {
                knowledge.clear();
                knowledge.addAll(knowLedge);
            }
            
            @Override
            public void queryKnowledgeClass(List<KnowledgeClass> knowledge) {
                knowledgeClasses.clear();
                knowledgeClasses.addAll(knowledge);
            }
        };
    }

    @Override
    public void initToolbar() {
        commonOperate.setImageResource(R.drawable.common_examine_normal);
        commonTitle.setText(getResources().getText(R.string.knowledge_tidy_title));
    }

    @OnClick({R.id.common_back,R.id.common_operate})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_operate:
                mDialog.show();
                mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
        }
    }

    @Override
    public void initView(Bundle save) {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            getPresenter().queryGroupAll();
        });
        mAdapter = new TidyExpandableAdapter(knowledgeClasses,knowledge);
        expandable.setAdapter(mAdapter);
        expandable.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            mSelectDialog.setPosition(groupPosition);
            mSelectDialog.show();
            return false;
        });
        expandable.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            ToastUtils.showToast(getBaseContext(),R.string.common_text);
            return false;
        });
        //整理添加
        mDialog = new KnowLedgeTidyDialog(this);
        mDialog.setOnDialogClickListener(new TidyDialogImpl());

        //类型操作
        mSelectDialog = new KnowLedgeSelectDialog(this);
        mSelectDialog.setOnDialogClickListener(new SelectDialogImpl());
    }

    @Override
    public void initData() {
        swipeRefreshLayout.setRefreshing(true);
        getPresenter().queryGroupAll();
    }

    //操作抽象类
    private class SelectDialogImpl implements KnowLedgeSelectDialog.OnDialogClickListener{

        @Override
        public void onClose() {
            mSelectDialog.dismiss();
        }

        @Override
        public void onDelete(int position) {
            //删除知识分类
            mPresenter.deleteKnowledgeClass(knowledgeClasses.get(position));
            if(knowledgeClasses.size() ==1){
                knowledgeClasses.clear();
            }
            mSelectDialog.dismiss();
        }

        @Override
        public void onUpdate(int position) {
            mPosition = position;
            //更新知识分类
            NiceDialog.init().setLayoutId(R.layout.dialog_knowledge_class_update)
                    .setConvertListener(new ViewConvertListener() {
                        @Override
                        protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {
                            EditText dialogEdit = holder.getView(R.id.dialog_update_edit);
                            dialogEdit.setText(knowledgeClasses.get(mPosition).getKnowledge_class_name());
                            TextView cancelText = holder.getView(R.id.dialog_cancel);
                            cancelText.setOnClickListener(v -> dialog.dismiss());

                            TextView determineText = holder.getView(R.id.dialog_determine);
                            determineText.setOnClickListener(v -> {
                                String classTitle = dialogEdit.getText().toString();
                                if(TextUtils.isEmpty(classTitle)){
                                    ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_empty);
                                    return;
                                }
                                if(classTitle.equals(knowledgeClasses.get(mPosition).getKnowledge_class_name())){
                                    ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_equals);
                                    return;
                                }
                                KnowledgeClass knowledgeClass = knowledgeClasses.get(mPosition);
                                knowledgeClass.setKnowledge_class_name(classTitle);
                                mPresenter.updateKnowledgeClass(knowledgeClass);
                                dialog.dismiss();
                            });
                            ImageView closeImage = holder.getView(R.id.dialog_close);
                            closeImage.setOnClickListener(v->{
                                dialog.dismiss();
                            });
                        }
                    })
                    .setDimAmount(0.6f)
                    .show(getSupportFragmentManager());
            mSelectDialog.dismiss();
        }

        @Override
        public void onLook(int position) {
            //查看知识分类
            NiceDialog.init().setLayoutId(R.layout.dialog_knowledge_class_details)
                    .setConvertListener(new ViewConvertListener() {
                        @Override
                        protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {
                            KnowledgeClass knowledgeClass = knowledgeClasses.get(position);
                            TextView dialogTitle = holder.getView(R.id.dialog_title);
                            dialogTitle.setText(knowledgeClass.getKnowledge_class_name());
                            TextView dialogCount = holder.getView(R.id.dialog_count);
                            dialogCount.setText(String.format(Locale.CHINA,"%d个", knowledgeClass.getKnowledge_class_count()));
                            TextView dialogQuality = holder.getView(R.id.dialog_quality);
                            dialogQuality.setText(String.format(Locale.CHINA,"%d分", knowledgeClass.getKnowledge_class_quality()));
                            ImageView dialogClose = holder.getView(R.id.dialog_close);
                            dialogClose.setOnClickListener(v->{
                                dialog.dismiss();
                            });
                        }
                    })
                    .show(getSupportFragmentManager());
            mSelectDialog.dismiss();
        }

        @Override
        public void onInsert(int position) {
            //添加知识
            openActivity(KnowledgeInsertChildActivity.class,
                    ConstantsUtils.KNOWLEDGE_ID,
                    knowledgeClasses.get(position).getKnowledge_class_id());
            mSelectDialog.dismiss();
        }
    }

    //整理抽象类
    private class TidyDialogImpl implements KnowLedgeTidyDialog.OnDialogClickListener{

        @Override
        public void onClose() {
            mDialog.dismiss();
        }

        @Override
        public void onInsert(String edit) {
            KnowledgeClass knowledgeClass = new KnowledgeClass();
            knowledgeClass.setKnowledge_class_count(0);
            knowledgeClass.setKnowledge_class_quality(0);
            knowledgeClass.setKnowledge_class_name(edit);
            getPresenter().insertKnowLedgeClass(knowledgeClass);
        }

        @Override
        public void onCancel() {
            mDialog.dismiss();
        }
    }
}
