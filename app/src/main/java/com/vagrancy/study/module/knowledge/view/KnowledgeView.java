package com.vagrancy.study.module.knowledge.view;

import com.vagrancy.study.common.base.BaseModelView;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class KnowledgeView<O> extends BaseModelView<O> {
    //获取知识分组
    public void queryKnowledgeClass(List<KnowledgeClass> knowledgeClasses){

    };

    //获取子数组知识
    public void queryChildKnowLedge(List<List<Knowledge>> knowledge){

    }
}
