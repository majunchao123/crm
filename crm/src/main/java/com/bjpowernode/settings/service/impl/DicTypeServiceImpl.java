package com.bjpowernode.settings.service.impl;

import com.bjpowernode.settings.beans.DicType;
import com.bjpowernode.settings.mapper.DicTypeMapper;
import com.bjpowernode.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:DicTypeServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/15 15:12
 * @author:白白白
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {
    @Autowired
    DicTypeMapper dicTypeMapper;


    public List<DicType> queryAllDicType() {
        return dicTypeMapper.selectAllDicType();
    }
}
