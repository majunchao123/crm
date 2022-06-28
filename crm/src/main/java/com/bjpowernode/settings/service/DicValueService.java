package com.bjpowernode.settings.service;

import com.bjpowernode.settings.beans.DicValue;

import java.util.List;

/**
 * ClassName:DicValueService
 * Package:com.bjpowernode.settings.service
 * Description: 描述信息
 *
 * @date:2022/4/7 21:29
 * @author:白白白
 */
public interface DicValueService {
    List<DicValue> queryDicValueListByTypeCode(String typeCode);
    List<DicValue> queryAllDicValueList();
}
