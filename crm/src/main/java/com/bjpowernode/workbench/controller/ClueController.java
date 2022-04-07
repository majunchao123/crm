package com.bjpowernode.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:ClueController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/4/7 11:57
 * @author:白白白
 */
@Controller
public class ClueController {



    @RequestMapping("workbench/clue/index.do")
    public String indexView(){
        return "workbench/clue/index";
    }
}
