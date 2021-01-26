package com.vagrancy.study.module.knowledge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/1/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识移动子项适配器
 */
public class KnowledgeMoveChildAdapter extends BaseViewAdapter<KnowledgeClass, KnowledgeMoveChildAdapter.KnowledgeMoveChildViewHolder> {
    private Map<Long,Boolean> selectChild = new HashMap<>();

    public KnowledgeMoveChildAdapter(Context context, List<KnowledgeClass> list) {
        super(context, R.layout.knowledge_insert_child_item, list);
        initChild(list);
    }

    //初始化子项选中状态
    public void initChild(List<KnowledgeClass> knowLedgeClass){
        selectChild.clear();
        if(knowLedgeClass.size() <= 0){
            return;
        }
        for (KnowledgeClass knowledgeClass : knowLedgeClass){
            selectChild.put(knowledgeClass.getKnowledge_class_id(),false);
        }
    }

    /**
     * 获取选中的子项
     * @return
     */
    public Long getSelectChild(){
        for (Map.Entry<Long,Boolean> entry : selectChild.entrySet()){
            if(entry.getValue()){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 清空选中的子项
     * @return
     */
    public void clearSelectChild(){
        for (Map.Entry<Long,Boolean> entry : selectChild.entrySet()){
            if(entry.getValue()){
                entry.setValue(false);
            }
        }
    }

    @Override
    public KnowledgeMoveChildViewHolder onCreateViewHolder(View view) {
        return new KnowledgeMoveChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(int position,KnowledgeMoveChildViewHolder holder, KnowledgeClass knowledgeClass) {
        holder.childCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            clearSelectChild();
            selectChild.put(knowledgeClass.getKnowledge_class_id(),isChecked);
        });
        holder.childCheckBox.setChecked(selectChild.get(knowledgeClass.getKnowledge_class_id()));
        holder.childTitle.setText(knowledgeClass.getKnowledge_class_name());
    }

    public static class KnowledgeMoveChildViewHolder extends IViewAdapter.CommonViewHolder{
        @BindView(R.id.child_checkbox)
        CheckBox childCheckBox;
        @BindView(R.id.child_title)
        TextView childTitle;
        public KnowledgeMoveChildViewHolder(View view){
            super(view);
        }
    }
}
