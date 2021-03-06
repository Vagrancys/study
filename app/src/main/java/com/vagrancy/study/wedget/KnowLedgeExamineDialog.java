package com.vagrancy.study.wedget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vagrancy.study.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识浏览弹窗
 */
public class KnowLedgeExamineDialog extends AlertDialog{
    private int mPosition;

    public KnowLedgeExamineDialog(Context context) {
        super(context);
    }

    protected KnowLedgeExamineDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.dialog_animation_style);
        setContentView(R.layout.dialog_examine_select);
        ButterKnife.bind(this);
    }

    public void setPosition(int position){
        mPosition = position;
    }

    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    @OnClick({R.id.dialog_look,R.id.dialog_update,R.id.dialog_close,R.id.dialog_delete,
            R.id.dialog_advanced,R.id.dialog_mileage})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.dialog_close:
                if(onDialogClickListener != null){
                    onDialogClickListener.onClose();
                }
                break;
            case R.id.dialog_delete:
                if(onDialogClickListener != null){
                    onDialogClickListener.onDelete(mPosition);
                }
                break;
            case R.id.dialog_look:
                if(onDialogClickListener != null){
                    onDialogClickListener.onLook(mPosition);
                }
                break;
            case R.id.dialog_update:
                if(onDialogClickListener != null){
                    onDialogClickListener.onUpdate(mPosition);
                }
                break;
            case R.id.dialog_advanced:
                if(onDialogClickListener != null){
                    onDialogClickListener.onAdvanced(mPosition);
                }
                break;
            case R.id.dialog_mileage:
                if(onDialogClickListener != null){
                    onDialogClickListener.onMileage(mPosition);
                }
                break;

        }
    }

    public interface OnDialogClickListener{
        void onClose();
        void onDelete(int position);
        void onUpdate(int position);
        void onLook(int position);
        void onAdvanced(int position);
        void onMileage(int position);
    }
}
