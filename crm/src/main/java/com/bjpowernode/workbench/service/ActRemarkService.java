package com.bjpowernode.workbench.service;

import com.bjpowernode.workbench.beans.ActRemark;

import java.util.List;

/**
 * ClassName:ActRemarkService
 * Package:com.bjpowernode.workbench.service
 * Description: 描述信息
 *
 * @date:2022/4/6 9:32
 * @author:白白白
 */
public interface ActRemarkService {
    List<ActRemark> queryActListByActId(String id);


    int saveActivityRemark(ActRemark actRemark);

    int deleteActRemarkById(String id);

    int saveActRemark(ActRemark remark);

    ActRemark selectActRemarkById(String id);

}
