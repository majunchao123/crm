package com.bjpowernode.workbench.service.impl;

import com.bjpowernode.workbench.beans.ClueRemark;
import com.bjpowernode.workbench.mapper.ClueRemarkMapper;
import com.bjpowernode.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ClueRemarkServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/10 10:34
 * @author:白白白
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    ClueRemarkMapper clueRemarkMapper;

    public List<ClueRemark> queryClueRemarkListByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkListByClueId(clueId);
    }

    public int saveClueRemark(ClueRemark clueRemark) {
        return clueRemarkMapper.insert(clueRemark);
    }

    public int deleteClueRemark(String id) {
        return clueRemarkMapper.deleteByPrimaryKey(id);
    }
}
