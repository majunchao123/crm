package com.bjpowernode.workbench.service;

import com.bjpowernode.workbench.beans.Act;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ActService
 * Package:com.bjpowernode.workbench.service
 * Description: 描述信息
 *
 * @date:2022/3/15 22:05
 * @author:白白白
 */


public interface ActService {
    int saveAct(Act act);

    List<Act> queryActivityByConditionForPage(Map<String,Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    List<Act> queryAllAct();

    int deleteActByIds(String[] ids);

    Act queryActById(String id);

    Act queryActForDetailByActId(String id);

    int saveEditAct(Act act);

    List<Act> queryActListByClueId(String clueId);

    List<Act> queryActivityForDetailByNameClueId(Map map);
    List<Act> QueryActListByIds(String[] id);

    List<Act> queryIncludeActivityForDetailByNameClueId(Map map);


}
