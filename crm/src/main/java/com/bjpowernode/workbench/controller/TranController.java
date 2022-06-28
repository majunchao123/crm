package com.bjpowernode.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:TranController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/4/15 11:20
 * @author:白白白
 */
@Controller
public class TranController {

    @RequestMapping("workbench/transaction/index.do")
    public String indexDo(){
        return "workbench/transaction/index";
    }

}
