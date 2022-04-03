package com.bjpowernode.workbench.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:IndexController
 * Package:com.bjpowernode.workbench.web
 * Description: 描述信息
 *
 * @date:2022/3/13 9:17
 * @author:白白白
 */
@Controller
public class WorkbenchIndexController {

    @RequestMapping("workbench/login.do")
    public String indexView(){
        return "workbench/index";
    }


}
