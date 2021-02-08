package com.vagrancy.study.module.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseViewAdapter;
import com.vagrancy.study.common.base.IViewAdapter;
import com.vagrancy.study.model.mine.entity.MineItem;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2021/2/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:  个人中心item适配
 */
public class MineItemAdapter extends BaseViewAdapter<MineItem, MineItemAdapter.MineItemViewHolder> {
    public MineItemAdapter(Context context, List<MineItem> list) {
        super(context, R.layout.item_mine_grid, list);
    }

    @Override
    public MineItemViewHolder onCreateViewHolder(View view) {
        return new MineItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(int position, MineItemViewHolder holder, MineItem mineItem) {
        holder.mineIcon.setImageResource(mineItem.getIcon());
        holder.mineText.setText(mineItem.getText());
    }

    public static class MineItemViewHolder extends IViewAdapter.CommonViewHolder{
        @BindView(R.id.mine_icon)
        ImageView mineIcon;
        @BindView(R.id.mine_text)
        TextView mineText;
        private MineItemViewHolder(View view){
            super(view);
        }
    }
}
