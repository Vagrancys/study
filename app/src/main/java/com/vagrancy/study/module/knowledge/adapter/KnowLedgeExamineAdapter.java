package com.vagrancy.study.module.knowledge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.model.knowledge.entity.Knowledge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识查看适配器
 */
public class KnowLedgeExamineAdapter extends BaseViewAdapter<Knowledge, KnowLedgeExamineAdapter.KnowLedgeExamineViewHolder> {
    private SimpleDateFormat simpleDateFormat;
    public KnowLedgeExamineAdapter(Context context, List<Knowledge> list) {
        super(context,R.layout.knowledge_examine_item,R.layout.item_common_footer,list);
        simpleDateFormat = new SimpleDateFormat("HH:ss:mm", Locale.getDefault());
    }

    @Override
    public KnowLedgeExamineViewHolder onCreateViewHolder(View view) {
        return new KnowLedgeExamineViewHolder(view);
    }

    @Override
    public KnowledgeExamineHeadHolder onCreateHeadHolder(View view) {
        return new KnowledgeExamineHeadHolder(view);
    }

    @Override
    public KnowledgeExamineFootHolder onCreateFootHolder(View view) {
        return new KnowledgeExamineFootHolder(view);
    }

    @Override
    public CommonViewHolder onCreateEmptyHolder(View view) {
        return new KnowledgeExamineEmptyHolder(view);
    }

    @Override
    public void onBindViewHolder(int position, KnowLedgeExamineViewHolder mHolder, Knowledge knowledge) {
        mHolder.examineNumber.setText(String.valueOf(knowledge.get_id()));
        mHolder.examineContent.setText(knowledge.getKnowledge_content());
        mHolder.examineTime.setText(simpleDateFormat.format(new Date(knowledge.getKnowledge_save_time())));
    }

    @Override
    public void onBindHeadHolder(int position,CommonViewHolder holder) {
        KnowledgeExamineHeadHolder headHolder = (KnowledgeExamineHeadHolder) holder;
        headHolder.commonHeader.setText(R.string.common_header_text);
    }

    @Override
    public void onBindFootHolder(int position,CommonViewHolder holder) {
        KnowledgeExamineFootHolder footHolder = (KnowledgeExamineFootHolder) holder;
        footHolder.commonFooter.setText(R.string.common_footer_text);
    }

    @Override
    public void onBindEmptyHolder(int position, CommonViewHolder holder) {
        KnowledgeExamineEmptyHolder emptyHolder = (KnowledgeExamineEmptyHolder) holder;
        emptyHolder.commonEmpty.setText(R.string.common_empty_text);
    }

    public static class KnowLedgeExamineViewHolder extends BaseViewAdapter.CommonViewHolder{
        @BindView(R.id.examine_number)
        TextView examineNumber;
        @BindView(R.id.examine_content)
        TextView examineContent;
        @BindView(R.id.examine_time)
        TextView examineTime;
        public KnowLedgeExamineViewHolder(View view){
            super(view);
        }
    }

    public static class KnowledgeExamineHeadHolder extends BaseViewAdapter.CommonViewHolder{
        @BindView(R.id.common_header)
        TextView commonHeader;
        public KnowledgeExamineHeadHolder(View view){
            super(view);
        }
    }

    public static class KnowledgeExamineFootHolder extends BaseViewAdapter.CommonViewHolder{
        @BindView(R.id.common_footer)
        TextView commonFooter;
        public KnowledgeExamineFootHolder(View view){
            super(view);
        }
    }

    public static class KnowledgeExamineEmptyHolder extends BaseViewAdapter.CommonViewHolder{
        @BindView(R.id.common_empty)
        TextView commonEmpty;
        public KnowledgeExamineEmptyHolder(View view){
            super(view);
        }
    }
}
