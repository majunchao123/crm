package com.bjpowernode.settings.service.impl;

import com.bjpowernode.settings.beans.DicValue;
import com.bjpowernode.settings.mapper.DicValueMapper;
import com.bjpowernode.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:DicValueServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/7 21:30
 * @author:白白白
 */
@Service
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    DicValueMapper dicValueMapper;



    public List<DicValue> queryDicValueListByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueListByTypeCode(typeCode);
    }

    public List<DicValue> queryAllDicValueList() {
        return dicValueMapper.selectAllDicValueList();
    }
}
