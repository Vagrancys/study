package com.vagrancy.study.presenter.knowledge;

import android.util.Log;

import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.model.knowledge.entity.KnowledgeClass;
import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.module.knowledge.view.KnowledgeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识控制类
 */
public class KnowledgePresenter extends BasePresenter<Knowledge,KnowledgeView<Knowledge>> {
    private KnowLedgeRequest knowLedgeRequest;
    private static KnowledgeView<Knowledge> mView;
    public KnowledgePresenter(){
        knowLedgeRequest = KnowLedgeRequest.getInstance();
    }

    @Override
    public void bindView(KnowledgeView<Knowledge> view) {
        mView = view;
        if(mView == null){
            Log.e(KnowledgePresenter.class.getSimpleName(),"no bindView");
        }else{
            Log.e(KnowledgePresenter.class.getSimpleName(),"yes bindView");
        }
    }

    /**
     * 添加知识
     * @param object
     */
    @Override
    public void insert(Knowledge object) {
        boolean result = knowLedgeRequest.insertKnowledge(object);
        if(result){
            mView.onSuccess(R.string.knowledge_save_success);
        }else{
            mView.onFail(R.string.knowledge_save_fail);
        }
        mView.onFinish();
    }

    /**
     * 删除知识
     * @param object
     */
    @Override
    public void delete(Knowledge object) {
        boolean result = knowLedgeRequest.deleteKnowledge(object);
        if(result){
            mView.onSuccess(R.string.knowledge_delete_success);
        }else{
            mView.onFail(R.string.knowledge_delete_fail);
        }
        mView.onFinish();
    }

    /**
     * 查询单个知识
     * @param key
     */
    @Override
    public void query(long key) {
        Knowledge knowledge = knowLedgeRequest.query(key);
        if(knowledge == null ){
            mView.onFail(R.string.knowledge_query_error);
        }else{
            mView.onSuccess(knowledge);
        }
        mView.onFinish();
    }

    @Override
    public void update(Knowledge object) {
        boolean result = knowLedgeRequest.update(object);
        if(result){
            mView.onSuccess(R.string.knowledge_update_success);
        }else{
            mView.onFail(R.string.knowledge_update_fail);
        }
        mView.onFinish();
    }

    /**
     * 查询所有的知识
     */
    public void queryAll() {
        List<Knowledge> knowledge = knowLedgeRequest.queryAll();
        if(knowledge == null){
            mView.onError(R.string.knowledge_query_error);
        }else if(knowledge.size() <=0){
            mView.onFail(R.string.knowledge_query_empty);
        }else{
            mView.onSuccess(knowledge);
        }
        mView.onFinish();
    }

    /**
     * 查询所有分组
     */
    public void queryGroupAll() {
        List<KnowledgeClass> knowledgeClasses = knowLedgeRequest.queryGroupAll();
        if(knowledgeClasses == null){
            mView.onError(R.string.knowledge_query_error);
        }else if(knowledgeClasses.size() <= 0){
            mView.onFail(R.string.knowledge_query_empty);
        }else {
            mView.queryKnowledgeClass(knowledgeClasses);
            initChildKnowledge(knowledgeClasses);
        }
        mView.onFinish();
    }

    //初始化分组知识
    private void initChildKnowledge(List<KnowledgeClass> knowledgeClasses) {
        List<List<Knowledge>> knowledge = new ArrayList<>();
        for (KnowledgeClass knowledgeClass : knowledgeClasses) {
            List<Knowledge> knowledgeList = knowLedgeRequest.queryByClassIdAll(knowledgeClass.getKnowledge_class_id());
            if(knowledgeList != null && knowledgeList.size() > 0){
                knowledge.add(knowledgeList);
            }
        }
        if(knowledge.size() > 0){
            mView.queryChildKnowLedge(knowledge);
        }
    }

    /**
     *  添加知识分类
     * @param knowledgeClass
     */
    public void insertKnowLedgeClass(KnowledgeClass knowledgeClass) {
        boolean result = knowLedgeRequest.insertKnowledgeClass(knowledgeClass);
        if(result){
            mView.onSuccess(R.string.knowledge_insert_success);
        }else{
            mView.onFail(R.string.knowledge_insert_fail);
        }
        mView.onFinish();
    }

    /**
     * 删除知识分类
     * @param knowledgeClass
     */
    public void deleteKnowledgeClass(KnowledgeClass knowledgeClass) {
        boolean result = knowLedgeRequest.deleteKnowledgeClass(knowledgeClass);
        if(result){
            mView.onSuccess(R.string.knowledge_delete_success);
        }else{
            mView.onFail(R.string.knowledge_delete_fail);
        }
        mView.onFinish();
    }

    /**
     * 更新知识分类
     * @param knowledgeClass
     */
    public void updateKnowledgeClass(KnowledgeClass knowledgeClass) {
        boolean result = knowLedgeRequest.updateKnowledgeClass(knowledgeClass);
        if(result){
            mView.onSuccess(R.string.knowledge_update_success);
        }else{
            mView.onFail(R.string.knowledge_update_fail);
        }
        mView.onFinish();
    }

}
