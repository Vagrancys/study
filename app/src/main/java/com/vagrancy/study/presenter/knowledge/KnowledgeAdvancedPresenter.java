package com.vagrancy.study.presenter.knowledge;

import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.knowledge.KnowledgeAdvancedContract;
import com.vagrancy.study.model.knowledge.KnowledgeAdvancedModel;
import com.vagrancy.study.model.knowledge.entity.KnowledgeAdvanced;
import com.vagrancy.study.module.knowledge.activity.KnowledgeAdvancedActivity;

/**
 * @author Vagrancy
 * @date 2021/1/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识进阶presenter层
 */
public class KnowledgeAdvancedPresenter extends BasePresenter<KnowledgeAdvancedActivity, KnowledgeAdvancedModel, KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced>> {
    @Override
    public KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced> getContract() {
        return new KnowledgeAdvancedContract.Presenter<KnowledgeAdvanced>() {
            @Override
            public void responseResult(KnowledgeAdvanced object) {

            }
        };
    }

    @Override
    public KnowledgeAdvancedModel getModel() {
        return new KnowledgeAdvancedModel(this);
    }
}
