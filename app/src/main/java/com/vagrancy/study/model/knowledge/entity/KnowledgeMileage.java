package com.vagrancy.study.model.knowledge.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2021/2/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识里程实体类
 */
@Entity
public class KnowledgeMileage {
    @Id
    private Long knowledge_mileage_id;
    private String knowledge_mileage_kid;
    //标题
    private String knowledge_mileage_title;
    //行动
    private String knowledge_mileage_action;
    //影响
    private String knowledge_mileage_affect;
    //结果
    private String knowledge_mileage_result;
    //时间
    private long knowledge_mileage_time;
    //感想
    private String knowledge_mileage_summary;
    @Generated(hash = 2016327967)
    public KnowledgeMileage(Long knowledge_mileage_id, String knowledge_mileage_kid,
            String knowledge_mileage_title, String knowledge_mileage_action,
            String knowledge_mileage_affect, String knowledge_mileage_result,
            long knowledge_mileage_time, String knowledge_mileage_summary) {
        this.knowledge_mileage_id = knowledge_mileage_id;
        this.knowledge_mileage_kid = knowledge_mileage_kid;
        this.knowledge_mileage_title = knowledge_mileage_title;
        this.knowledge_mileage_action = knowledge_mileage_action;
        this.knowledge_mileage_affect = knowledge_mileage_affect;
        this.knowledge_mileage_result = knowledge_mileage_result;
        this.knowledge_mileage_time = knowledge_mileage_time;
        this.knowledge_mileage_summary = knowledge_mileage_summary;
    }
    @Generated(hash = 384794869)
    public KnowledgeMileage() {
    }
    public Long getKnowledge_mileage_id() {
        return this.knowledge_mileage_id;
    }
    public void setKnowledge_mileage_id(Long knowledge_mileage_id) {
        this.knowledge_mileage_id = knowledge_mileage_id;
    }
    public String getKnowledge_mileage_kid() {
        return this.knowledge_mileage_kid;
    }
    public void setKnowledge_mileage_kid(String knowledge_mileage_kid) {
        this.knowledge_mileage_kid = knowledge_mileage_kid;
    }
    public String getKnowledge_mileage_title() {
        return this.knowledge_mileage_title;
    }
    public void setKnowledge_mileage_title(String knowledge_mileage_title) {
        this.knowledge_mileage_title = knowledge_mileage_title;
    }
    public String getKnowledge_mileage_action() {
        return this.knowledge_mileage_action;
    }
    public void setKnowledge_mileage_action(String knowledge_mileage_action) {
        this.knowledge_mileage_action = knowledge_mileage_action;
    }
    public String getKnowledge_mileage_affect() {
        return this.knowledge_mileage_affect;
    }
    public void setKnowledge_mileage_affect(String knowledge_mileage_affect) {
        this.knowledge_mileage_affect = knowledge_mileage_affect;
    }
    public String getKnowledge_mileage_result() {
        return this.knowledge_mileage_result;
    }
    public void setKnowledge_mileage_result(String knowledge_mileage_result) {
        this.knowledge_mileage_result = knowledge_mileage_result;
    }
    public long getKnowledge_mileage_time() {
        return this.knowledge_mileage_time;
    }
    public void setKnowledge_mileage_time(long knowledge_mileage_time) {
        this.knowledge_mileage_time = knowledge_mileage_time;
    }
    public String getKnowledge_mileage_summary() {
        return this.knowledge_mileage_summary;
    }
    public void setKnowledge_mileage_summary(String knowledge_mileage_summary) {
        this.knowledge_mileage_summary = knowledge_mileage_summary;
    }
   
}
