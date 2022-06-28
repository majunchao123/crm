package com.bjpowernode.settings.controller;

import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.settings.beans.DicType;
import com.bjpowernode.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName:DicTypeController
 * Package:com.bjpowernode.settings.controller
 * Description: 描述信息
 *
 * @date:2022/4/15 11:37
 * @author:白白白
 */
@Controller
public class DicTypeController {
    @Autowired
    DicTypeService dicTypeService;


    @RequestMapping("settings/dictionary/type/index.do")
    public String index(){
        return "settings/dictionary/type/index";
    }



    @RequestMapping("settings/dictionary/type/queryDicType.do")
    @ResponseBody
    public Object queryDicType(){
        ReturnObject returnObject = new ReturnObject();


        List<DicType> dicTypeList = dicTypeService.queryAllDicType();
        return dicTypeList;
    }
}
