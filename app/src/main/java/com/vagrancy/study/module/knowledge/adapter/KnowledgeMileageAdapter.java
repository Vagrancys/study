package com.vagrancy.study.module.knowledge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/2/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程适配器
 */
public class KnowledgeMileageAdapter extends BaseViewAdapter<KnowledgeMileage,KnowledgeMileageAdapter.KnowledgeMileageViewHolder> {
    public KnowledgeMileageAdapter(Context context, List<KnowledgeMileage> list) {
        super(context, R.layout.knowledge_mileage_item, list);
    }

    @Override
    public KnowledgeMileageViewHolder onCreateViewHolder(View view) {
        return new KnowledgeMileageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(int position, KnowledgeMileageViewHolder holder,
                                 KnowledgeMileage knowledgeMileage) {
        holder.mileageTitle.setText(knowledgeMileage.getKnowledge_mileage_title());
        holder.mileageAction.setText(knowledgeMileage.getKnowledge_mileage_action());
        holder.mileageAffect.setText(knowledgeMileage.getKnowledge_mileage_affect());
        holder.mileageResult.setText(knowledgeMileage.getKnowledge_mileage_result());
        holder.mileageSummary.setText(knowledgeMileage.getKnowledge_mileage_summary());
        holder.mileageTime.setText(TimeUtils.getTimeText(knowledgeMileage.getKnowledge_mileage_time()));
    }

    public static class KnowledgeMileageViewHolder extends IViewAdapter.CommonViewHolder{
        @BindView(R.id.mileage_title)
        TextView mileageTitle;
        @BindView(R.id.mileage_action)
        TextView mileageAction;
        @BindView(R.id.mileage_affect)
        TextView mileageAffect;
        @BindView(R.id.mileage_summary)
        TextView mileageSummary;
        @BindView(R.id.mileage_result)
        TextView mileageResult;
        @BindView(R.id.mileage_time)
        TextView mileageTime;
        public KnowledgeMileageViewHolder(View view) {
            super(view);
        }
    }
}
