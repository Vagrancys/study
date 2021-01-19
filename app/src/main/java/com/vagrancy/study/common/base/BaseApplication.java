package com.vagrancy.study.common.base;

import android.app.Application;

import com.vagrancy.study.common.db.DaoManager;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 应用中心
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }
    private void initGreenDao(){
        DaoManager manager = DaoManager.getInstance();
        manager.init(this);
    }
}
