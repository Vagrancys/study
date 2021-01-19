package com.vagrancy.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vagrancy.study.common.base.BaseActivity;
import com.vagrancy.study.common.base.BaseModelView;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeExamineActivity;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.DaoUtilsStore;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.knowledge_edit)
    EditText knowledgeEdit;
    @BindView(R.id.knowledge_save)
    ImageView knowledgeSave;
    @BindView(R.id.knowledge_examine)
    ImageView knowledgeExamine;
    private CommonDaoUtils<Knowledge> knowledgeDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public BaseModelView getModelView() {
        return null;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
    //1.分类 排序 升级
        knowledgeDao = DaoUtilsStore.getInstance().getKnowledgeUtils();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.knowledge_save,R.id.knowledge_examine})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.knowledge_save:
                knowledgeSave();
                break;
            case R.id.knowledge_examine:
                openActivity(KnowLedgeExamineActivity.class);
                break;
        }
    }

    /**
     * 保存知识
     */
    private void knowledgeSave(){
        String save = knowledgeEdit.getText().toString();
        if(TextUtils.isEmpty(save)){
            Toast.makeText(getBaseContext(),R.string.knowledge_save_empty,Toast.LENGTH_SHORT).show();
            return;
        }
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledge_content(save);
        knowledge.setKnowledge_state(1);
        knowledge.setKnowledge_save_time(System.currentTimeMillis());
        boolean result = knowledgeDao.insert(knowledge);
        if(result){
            Toast.makeText(getBaseContext(),R.string.knowledge_save_success,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),R.string.knowledge_save_fail,Toast.LENGTH_SHORT).show();
        }
        knowledgeEdit.setText(null);
    }
}