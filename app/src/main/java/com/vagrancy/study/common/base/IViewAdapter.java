package com.vagrancy.study.common.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础recycler适配器
 */
public abstract class IViewAdapter<T,VH extends IViewAdapter.CommonViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> list;
    private int layoutId;
    private int headerId;
    private int footerId;
    private Context mContext;
    private static final int ITEM = 0;
    private static final int HEAD = 1;
    private static final int FOOT = 2;
    private static final int EMPTY = 3;
    private static boolean IS_HEAD = false;
    private static boolean IS_FOOT = false;
    private static boolean IS_EMPTY = false;
    public IViewAdapter(Context context, int layout, int footerId, int headerId, List<T> list){
        this(context,layout,footerId,list);
        this.headerId = headerId;
        IS_HEAD = true;
    }

    public IViewAdapter(Context context, int layout, int footerId, List<T> list){
        this(context,layout,list);
        this.footerId = footerId;
        IS_FOOT = true;
    }

    public IViewAdapter(Context context, int layoutId, List<T> list){
        mContext = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    public void setIsFoot(boolean isFoot) {
        IS_FOOT = isFoot;
    }

    public void setIsHead(boolean isHead) {
        IS_HEAD = isHead;
    }

    public void setEmpty(boolean empty){
        IS_EMPTY = empty;
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.getParentView().setOnClickListener(v->{
            if(onItemClickListener != null){
                onItemClickListener.OnItemClick(position);
            }
        });
        holder.getParentView().setOnLongClickListener(v-> {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.OnItemLongClick(position);
            }
            return false;
        });
        if(IS_EMPTY){
            onBindEmptyHolder(position,holder);
        }else{
            if(position == 0 & IS_HEAD){
                onBindHeadHolder(position,holder);
            }else if(position ==getItemCount()-1 & IS_FOOT){
                onBindFootHolder(position,holder);
            }else{
                int mPosition = position;
                if(IS_HEAD){
                    mPosition -= 1;
                }
                onBindViewHolder(position,holder,list.get(mPosition));
            }
        }
    }

    @Override
    public int getItemCount() {
        if(list != null){
            if(list.size() > 0){
                return list.size() +(IS_HEAD ? 1: 0)+(IS_FOOT ? 1 :0);
            }
        }
        return 0;
    }

    public abstract VH onCreateViewHolder(View view);
    public abstract CommonViewHolder onCreateHeadHolder(View view);
    public abstract CommonViewHolder onCreateFootHolder(View view);
    public abstract CommonViewHolder onCreateEmptyHolder(View view);

    public abstract void onBindViewHolder(int position,VH vh,T t);
    public abstract void onBindHeadHolder(int position,CommonViewHolder holder);
    public abstract void onBindFootHolder(int position,CommonViewHolder holder);
    public abstract void onBindEmptyHolder(int position,CommonViewHolder holder);
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        VH holder;

        switch (viewType){
            case HEAD:
                view = LayoutInflater.from(parent.getContext()).inflate(headerId,parent,false);
                holder = (VH) onCreateHeadHolder(view);
                break;
            case FOOT:
                view = LayoutInflater.from(parent.getContext()).inflate(footerId,parent,false);
                holder = (VH) onCreateFootHolder(view);
                break;
            case EMPTY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_empty,parent,false);
                holder = (VH) onCreateEmptyHolder(view);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
                holder = onCreateViewHolder(view);
                break;
        }
        return holder;
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(int position);
    }

    @Override
    public int getItemViewType(int position) {

        if(IS_EMPTY){
            return EMPTY;
        }else{
            if(IS_HEAD){
                if(position == 0){
                    return HEAD;
                }
            }
            if(IS_FOOT){
                if(position == getItemCount()-1){
                    return FOOT;
                }
            }
            return ITEM;
        }

    }

    public abstract static class CommonViewHolder extends RecyclerView.ViewHolder{
        private View parentView;
        public CommonViewHolder(View view){
            super(view);
            this.parentView = view;
            ButterKnife.bind(this,view);
        }

        View getParentView(){
            return parentView;
        }
    }

    /**
     * 判断是否点击item
     * @param position
     * @return
     */
    public boolean isItem(int position){
        if(position <= 0 &&IS_EMPTY) {
            return false;
        }
        if(position ==0 && IS_HEAD){
            return false;
        }
        if(position == getItemCount()-1 && IS_FOOT){
            return false;
        }
        return true;
    }
}
