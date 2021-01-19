package com.vagrancy.study.utils;

import android.util.Log;

import com.vagrancy.study.common.db.DaoManager;
import com.vagrancy.study.db.DaoSession;
import com.vagrancy.study.model.knowledge.entity.Knowledge;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据库操作类
 */
public class CommonDaoUtils<T> {
    private static final String TAG = CommonDaoUtils.class.getSimpleName();
    private DaoSession daoSession;
    private Class<T> entityClass;
    private AbstractDao<T,Long> entityDao;

    public CommonDaoUtils(Class<T> pEntityClass, AbstractDao<T,Long> pEntityDao){
        DaoManager mManager = DaoManager.getInstance();
        daoSession = mManager.getDaoSession();
        entityClass = pEntityClass;
        entityDao = pEntityDao;
    }

    /**
     * 插入记录 如果表未创建,先创建表
     * @param pEntity
     * @return
     */
    public boolean insert(T pEntity){
        boolean flag = entityDao.insert(pEntity) != -1;
        Log.i(TAG,"insert :" +flag +"-->"+pEntity.toString());
        return flag;
    }

    /**
     * 插入多条数据,在子线程操作
     * @param pEntityList
     * @return
     */
    public boolean insertMulti(final List<T> pEntityList){
        try {
            daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T tableData : pEntityList){
                        daoSession.insertOrReplace(tableData);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改一条数据
     * @param pEntity
     * @return
     */
    public boolean update(T pEntity){
        try {
            daoSession.update(pEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除单条记录
     * @param pEntity
     * @return
     */
    public boolean delete(T pEntity){
        try {
            daoSession.delete(pEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        try {
            daoSession.deleteAll(entityClass);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<T> queryAll(){
        return daoSession.loadAll(entityClass);
    }

    /**
     * 根据主键id 查询记录
     * @param key
     * @return
     */
    public T queryById(long key){
        return daoSession.load(entityClass,key);
    }

    /**
     * 使用queryBuilder进行查询
     * @param condition
     * @param conditions
     * @return
     */
    public List<T> queryByQueryBuilder(WhereCondition condition,WhereCondition... conditions){
        QueryBuilder<T> queryBuilder = daoSession.queryBuilder(entityClass);
        return queryBuilder.where(condition,conditions).list();
    }
}
