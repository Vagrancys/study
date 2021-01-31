package com.vagrancy.study.utils;

import com.vagrancy.study.common.db.DaoManager;
import com.vagrancy.study.db.DaoMaster;
import com.vagrancy.study.db.KnowledgeAdvancedDao;
import com.vagrancy.study.db.KnowledgeClassDao;
import com.vagrancy.study.db.KnowledgeDao;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

/**
 * @author Vagrancy
 * @date 2021/1/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 保存commonDao工具类
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private CommonDaoUtils<Knowledge> knowledgeUtils;
    private CommonDaoUtils<KnowledgeClass> knowledgeClassUtils;
    private CommonDaoUtils<KnowledgeAdvanced> knowledgeAdvancedUtils;

    public static DaoUtilsStore getInstance(){
        return instance;
    }

    private DaoUtilsStore(){
        DaoManager mManager = DaoManager.getInstance();
        //知识表操作
        KnowledgeDao knowledgeDao = mManager.getDaoSession().getKnowledgeDao();
        knowledgeUtils = new CommonDaoUtils<>(Knowledge.class,knowledgeDao);
        //知识分组操作
        KnowledgeClassDao knowledgeClassDao = mManager.getDaoSession().getKnowledgeClassDao();
        knowledgeClassUtils = new CommonDaoUtils<>(KnowledgeClass.class,knowledgeClassDao);
        //知识进阶操作
        KnowledgeAdvancedDao knowledgeAdvancedDao = mManager.getDaoSession().getKnowledgeAdvancedDao();
        knowledgeAdvancedUtils = new CommonDaoUtils<>(KnowledgeAdvanced.class,knowledgeAdvancedDao);
    }

    public CommonDaoUtils<Knowledge> getKnowledgeUtils() {
        return knowledgeUtils;
    }

    public CommonDaoUtils<KnowledgeClass> getKnowledgeClassUtils() {
        return knowledgeClassUtils;
    }

    public CommonDaoUtils<KnowledgeAdvanced> getKnowledgeAdvancedUtils() {
        return knowledgeAdvancedUtils;
    }
}
