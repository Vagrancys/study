package com.vagrancy.study.wedget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.vagrancy.study.R;
import com.vagrancy.study.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2021/1/17
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识整理弹窗
 */
public class KnowLedgeTidyDialog extends AlertDialog{
    @BindView(R.id.dialog_insert_edit)
    EditText dialogInsertEdit;

    public KnowLedgeTidyDialog(Context context) {
        super(context);
    }

    protected KnowLedgeTidyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.dialog_animation_style);
        setContentView(R.layout.dialog_tidy_select);
        ButterKnife.bind(this);
    }

    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    @OnClick({R.id.dialog_close,R.id.dialog_cancel,R.id.dialog_determine})
    public void onClicked(View view){
        switch (view.getId()){
            case R.id.dialog_close:
                if(onDialogClickListener != null){
                    onDialogClickListener.onClose();
                }
                break;
            case R.id.dialog_cancel:
                if(onDialogClickListener != null){
                    onDialogClickListener.onCancel();
                }
                break;
            case R.id.dialog_determine:
                String insertStr = dialogInsertEdit.getText().toString();
                if(TextUtils.isEmpty(insertStr)){
                    ToastUtils.showToast(getContext(),R.string.common_empty_text);
                    return;
                }
                if(onDialogClickListener != null){
                    onDialogClickListener.onInsert(insertStr);
                }
                break;
        }
    }

    public interface OnDialogClickListener{
        void onClose();
        void onInsert(String edit);
        void onCancel();
    }
}
