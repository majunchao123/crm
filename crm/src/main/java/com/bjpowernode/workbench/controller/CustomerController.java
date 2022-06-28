package com.bjpowernode.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:CustomerController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/4/12 16:53
 * @author:白白白
 */
@Controller
public class CustomerController {

    @RequestMapping("workbench/customer/index.do")
    public String indexView(){
        return "workbench/customer/index";
    }
}
