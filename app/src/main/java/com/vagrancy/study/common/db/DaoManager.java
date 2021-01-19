package com.vagrancy.study.common.db;

import android.content.Context;

import com.vagrancy.study.BuildConfig;
import com.vagrancy.study.db.DaoMaster;
import com.vagrancy.study.db.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: greenDao管理类
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "study";
    private Context context;
    private volatile static DaoManager manager = new DaoManager();
    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sHelper;
    private static DaoSession sDaoSession;

    public static DaoManager getInstance(){
        return manager;
    }

    private DaoManager(){
        setDebug();
    }

    public void init(Context context){
        this.context = context;
    }

    public DaoMaster getDaoMaster(){
        if(sDaoMaster == null){
            DaoMaster.DevOpenHelper helper =
                    new DaoMaster.DevOpenHelper(context,DB_NAME,null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public DaoSession getDaoSession(){
        if(sDaoSession == null){
            if(sDaoMaster == null){
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    public void setDebug(){
        if(BuildConfig.DEBUG){
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if(sHelper != null){
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession(){
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }

}
