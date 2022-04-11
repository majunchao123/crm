package com.bjpowernode.workbench.service;

import com.bjpowernode.workbench.beans.ClueActivityRelation;

import java.util.List;

/**
 * ClassName:ClueActivityRelationService
 * Package:com.bjpowernode.workbench.service
 * Description: 描述信息
 *
 * @date:2022/4/11 16:05
 * @author:白白白
 */
public interface ClueActivityRelationService {
    int saveClueActivityRelationByList(List<ClueActivityRelation> list);
}
