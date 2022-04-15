package com.bjpowernode.workbench.controller;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.DicValue;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.settings.service.DicValueService;
import com.bjpowernode.settings.service.UserService;
import com.bjpowernode.workbench.beans.*;
import com.bjpowernode.workbench.service.ActService;
import com.bjpowernode.workbench.service.ClueActivityRelationService;
import com.bjpowernode.workbench.service.ClueRemarkService;
import com.bjpowernode.workbench.service.ClueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.ElementType;
import java.util.*;

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
    @Autowired
    ClueRemarkService clueRemarkService;
    @Autowired
    ActService actService;
    @Autowired
    ClueActivityRelationService clueActivityRelationService;


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

    @RequestMapping("workbench/clue/detailClueIndex")
    public String detailClueIndex(HttpServletRequest request,@RequestParam("id") String clueId){
        //查出clue详细信息根据id
        Clue clue = clueService.queryClueDetailById(clueId);
        request.setAttribute("clue",clue);
        //查出关于clueRemarkList
        List<ClueRemark> remarkList = clueRemarkService.queryClueRemarkListByClueId(clueId);
        request.setAttribute("remarkList",remarkList);
        //查出关联的市场活动
        List<Act> actList = actService.queryActListByClueId(clueId);
        request.setAttribute("actList",actList);
        return "workbench/clue/detail";
    }

    @RequestMapping("workbench/clue/queryActivityForDetailByNameClueId.do")
    @ResponseBody
    public Object queryActivityForDetailByNameClueId(String activityName,String clueId){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        List<Act> actList = actService.queryActivityForDetailByNameClueId(map);
        return actList;
    }

    @RequestMapping("workbench/clue/saveBoundActClueRelation.do")
    @ResponseBody
    public Object saveBoundActClueRelation(String[] activityId,String clueId){
        //封装参数
        ArrayList<ClueActivityRelation> list = new ArrayList<ClueActivityRelation>();
        ClueActivityRelation clueActivityRelation = null;
        for (String id :
                activityId) {
            clueActivityRelation = new ClueActivityRelation();

            clueActivityRelation.setId(UUIDUtils.getUUID());
            clueActivityRelation.setActivityId(id);
            clueActivityRelation.setClueId(clueId);
            list.add(clueActivityRelation);
        }
        ReturnObject returnObject = new ReturnObject();

        int i = clueActivityRelationService.saveClueActivityRelationByList(list);
        if (i>0){
            List<Act> actList = actService.QueryActListByIds(activityId);
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
            returnObject.setOtherData(actList);
        }else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setOtherData("系统繁忙，稍后再试");
        }
        return returnObject;
    }
    @RequestMapping("workbench/clue/convertTo.do")
    public Object convertTo(String id,HttpServletRequest request){
        Clue clue = clueService.queryClueDetailById(id);
        List<DicValue> valueList = dicValueService.queryDicValueListByTypeCode("stage");
        request.setAttribute("clue",clue);
        request.setAttribute("valueList",valueList);

        return "workbench/clue/convert";
    }

    @RequestMapping("workbench/clue/queryIncludeActivityByNameClueId.do")
    @ResponseBody
    public Object queryIncludeActivityByNameClueId(String activityName,String clueId){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        List<Act> actList = actService.queryIncludeActivityForDetailByNameClueId(map);
        return actList;
    }

    @RequestMapping("workbench/clue/convertClue.do")
    @ResponseBody
    public Object convertClue(String clueId,
                              String money,String name,
                              String expectedDate,
                              String stage,String activityId,String isCreateTran,HttpSession session){


        //封装参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Constant.SESSION_USER,session.getAttribute(Constant.SESSION_USER));
        ReturnObject returnObject = new ReturnObject();
        try {
            clueService.saveConvertClue(map);
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，稍后再试");
        }
        return returnObject;

    }





}
