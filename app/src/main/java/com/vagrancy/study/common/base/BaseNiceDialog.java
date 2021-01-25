package com.vagrancy.study.common.base;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.vagrancy.study.R;
import com.vagrancy.study.utils.CommonUtils;
import com.vagrancy.study.wedget.NiceViewHolder;

/**
 * @author Vagrancy
 * @date 2021/1/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础弹窗
 */
public abstract class BaseNiceDialog extends DialogFragment {
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String CANCEL = "out_cancel";
    private static final String THEME = "theme";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";
    //边距
    private int margin;
    //宽度
    private int width;
    //高度
    private int height;
    //灰度深浅
    private float dimAmount = 0.5f;
    //显示位置
    private int gravity = Gravity.CENTER;
    //是否点击外部消失
    private boolean outCancel = true;
    @StyleRes
    protected int theme = R.style.Nice_dialog_style;
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    public abstract int getLayoutId();

    public abstract void convertView(NiceViewHolder holder, BaseNiceDialog dialog);

    public int initTheme(){
        return theme;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, initTheme());
        if(savedInstanceState != null){
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            gravity = savedInstanceState.getInt(GRAVITY);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            theme = savedInstanceState.getInt(THEME);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutId = getLayoutId();
        View view = inflater.inflate(getLayoutId(),container,false);
        convertView(NiceViewHolder.create(view),this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putInt(GRAVITY, gravity);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(THEME, theme);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams(){
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            if (gravity != 0) {
                lp.gravity = gravity;
            }
            //设置dialog宽度
            if (width == 0) {
                lp.width = CommonUtils.getScreenWidth(getContext()) - 2 * margin;
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.width = CommonUtils.dp2px(getContext(), width);
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = CommonUtils.dp2px(getContext(), height);
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    public BaseNiceDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseNiceDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseNiceDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseNiceDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseNiceDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public BaseNiceDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public BaseNiceDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseNiceDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }
}
