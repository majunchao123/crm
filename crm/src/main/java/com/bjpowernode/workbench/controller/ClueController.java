package com.bjpowernode.workbench.controller;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.DicValue;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.settings.service.DicValueService;
import com.bjpowernode.settings.service.UserService;
import com.bjpowernode.workbench.beans.Clue;
import com.bjpowernode.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    UserService userService;
    @Autowired
    DicValueService dicValueService;
    @Autowired
    ClueService clueService;


    @RequestMapping("workbench/clue/index.do")
    public String indexView(HttpServletRequest request) {
        List<User> userList = userService.queryAllUsers();
        List<DicValue> appellation = dicValueService.queryDicValueListByTypeCode("appellation");
        List<DicValue> source = dicValueService.queryDicValueListByTypeCode("source");
        List<DicValue> clueState = dicValueService.queryDicValueListByTypeCode("clueState");
        request.setAttribute("userList", userList);
        request.setAttribute("appellation", appellation);
        request.setAttribute("source", source);
        request.setAttribute("clueState", clueState);
        return "workbench/clue/index";
    }

    @RequestMapping("workbench/clue/saveClue.do")
    @ResponseBody
    public Object saveClueDo(Clue clue, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        session.getAttribute(Constant.SESSION_USER);
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(user.getName());
        clue.setCreateTime(DateUtils.formatDateAndTime(new Date()));
        int i = clueService.saveClue(clue);
        if (i > 0) {
            returnObject.setCode("1");
        } else {
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙，稍后再试");
        }
        return returnObject;
    }


    @RequestMapping("workbench/clue/queryClueForPage.do")
    @ResponseBody
    public Object queryClueForPage(  String fullname,
                                     String company,
                                     String mphone,
                                     String state,
                                     String source,
                                     String phone,
                                     String owner,
                                     int pageNo,
                                     int pageSize)
    {
        //封装参数
        Map<String,Object> map=new HashMap();
        map.put("fullname",fullname);
        map.put("company",company);
        map.put("mphone",mphone);
        map.put("state",state);
        map.put("source",source);
        map.put("phone",phone);
        map.put("owner",owner);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        //查询数据库 获取数据
        List<Clue> clueList = clueService.queryClueListByPage(map);
        //获取总行数
        int totalRows = clueService.queryCountClueListByPage(map);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("clueList",clueList);
        hashMap.put("totalRows",totalRows);
        return hashMap;
    }


}
