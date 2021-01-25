package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseNiceDialog;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.contract.knowledge.KnowledgeTidyContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.module.knowledge.adapter.TidyExpandableAdapter;
import com.vagrancy.study.presenter.knowledge.KnowledgeTidyPresenter;
import com.vagrancy.study.utils.CommonUtils;
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
public class KnowledgeTidyActivity extends BaseView<KnowledgeTidyPresenter, KnowledgeTidyContract.View<KnowledgeClass>> {
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
    public KnowledgeTidyContract.View<KnowledgeClass> getContract() {
        return new KnowledgeTidyContract.View<KnowledgeClass>() {

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
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                mDialog.dismiss();
                mSelectDialog.dismiss();
                mPresenter.getContract().queryKnowledgeGroupAll();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void queryKnowLedgeAll(List<List<Knowledge>> knowLedge) {
                knowledge.clear();
                knowledge.addAll(knowLedge);
            }

            @Override
            public void queryKnowledgeClassAll(List<KnowledgeClass> knowledge) {
                knowledgeClasses.clear();
                knowledgeClasses.addAll(knowledge);
            }

            @Override
            public void onError(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }
        };
    }

    @Override
    public KnowledgeTidyPresenter getPresenter() {
        return new KnowledgeTidyPresenter();
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
            mPresenter.getContract().queryKnowledgeGroupAll();
        });
        mAdapter = new TidyExpandableAdapter(knowledgeClasses,knowledge);
        expandable.setAdapter(mAdapter);
        expandable.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectDialog.setPosition(position);
                mSelectDialog.show();
                return false;
            }
        });
        expandable.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            NiceDialog.init().setLayoutId(R.layout.dialog_select)
                    .setConvertListener(new ViewConvertListener() {
                @Override
                protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {
                    //查看
                    LinearLayout dialogLook = holder.getView(R.id.dialog_look);
                    dialogLook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openActivity(KnowLedgeLookActivity.class,ConstantsUtils.KNOWLEDGE_ID,knowledge.get(groupPosition).get(childPosition).get_id());
                            dialog.dismiss();
                        }
                    });
                    //移动
                    LinearLayout dialogMove = holder.getView(R.id.dialog_move);
                    dialogMove.setOnClickListener(v->{
                        openActivity(KnowledgeMoveChildActivity.class,ConstantsUtils.KNOWLEDGE_ID,knowledge.get(groupPosition).get(childPosition).get_id());
                        dialog.dismiss();
                    });
                    //删除
                    LinearLayout dialogDelete = holder.getView(R.id.dialog_delete);
                    dialogDelete.setOnClickListener(v1 -> {
                        mPresenter.getContract().deleteKnowledge(knowledge.get(groupPosition).get(childPosition));
                        dialog.dismiss();
                    });
                }
            }).setMargin(CommonUtils.dp2px(getBaseContext(),R.dimen.width_44dp))
                    .show(getSupportFragmentManager());
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
        mPresenter.getContract().queryKnowledgeGroupAll();
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
            mPresenter.getContract().deleteKnowledgeClass(knowledgeClasses.get(position));
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
                                mPresenter.getContract().updateKnowledgeClass(knowledgeClass);
                                dialog.dismiss();
                            });
                            ImageView closeImage = holder.getView(R.id.dialog_close);
                            closeImage.setOnClickListener(v->{
                                dialog.dismiss();
                            });
                        }
                    })
                    .setMargin(CommonUtils.dp2px(getBaseContext(),R.dimen.width_44dp))
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
                    .setMargin(CommonUtils.dp2px(getBaseContext(),R.dimen.width_44dp))
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
            mPresenter.getContract().insertKnowLedgeClass(knowledgeClass);
        }

        @Override
        public void onCancel() {
            mDialog.dismiss();
        }
    }
}
