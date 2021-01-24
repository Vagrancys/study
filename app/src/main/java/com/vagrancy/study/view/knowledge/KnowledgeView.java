package com.vagrancy.study.view.knowledge;

import com.vagrancy.study.common.contract.knowledge.KnowledgeContract;
import com.vagrancy.study.model.knowledge.entity.Knowledge;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2021/1/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class KnowledgeView implements KnowledgeContract.View<Knowledge> {
    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Knowledge object) {

    }

    @Override
    public void onSuccess(int message) {

    }

    @Override
    public void onFail(int message) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(int message) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onSuccess(List<Knowledge> object) {

    }
}
