package com.bjpowernode.settings.controller;

import com.bjpowernode.settings.beans.DicValue;
import com.bjpowernode.settings.mapper.DicValueMapper;
import com.bjpowernode.settings.service.DicTypeService;
import com.bjpowernode.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName:DicValueController
 * Package:com.bjpowernode.settings.controller
 * Description: 描述信息
 *
 * @date:2022/4/15 11:37
 * @author:白白白
 */
@Controller
public class DicValueController {
    @Autowired
    DicValueService dicValueService;


    @RequestMapping("settings/dictionary/value/index.do")
    public String index(HttpServletRequest request){

        List<DicValue> dicValueList = dicValueService.queryAllDicValueList();
        request.setAttribute("dicValueList",dicValueList);
        return "settings/dictionary/value/index";
    }
}
