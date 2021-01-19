package com.vagrancy.study.utils;

import android.content.Context;
import android.widget.Toast;

import com.vagrancy.study.R;

/**
 * @author Vagrancy
 * @date 2021/1/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 吐司工具类
 */
public class ToastUtils {
    /**
     * 显示吐司
     * @param context
     */
    public static void showToast(Context context,int text){
        Toast.makeText(context,context.getResources().getText(text),Toast.LENGTH_SHORT).show();
    }
}
