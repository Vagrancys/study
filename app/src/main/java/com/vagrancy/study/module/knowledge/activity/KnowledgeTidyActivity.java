package com.vagrancy.study.module.knowledge.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.vagrancy.study.utils.ToastUtils;
import com.vagrancy.study.wedget.KnowLedgeSelectDialog;
import com.vagrancy.study.wedget.KnowLedgeTidyDialog;
import com.vagrancy.study.wedget.NiceDialog;
import com.vagrancy.study.wedget.NiceViewHolder;
import com.vagrancy.study.wedget.ViewConvertListener;

import java.util.ArrayList;
import java.util.List;

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
    protected void onRestart() {
        super.onRestart();
        swipeRefreshLayout.setRefreshing(true);
        mPresenter.queryAll();
    }

    @Override
    public KnowledgeView<List<Knowledge>> getModelView() {
        return new KnowledgeView<List<Knowledge>>(){
            @Override
            public void onSuccess(int message) {
                ToastUtils.showToast(getBaseContext(),message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(int message) {
                ToastUtils.showToast(getBaseContext(),message);
            }

            @Override
            public void onFinish() {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void queryChildKnowLedge(List<List<Knowledge>> knowledge) {
                knowledge.clear();
                knowledge.addAll(knowledge);
                mAdapter.notifyDataSetChanged();
            }
            
            @Override
            public void queryKnowledgeClass(List<KnowledgeClass> knowledge) {
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
                Toast.makeText(getBaseContext(),R.string.function_no_open,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void initView(Bundle save) {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getPresenter().queryGroupAll();
            }
        });
        mAdapter = new TidyExpandableAdapter(knowledgeClasses,knowledge);
        expandable.setAdapter(mAdapter);
        expandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ToastUtils.showToast(getBaseContext(),R.string.common_text);
                return false;
            }
        });
        expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ToastUtils.showToast(getBaseContext(),R.string.common_text);
                return false;
            }
        });
        //整理添加
        mDialog = new KnowLedgeTidyDialog(getBaseContext());
        mDialog.setOnDialogClickListener(new TidyDialogImpl());
        //类型操作
        mSelectDialog = new KnowLedgeSelectDialog(getBaseContext());
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
        }

        @Override
        public void onUpdate(int position) {
            mPosition = position;
            //更新知识分类
            NiceDialog.init().setLayoutId(R.layout.dialog_knowledge_class_update)
                    .setConvertListener(new ViewConvertListener() {
                        @Override
                        protected void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {

                            TextView cancelText = holder.getView(R.id.dialog_cancel);
                            cancelText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            TextView determineText = holder.getView(R.id.dialog_determine);
                            determineText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EditText dialogEdit = holder.getView(R.id.dialog_update_edit);
                                    String classTitle = dialogEdit.getText().toString();
                                    if(TextUtils.isEmpty(classTitle)){
                                        ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_empty);
                                        return;
                                    }
                                    if(classTitle.equals(knowledgeClasses.get(mPosition).getKnowledge_class_name())){
                                        ToastUtils.showToast(getBaseContext(),R.string.knowledge_update_equals);
                                        return;
                                    }
                                }
                            });
                            ImageView closeImage = holder.getView(R.id.dialog_close);
                            closeImage.setOnClickListener(v->{
                                dialog.dismiss();
                            });
                        }
                    })
                    .setDimAmount(0.6f)
                    .show(getSupportFragmentManager());
        }

        @Override
        public void onLook(int position) {
            //查看知识分类
        }

        @Override
        public void onTidy() {
            //整理知识
        }

        @Override
        public void onInsert(int position) {
            //添加知识
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
