package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedEditContract;
import com.vagrancy.study.model.knowledge.KnowledgeAdvancedEditModel;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.knowledge.activity.KnowledgeAdvancedEditActivity;

/**
 * @author Vagrancy
 * @date 2021/2/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶编辑presenter
 */
public class KnowledgeAdvancedEditPresenter extends BasePresenter<KnowledgeAdvancedEditActivity, KnowledgeAdvancedEditModel, KnowledgeAdvancedEditContract.Presenter<Knowledge>> {
    @Override
    public KnowledgeAdvancedEditContract.Presenter<Knowledge> getContract() {
        return new KnowledgeAdvancedEditContract.Presenter<Knowledge>() {
            @Override
            public void responseResult(Knowledge object) {

            }
        };
    }

    @Override
    public KnowledgeAdvancedEditModel getModel() {
        return new KnowledgeAdvancedEditModel(this);
    }
}
