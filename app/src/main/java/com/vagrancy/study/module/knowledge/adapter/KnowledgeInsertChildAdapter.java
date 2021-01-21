package com.vagrancy.study.module.knowledge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.model.knowledge.entity.Knowledge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/1/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识添加子项适配器
 */
public class KnowledgeInsertChildAdapter extends BaseViewAdapter<Knowledge, KnowledgeInsertChildAdapter.KnowledgeInsertChildViewHolder> {
    private Map<Integer,Boolean> selectChild = new HashMap<>();

    public KnowledgeInsertChildAdapter(Context context, List<Knowledge> list) {
        super(context, R.layout.knowledge_insert_child_item, list);
        initChild(list);
    }

    //初始化子项选中状态
    public void initChild(List<Knowledge> knowLedge){
        if(knowLedge.size() <= 0){
            return;
        }
        for (Knowledge knowledge : knowLedge){
            selectChild.put(knowledge.get_id().intValue(),false);
        }
    }

    /**
     * 获取选中的子项
     * @return
     */
    public List<Integer> getSelectChild(){
        List<Integer> child = new ArrayList<>();
        for (Map.Entry<Integer,Boolean> entry : selectChild.entrySet()){
            if(entry.getValue()){
                child.add(entry.getKey());
            }
        }
        return child;
    }

    @Override
    public KnowledgeInsertChildViewHolder onCreateViewHolder(View view) {
        return new KnowledgeInsertChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(int position, KnowledgeInsertChildViewHolder holder, Knowledge knowledge) {
        holder.childCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> selectChild.put(knowledge.get_id().intValue(),isChecked));
        holder.childTitle.setText(knowledge.getKnowledge_content());
    }

    public static class KnowledgeInsertChildViewHolder extends IViewAdapter.CommonViewHolder{
        @BindView(R.id.child_checkbox)
        CheckBox childCheckBox;
        @BindView(R.id.child_title)
        TextView childTitle;
        public KnowledgeInsertChildViewHolder(View view){
            super(view);
        }
    }

}
