package com.bjpowernode.settings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:SettingController
 * Package:com.bjpowernode.settings.controller
 * Description: 描述信息
 *
 * @date:2022/3/15 14:10
 * @author:白白白
 */
@Controller
public class SettingController {

    @RequestMapping("settings/index.do")
    public String index() {
        return "settings/index";
    }
}
