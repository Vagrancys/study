package com.vagrancy.study.model.knowledge.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

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
    private Long knowledge_advanced_id;
    //知识的id索引
    private long knowledge_kid;
}
