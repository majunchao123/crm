package com.bjpowernode.workbench.service.impl;

import com.bjpowernode.workbench.beans.Act;
import com.bjpowernode.workbench.mapper.ActMapper;
import com.bjpowernode.workbench.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ActServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/3/15 22:06
 * @author:白白白
 */

@Service
public class ActServiceImpl implements ActService {
    @Autowired
    ActMapper actMapper;

    public int saveAct(Act act) {
        return actMapper.insertAct(act);
    }

    public List<Act> queryActivityByConditionForPage(Map<String, Object> map) {
        return actMapper.selectActivityByConditionForPage(map);
    }

    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return actMapper.selectCountOfActivityByCondition(map);
    }

    public List<Act> queryAllAct() {
        return actMapper.selectAllAct();
    }

    public int deleteActByIds(String[] ids) {
        return actMapper.deleteActByIds(ids);
    }

    public Act queryActById(String id) {
        return actMapper.selectByPrimaryKey(id);
    }

    public Act queryActForDetailByActId(String id) {
        return actMapper.selectActForDetailByActId(id);
    }

    public int saveEditAct(Act act) {
        return actMapper.updateAct(act);
    }

    public List<Act> queryActListByClueId(String clueId) {
        return actMapper.selectActListByClueId(clueId);
    }

    public List<Act> queryActivityForDetailByNameClueId(Map map) {
        return actMapper.selectActivityForDetailByNameClueId(map);
    }

    public List<Act> QueryActListByIds(String[] id) {
        return actMapper.selectActListByIds(id);
    }
}
