package com.vagrancy.study.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "KNOWLEDGE_CLASS".
*/
public class KnowledgeClassDao extends AbstractDao<KnowledgeClass, Long> {

    public static final String TABLENAME = "KNOWLEDGE_CLASS";

    /**
     * Properties of entity KnowledgeClass.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Knowledge_class_id = new Property(0, Long.class, "knowledge_class_id", true, "_id");
        public final static Property Knowledge_class_name = new Property(1, String.class, "knowledge_class_name", false, "KNOWLEDGE_CLASS_NAME");
        public final static Property Knowledge_class_count = new Property(2, int.class, "knowledge_class_count", false, "KNOWLEDGE_CLASS_COUNT");
        public final static Property Knowledge_class_quality = new Property(3, int.class, "knowledge_class_quality", false, "KNOWLEDGE_CLASS_QUALITY");
    }

    private DaoSession daoSession;


    public KnowledgeClassDao(DaoConfig config) {
        super(config);
    }
    
    public KnowledgeClassDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"KNOWLEDGE_CLASS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: knowledge_class_id
                "\"KNOWLEDGE_CLASS_NAME\" TEXT," + // 1: knowledge_class_name
                "\"KNOWLEDGE_CLASS_COUNT\" INTEGER NOT NULL ," + // 2: knowledge_class_count
                "\"KNOWLEDGE_CLASS_QUALITY\" INTEGER NOT NULL );"); // 3: knowledge_class_quality
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"KNOWLEDGE_CLASS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KnowledgeClass entity) {
        stmt.clearBindings();
 
        Long knowledge_class_id = entity.getKnowledge_class_id();
        if (knowledge_class_id != null) {
            stmt.bindLong(1, knowledge_class_id);
        }
 
        String knowledge_class_name = entity.getKnowledge_class_name();
        if (knowledge_class_name != null) {
            stmt.bindString(2, knowledge_class_name);
        }
        stmt.bindLong(3, entity.getKnowledge_class_count());
        stmt.bindLong(4, entity.getKnowledge_class_quality());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KnowledgeClass entity) {
        stmt.clearBindings();
 
        Long knowledge_class_id = entity.getKnowledge_class_id();
        if (knowledge_class_id != null) {
            stmt.bindLong(1, knowledge_class_id);
        }
 
        String knowledge_class_name = entity.getKnowledge_class_name();
        if (knowledge_class_name != null) {
            stmt.bindString(2, knowledge_class_name);
        }
        stmt.bindLong(3, entity.getKnowledge_class_count());
        stmt.bindLong(4, entity.getKnowledge_class_quality());
    }

    @Override
    protected final void attachEntity(KnowledgeClass entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public KnowledgeClass readEntity(Cursor cursor, int offset) {
        KnowledgeClass entity = new KnowledgeClass( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // knowledge_class_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // knowledge_class_name
            cursor.getInt(offset + 2), // knowledge_class_count
            cursor.getInt(offset + 3) // knowledge_class_quality
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KnowledgeClass entity, int offset) {
        entity.setKnowledge_class_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKnowledge_class_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKnowledge_class_count(cursor.getInt(offset + 2));
        entity.setKnowledge_class_quality(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(KnowledgeClass entity, long rowId) {
        entity.setKnowledge_class_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(KnowledgeClass entity) {
        if(entity != null) {
            return entity.getKnowledge_class_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(KnowledgeClass entity) {
        return entity.getKnowledge_class_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
