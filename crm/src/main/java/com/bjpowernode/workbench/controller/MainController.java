package com.bjpowernode.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:MainController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/3/13 22:30
 * @author:白白白
 */
@Controller
public class MainController {



    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }



}
