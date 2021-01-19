package com.vagrancy.study.model.knowledge.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.vagrancy.study.db.DaoSession;
import com.vagrancy.study.db.KnowledgeDao;
import com.vagrancy.study.db.KnowledgeClassDao;

/**
 * @author Vagrancy
 * @date 2021/1/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
@Entity
public class KnowledgeClass {
    //分组id
    @Id(autoincrement = true)
    private Long knowledge_class_id;
    //分组名称
    private String knowledge_class_name;
    //分组数量
    private int knowledge_class_count;
    //分组质量
    private int knowledge_class_quality;

    @ToMany(referencedJoinProperty = "knowledge_class")
    private List<Knowledge> knowledgeList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1119273602)
    private transient KnowledgeClassDao myDao;

    @Generated(hash = 1087415139)
    public KnowledgeClass(Long knowledge_class_id, String knowledge_class_name,
            int knowledge_class_count, int knowledge_class_quality) {
        this.knowledge_class_id = knowledge_class_id;
        this.knowledge_class_name = knowledge_class_name;
        this.knowledge_class_count = knowledge_class_count;
        this.knowledge_class_quality = knowledge_class_quality;
    }
    @Generated(hash = 260552202)
    public KnowledgeClass() {
    }
    public Long getKnowledge_class_id() {
        return this.knowledge_class_id;
    }
    public void setKnowledge_class_id(Long knowledge_class_id) {
        this.knowledge_class_id = knowledge_class_id;
    }
    public String getKnowledge_class_name() {
        return this.knowledge_class_name;
    }
    public void setKnowledge_class_name(String knowledge_class_name) {
        this.knowledge_class_name = knowledge_class_name;
    }
    public int getKnowledge_class_count() {
        return this.knowledge_class_count;
    }
    public void setKnowledge_class_count(int knowledge_class_count) {
        this.knowledge_class_count = knowledge_class_count;
    }
    public int getKnowledge_class_quality() {
        return this.knowledge_class_quality;
    }
    public void setKnowledge_class_quality(int knowledge_class_quality) {
        this.knowledge_class_quality = knowledge_class_quality;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1640359070)
    public List<Knowledge> getKnowledgeList() {
        if (knowledgeList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KnowledgeDao targetDao = daoSession.getKnowledgeDao();
            List<Knowledge> knowledgeListNew = targetDao
                    ._queryKnowledgeClass_KnowledgeList(knowledge_class_id);
            synchronized (this) {
                if (knowledgeList == null) {
                    knowledgeList = knowledgeListNew;
                }
            }
        }
        return knowledgeList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 459174405)
    public synchronized void resetKnowledgeList() {
        knowledgeList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 83610293)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKnowledgeClassDao() : null;
    }
}
