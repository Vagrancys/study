package com.vagrancy.study.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author Vagrancy
 * @date 2021/1/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 通用工具类
 */
public class CommonUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dp2px(Context context,int id){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (context.getResources().getDimension(id) * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
