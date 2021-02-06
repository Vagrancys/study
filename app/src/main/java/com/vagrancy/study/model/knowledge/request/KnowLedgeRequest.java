package com.vagrancy.study.model.knowledge.request;

import android.util.Log;

import com.vagrancy.study.db.KnowledgeAdvancedDao;
import com.vagrancy.study.db.KnowledgeClassDao;
import com.vagrancy.study.db.KnowledgeDao;
import com.vagrancy.study.db.KnowledgeMileageDao;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.model.knowledge.entity.KnowledgeMileage;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.DaoUtilsStore;

import java.util.ArrayList;
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
    private CommonDaoUtils<KnowledgeAdvanced> knowledgeAdvancedUtils;
    private CommonDaoUtils<KnowledgeMileage> knowledgeMileageUtils;
    private KnowLedgeRequest(){
        knowledgeUtils = DaoUtilsStore.getInstance().getKnowledgeUtils();
        knowledgeClassUtils = DaoUtilsStore.getInstance().getKnowledgeClassUtils();
        knowledgeAdvancedUtils = DaoUtilsStore.getInstance().getKnowledgeAdvancedUtils();
        knowledgeMileageUtils = DaoUtilsStore.getInstance().getKnowledgeMileageUtils();
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

    /**
     * 删除知识的父类
     * @param object
     * @return
     */
    public boolean deleteClassByKnowledge(Knowledge object) {
        object.setKnowledge_class(0);
        return knowledgeUtils.update(object);
    }

    /**
     * 查询知识分类
     * @param knowledge_class
     * @return
     */
    public List<KnowledgeClass> queryClassByChildIdAll(long knowledge_class) {
        List<KnowledgeClass> knowledgeClass = knowledgeClassUtils.queryAll();
        List<KnowledgeClass> newKnowledge = new ArrayList<>();
        for (KnowledgeClass knowLedge : knowledgeClass){
            if(!knowLedge.getKnowledge_class_id().equals(knowledge_class)){
                newKnowledge.add(knowLedge);
            }
        }
        return newKnowledge;
    }

    /**
     * 更新分类数据
     * @param knowledge_id 知识id
     * @param selectChild 知识分类id
     * @return
     */
    public boolean moveKnowledgeChildByClassId(long knowledge_id, Long selectChild) {
        Knowledge knowledge = knowledgeUtils.queryById(knowledge_id);
        knowledge.setKnowledge_class(selectChild);
        return knowledgeUtils.update(knowledge);
    }

    /**
     * 通过id查找进阶数据
     * @param
     * @return
     */
    public KnowledgeAdvanced queryKnowledgeAdvanced(long knowledge_advanced) {
        return knowledgeAdvancedUtils.queryById(knowledge_advanced);
    }

    /**
     * 添加知识进阶
     * @return
     */
    public boolean insertKnowledgeAdvanced(long knowledge_id) {
        KnowledgeAdvanced knowledgeAdvanced = new KnowledgeAdvanced();
        knowledgeAdvanced.setAdvanced_count(0);
        knowledgeAdvanced.setAdvanced_master(0);
        knowledgeAdvanced.setKnowledge_kid(knowledge_id);
        knowledgeAdvanced.setAdvanced_master_ask(1);
        knowledgeAdvanced.setAdvanced_nickname("");
        knowledgeAdvanced.setAdvanced_quality(0);
        knowledgeAdvanced.setAdvanced_quality_ask(1);
        knowledgeAdvanced.setAdvanced_seniority(0);
        knowledgeAdvanced.setAdvanced_seniority_ask(1);
        knowledgeAdvanced.setAdvanced_size(0);
        knowledgeAdvanced.setAdvanced_size_ask(100);
        knowledgeAdvanced.setAdvanced_now(1);
        return knowledgeAdvancedUtils.insert(knowledgeAdvanced);
    }

    /**
     * 更新知识进阶
     * @param knowledgeAdvanced
     * @return
     */
    public KnowledgeAdvanced updateKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
        KnowledgeAdvanced newKnowledgeAdvanced = updateKnowledge(knowledgeAdvanced);
        boolean result = knowledgeAdvancedUtils.update(newKnowledgeAdvanced);
        if(result){
            return newKnowledgeAdvanced;
        }else{
            return null;
        }
    }

    /**
     * 更新知识进阶
     * @param knowledgeAdvanced
     * @return
     */
    public boolean updateOneKnowledgeAdvanced(KnowledgeAdvanced knowledgeAdvanced) {
        return knowledgeAdvancedUtils.update(knowledgeAdvanced);
    }

    private KnowledgeAdvanced updateKnowledge(KnowledgeAdvanced knowledgeAdvanced) {
        switch (knowledgeAdvanced.getAdvanced_now()){
            case 1:
                knowledgeAdvanced.setAdvanced_size_ask(100);
                knowledgeAdvanced.setAdvanced_quality_ask(1);
                knowledgeAdvanced.setAdvanced_seniority_ask(1);
                knowledgeAdvanced.setAdvanced_master_ask(1);
                break;
            case 2:
                knowledgeAdvanced.setAdvanced_size_ask(300);
                knowledgeAdvanced.setAdvanced_quality_ask(3);
                knowledgeAdvanced.setAdvanced_seniority_ask(3);
                knowledgeAdvanced.setAdvanced_master_ask(2);
                break;
            case 3:
                knowledgeAdvanced.setAdvanced_size_ask(500);
                knowledgeAdvanced.setAdvanced_quality_ask(5);
                knowledgeAdvanced.setAdvanced_seniority_ask(5);
                knowledgeAdvanced.setAdvanced_master_ask(3);
                break;
            case 4:
                knowledgeAdvanced.setAdvanced_size_ask(800);
                knowledgeAdvanced.setAdvanced_quality_ask(8);
                knowledgeAdvanced.setAdvanced_seniority_ask(8);
                knowledgeAdvanced.setAdvanced_master_ask(4);
                break;
            case 5:
                knowledgeAdvanced.setAdvanced_size_ask(1200);
                knowledgeAdvanced.setAdvanced_quality_ask(12);
                knowledgeAdvanced.setAdvanced_seniority_ask(12);
                knowledgeAdvanced.setAdvanced_master_ask(5);
                break;
        }
        knowledgeAdvanced.setAdvanced_count(knowledgeAdvanced.getAdvanced_count()+1);
        knowledgeAdvanced.setAdvanced_now(knowledgeAdvanced.getAdvanced_now()+1);
        return knowledgeAdvanced;
    }

    /**
     * 通过知识id查找知识进阶
     * @param knowledge_id 知识id
     * @return
     */
    public KnowledgeAdvanced queryKnowledgeAdvancedByKnowledgeId(long knowledge_id) {
        List<KnowledgeAdvanced> knowledgeAdvanced = knowledgeAdvancedUtils.queryByQueryBuilder(KnowledgeAdvancedDao.Properties.Knowledge_kid.eq(knowledge_id));
        if(knowledgeAdvanced.size() > 0){
            return knowledgeAdvanced.get(0);
        }else{
            return null;
        }
    }

    /**
     * 通过知识id查找知识里程
     */
    public List<KnowledgeMileage> queryKnowledgeMileage(long knowledge_id) {
        return knowledgeMileageUtils.queryByQueryBuilder(KnowledgeMileageDao.Properties.Knowledge_mileage_kid.eq(knowledge_id));
    }
}
