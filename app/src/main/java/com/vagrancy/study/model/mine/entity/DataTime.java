package com.vagrancy.study.model.mine.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Vagrancy
 * @date 2021/2/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据时间
 */
@Entity
public class DataTime {
    @Id
    private Long data_id;
    //开始努力次数
    private long start_time;
    //努力天数
    private int data_count;
    //最新时间
    private long new_time;
    //达成次数
    private int reach_count;
    //总知识数
    private int total_count;
}
