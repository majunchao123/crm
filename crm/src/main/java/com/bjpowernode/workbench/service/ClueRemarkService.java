package com.bjpowernode.workbench.service;

import com.bjpowernode.workbench.beans.ClueRemark;

import java.util.List;

/**
 * ClassName:ClueRemarkService
 * Package:com.bjpowernode.workbench.service
 * Description: 描述信息
 *
 * @date:2022/4/10 10:16
 * @author:白白白
 */
public interface ClueRemarkService {

    List<ClueRemark> queryClueRemarkListByClueId(String clueId);

    int saveClueRemark(ClueRemark clueRemark);

    int deleteClueRemark(String id);



}
