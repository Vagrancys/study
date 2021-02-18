package com.vagrancy.study.model.mine.request;

import com.vagrancy.study.model.knowledge.request.KnowLedgeRequest;
import com.vagrancy.study.utils.DaoUtilsStore;

/**
 * @author Vagrancy
 * @date 2021/2/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 数据请求类
 */
public class DataRequest {
    private static DataRequest instance;
    private DataRequest(){

    }

    public static DataRequest getInstance(){
        if(instance == null){
            synchronized (DataRequest.class){
                if(instance == null){
                    instance = new DataRequest();
                }
            }
        }
        return instance;
    }
}
