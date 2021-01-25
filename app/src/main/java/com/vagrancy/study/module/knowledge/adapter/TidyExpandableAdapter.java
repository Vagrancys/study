package com.vagrancy.study.module.knowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识整理适配器
 */
public class TidyExpandableAdapter extends BaseExpandableListAdapter {
    private List<KnowledgeClass> groupData;
    private List<List<Knowledge>> childrenData;
    public Context context;
    public TidyExpandableAdapter(List<KnowledgeClass> groupData, List<List<Knowledge>> childrenData){
        this.groupData = groupData;
        this.childrenData = childrenData;
    }
    public class GroupViewHolder{
        TextView groupTitle;
        TextView groupCount;
        TextView groupQuality;
    }

    public class ChildViewHolder{
        TextView groupContent;
        TextView groupTime;
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(childrenData.size() <=0){
            return 0;
        }else if(childrenData.size() == groupPosition){
            return 0;
        }
        return childrenData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        context = parent.getContext();
        convertView = LayoutInflater.from(context).inflate(R.layout.item_group_header,null,false);
        GroupViewHolder groupViewHolder = new GroupViewHolder();
        KnowledgeClass knowledgeClass = groupData.get(groupPosition);
        groupViewHolder.groupTitle = convertView.findViewById(R.id.group_title);
        groupViewHolder.groupTitle.setText(knowledgeClass.getKnowledge_class_name());
        groupViewHolder.groupCount = convertView.findViewById(R.id.group_count);
        groupViewHolder.groupCount.setText(String.valueOf(knowledgeClass.getKnowledge_class_count()));
        groupViewHolder.groupQuality = convertView.findViewById(R.id.group_quality);
        groupViewHolder.groupQuality.setText(String.valueOf(knowledgeClass.getKnowledge_class_quality()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_group_body,null,false);
        ChildViewHolder childViewHolder = new ChildViewHolder();
        Knowledge knowledge = childrenData.get(groupPosition).get(childPosition);
        childViewHolder.groupContent = convertView.findViewById(R.id.group_content);
        childViewHolder.groupContent.setText(knowledge.getKnowledge_content());
        childViewHolder.groupTime = convertView.findViewById(R.id.group_time);
        childViewHolder.groupTime.setText(knowledge.getKnowledge_content());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
