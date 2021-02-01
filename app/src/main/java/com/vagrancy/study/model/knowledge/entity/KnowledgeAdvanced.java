package com.vagrancy.study.model.knowledge.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶实体类
 */
@Entity
public class KnowledgeAdvanced {
    @Id
    private Long advanced_id;
    //知识的id索引
    private long knowledge_kid;
    //当前阶级
    private int advanced_now;
    //进阶次数
    private int advanced_count;
    //进阶称呼
    private String advanced_nickname;
    //大小
    private int advanced_size;
    //进阶要求大小
    private int advanced_size_ask;
    //质量
    private int advanced_quality;
    //进阶要求质量
    private int advanced_quality_ask;
    //精通
    private int advanced_master;
    //进阶要求精通
    private int advanced_master_ask;
    //资格
    private int advanced_seniority;
    //进阶要求资格
    private int advanced_seniority_ask;
    @Generated(hash = 1883279091)
    public KnowledgeAdvanced(Long advanced_id, long knowledge_kid, int advanced_now,
            int advanced_count, String advanced_nickname, int advanced_size,
            int advanced_size_ask, int advanced_quality, int advanced_quality_ask,
            int advanced_master, int advanced_master_ask, int advanced_seniority,
            int advanced_seniority_ask) {
        this.advanced_id = advanced_id;
        this.knowledge_kid = knowledge_kid;
        this.advanced_now = advanced_now;
        this.advanced_count = advanced_count;
        this.advanced_nickname = advanced_nickname;
        this.advanced_size = advanced_size;
        this.advanced_size_ask = advanced_size_ask;
        this.advanced_quality = advanced_quality;
        this.advanced_quality_ask = advanced_quality_ask;
        this.advanced_master = advanced_master;
        this.advanced_master_ask = advanced_master_ask;
        this.advanced_seniority = advanced_seniority;
        this.advanced_seniority_ask = advanced_seniority_ask;
    }
    @Generated(hash = 815527565)
    public KnowledgeAdvanced() {
    }
    public Long getAdvanced_id() {
        return this.advanced_id;
    }
    public void setAdvanced_id(Long advanced_id) {
        this.advanced_id = advanced_id;
    }
    public long getKnowledge_kid() {
        return this.knowledge_kid;
    }
    public void setKnowledge_kid(long knowledge_kid) {
        this.knowledge_kid = knowledge_kid;
    }
    public int getAdvanced_now() {
        return this.advanced_now;
    }
    public void setAdvanced_now(int advanced_now) {
        this.advanced_now = advanced_now;
    }
    public int getAdvanced_count() {
        return this.advanced_count;
    }
    public void setAdvanced_count(int advanced_count) {
        this.advanced_count = advanced_count;
    }
    public String getAdvanced_nickname() {
        return this.advanced_nickname;
    }
    public void setAdvanced_nickname(String advanced_nickname) {
        this.advanced_nickname = advanced_nickname;
    }
    public int getAdvanced_size() {
        return this.advanced_size;
    }
    public void setAdvanced_size(int advanced_size) {
        this.advanced_size = advanced_size;
    }
    public int getAdvanced_size_ask() {
        return this.advanced_size_ask;
    }
    public void setAdvanced_size_ask(int advanced_size_ask) {
        this.advanced_size_ask = advanced_size_ask;
    }
    public int getAdvanced_quality() {
        return this.advanced_quality;
    }
    public void setAdvanced_quality(int advanced_quality) {
        this.advanced_quality = advanced_quality;
    }
    public int getAdvanced_quality_ask() {
        return this.advanced_quality_ask;
    }
    public void setAdvanced_quality_ask(int advanced_quality_ask) {
        this.advanced_quality_ask = advanced_quality_ask;
    }
    public int getAdvanced_master() {
        return this.advanced_master;
    }
    public void setAdvanced_master(int advanced_master) {
        this.advanced_master = advanced_master;
    }
    public int getAdvanced_master_ask() {
        return this.advanced_master_ask;
    }
    public void setAdvanced_master_ask(int advanced_master_ask) {
        this.advanced_master_ask = advanced_master_ask;
    }
    public int getAdvanced_seniority() {
        return this.advanced_seniority;
    }
    public void setAdvanced_seniority(int advanced_seniority) {
        this.advanced_seniority = advanced_seniority;
    }
    public int getAdvanced_seniority_ask() {
        return this.advanced_seniority_ask;
    }
    public void setAdvanced_seniority_ask(int advanced_seniority_ask) {
        this.advanced_seniority_ask = advanced_seniority_ask;
    }
   
}
