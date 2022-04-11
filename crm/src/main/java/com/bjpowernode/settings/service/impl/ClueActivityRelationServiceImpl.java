package com.bjpowernode.settings.service.impl;

import com.bjpowernode.workbench.beans.ClueActivityRelation;
import com.bjpowernode.workbench.mapper.ClueActivityRelationMapper;
import com.bjpowernode.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ClueActivityRelationServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/11 16:06
 * @author:白白白
 */

@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;

    public int saveClueActivityRelationByList(List<ClueActivityRelation> list) {

        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }
}
