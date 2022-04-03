package com.bjpowernode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:IndexController
 * Package:com.bjpowernode.web.controller
 * Description: 描述信息
 *
 * @date:2022/3/10 9:14
 * @author:白白白
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String indexView() {
        return "index";
    }

}
