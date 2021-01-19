package com.vagrancy.study.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 时间工具类
 */
public class TimeUtils {

    private TimeUtils(){

    }

    /**
     * 获取时间文本
     * @param time
     * @return
     */
    public static String getTimeText(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date(time));
    }
}
