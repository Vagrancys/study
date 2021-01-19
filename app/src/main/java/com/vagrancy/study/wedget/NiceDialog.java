package com.vagrancy.study.wedget;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.vagrancy.study.common.base.BaseNiceDialog;

/**
 * @author Vagrancy
 * @date 2021/1/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 使用dialog
 */
public class NiceDialog extends BaseNiceDialog {
    private ViewConvertListener convertListener;

    public static NiceDialog init(){
        return new NiceDialog();
    }

    @Override
    public int initTheme() {
        return theme;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(NiceViewHolder holder, BaseNiceDialog dialog) {
        if(convertListener != null){
            convertListener.convertView(holder,dialog);
        }
    }

    public NiceDialog setTheme(@StyleRes int theme) {
        this.theme = theme;
        return this;
    }

    public NiceDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public NiceDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable("listener");
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listener", convertListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        convertListener = null;
    }
}
