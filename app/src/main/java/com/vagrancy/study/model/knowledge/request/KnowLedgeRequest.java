package com.vagrancy.study.model.knowledge.request;

import com.vagrancy.study.db.KnowledgeClassDao;
import com.vagrancy.study.db.KnowledgeDao;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.DaoUtilsStore;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识数据请求类
 */
public class KnowLedgeRequest {
    private static KnowLedgeRequest instance;
    private CommonDaoUtils<Knowledge> knowledgeUtils;
    private CommonDaoUtils<KnowledgeClass> knowledgeClassUtils;
    private KnowLedgeRequest(){
        knowledgeUtils = DaoUtilsStore.getInstance().getKnowledgeUtils();
        knowledgeClassUtils = DaoUtilsStore.getInstance().getKnowledgeClassUtils();
    }

    public static KnowLedgeRequest getInstance(){
        if(instance == null){
            synchronized (KnowLedgeRequest.class){
                if(instance == null){
                    instance = new KnowLedgeRequest();
                }
            }
        }
        return instance;
    }

    /**
     *  添加知识
     * @param object 知识
     * @return
     */
    public boolean insertKnowledge(Knowledge object) {
        return knowledgeUtils.insert(object);
    }

    /**
     * 删除知识
     * @param object
     * @return
     */
    public boolean deleteKnowledge(Knowledge object) {
        return knowledgeUtils.delete(object);
    }

    /**
     * 查询所有知识数据
     * @return
     */
    public List<Knowledge> queryAll() {
        return knowledgeUtils.queryAll();
    }

    /**
     * 查询单个知识数据
     * @param key
     * @return
     */
    public Knowledge query(long key) {
        return knowledgeUtils.queryById(key);
    }

    /**
     * 通过object来更新知识
     * @param object 知识
     * @return
     */
    public boolean update(Knowledge object) {
        return knowledgeUtils.update(object);
    }

    /**
     * 查询所有的知识分组数据
     * @return
     */
    public List<KnowledgeClass> queryGroupAll() {
        return knowledgeClassUtils.queryAll();
    }

    /**
     * 查询知识分组下的所有数据
     * @param knowledge_class_id
     * @return
     */
    public List<Knowledge> queryByClassIdAll(Long knowledge_class_id) {
        return knowledgeUtils.queryByQueryBuilder(KnowledgeDao.Properties.Knowledge_class.eq(knowledge_class_id));
    }

    /**
     * 添加知识分类选项
     * @param knowledgeClass
     * @return
     */
    public boolean insertKnowledgeClass(KnowledgeClass knowledgeClass) {
        return knowledgeClassUtils.insert(knowledgeClass);
    }

    /**
     * 删除知识分类
     * @param knowledgeClass
     * @return
     */
    public boolean deleteKnowledgeClass(KnowledgeClass knowledgeClass) {
        List<Knowledge> knowLedge = knowledgeClass.getKnowledgeList();
        if(knowLedge.size() >0){
            for (Knowledge knowledge :knowLedge){
                knowledge.setKnowledge_class(0);
                knowledgeUtils.update(knowledge);
            }
        }
        return knowledgeClassUtils.delete(knowledgeClass);
    }

    /**
     * 更新知识分类
     * @param knowledgeClass 知识分类合集
     * @return
     */
    public boolean updateKnowledgeClass(KnowledgeClass knowledgeClass) {
        return knowledgeClassUtils.update(knowledgeClass);
    }

    /**
     * 查询知识子项所有
     * @param knowledge_id 知识id
     * @return
     */
    public List<Knowledge> queryChildAll() {
        return knowledgeUtils.queryByQueryBuilder(KnowledgeDao.Properties.Knowledge_class.eq(0));
    }

    /**
     * 移动所有知识子项
     * @param knowledgeId 子项合集
     * @param knowledge_id 知识id
     * @return
     */
    public boolean moveKnowledgeChildAll(List<Long> knowledgeId, long knowledge_id) {
        boolean result = false;
        for (Long knowLedge : knowledgeId){
            Knowledge knowledge = knowledgeUtils.queryById(knowLedge);
            knowledge.setKnowledge_class(knowledge_id);
            knowledgeUtils.update(knowledge);
            result = true;
        }
        return result;
    }
}
