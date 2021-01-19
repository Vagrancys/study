package com.vagrancy.study.model.knowledge.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author Vagrancy
 * @date 2021/1/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识存成类
 */
@Entity
public class Knowledge {

    @Id(autoincrement = true)
    private Long _id;
    private String knowledge_content;
    private long knowledge_save_time;
    private int knowledge_state;
    private long knowledge_class;
    @Generated(hash = 2099078987)
    public Knowledge(Long _id, String knowledge_content, long knowledge_save_time,
            int knowledge_state, long knowledge_class) {
        this._id = _id;
        this.knowledge_content = knowledge_content;
        this.knowledge_save_time = knowledge_save_time;
        this.knowledge_state = knowledge_state;
        this.knowledge_class = knowledge_class;
    }
    @Generated(hash = 2109785241)
    public Knowledge() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getKnowledge_content() {
        return this.knowledge_content;
    }
    public void setKnowledge_content(String knowledge_content) {
        this.knowledge_content = knowledge_content;
    }
    public long getKnowledge_save_time() {
        return this.knowledge_save_time;
    }
    public void setKnowledge_save_time(long knowledge_save_time) {
        this.knowledge_save_time = knowledge_save_time;
    }
    public int getKnowledge_state() {
        return this.knowledge_state;
    }
    public void setKnowledge_state(int knowledge_state) {
        this.knowledge_state = knowledge_state;
    }
    public long getKnowledge_class() {
        return this.knowledge_class;
    }
    public void setKnowledge_class(long knowledge_class) {
        this.knowledge_class = knowledge_class;
    }
}
