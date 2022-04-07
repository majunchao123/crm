package com.bjpowernode.workbench.service.impl;

import com.bjpowernode.workbench.beans.ActRemark;
import com.bjpowernode.workbench.mapper.ActRemarkMapper;
import com.bjpowernode.workbench.service.ActRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ActRemarkServiceImpl
 * Package:com.bjpowernode.workbench.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/6 9:35
 * @author:白白白
 */
@Service
public class ActRemarkServiceImpl implements ActRemarkService {
    @Autowired
    ActRemarkMapper actRemarkMapper;

    public List<ActRemark> queryActListByActId(String id) {
        return actRemarkMapper.selectActListByActId(id);
    }

    public int saveActivityRemark(ActRemark actRemark) {
        return actRemarkMapper.insert(actRemark);
    }

    public int deleteActRemarkById(String id) {
        return actRemarkMapper.deleteActRemarkById(id);
    }

    public int saveActRemark(ActRemark remark) {
        return actRemarkMapper.updateActRemark(remark);
    }

    public ActRemark selectActRemarkById(String id) {
        return actRemarkMapper.selectByPrimaryKey(id);
    }


}
