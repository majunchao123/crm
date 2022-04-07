package com.bjpowernode.workbench.controller;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.workbench.beans.ActRemark;
import com.bjpowernode.workbench.service.ActRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:ActRemarkController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/4/6 10:35
 * @author:白白白
 */
@Controller
public class ActRemarkController {
    @Autowired
    ActRemarkService actRemarkService;

    @RequestMapping("workbench/activity/saveCreateActivityRemark.do")
    @ResponseBody
    public Object saveCreateActivityRemark(String noteContent,
                                           String activityId,
                                           HttpSession session)
    {
        User user = (User) session.getAttribute(Constant.SESSION_USER);

        ActRemark actRemark = new ActRemark();

        actRemark.setActivityId(activityId);
        actRemark.setCreateBy(user.getName());
        actRemark.setCreateTime(DateUtils.formatDateAndTime(new Date()));
        actRemark.setId(UUIDUtils.getUUID());
        actRemark.setEditFlag(Constant.EDIT_FLAG_NO);
        actRemark.setNoteContent(noteContent);


        ReturnObject returnObject = new ReturnObject();
        int i = actRemarkService.saveActivityRemark(actRemark);
        if (i>0){
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
            returnObject.setOtherData(actRemark);
        }else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试");
        }
        return returnObject;
    }

    @RequestMapping("workbench/activity/deleteActRemark.do")
    @ResponseBody
    public Object deleteActRemark(String id){
        int i = actRemarkService.deleteActRemarkById(id);
        ReturnObject returnObject = new ReturnObject();
        if (i>0){
            returnObject.setCode("1");
        }else {
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙，稍后再试");
        }
        return returnObject;
    }

    @RequestMapping("workbench/activity/saveEditActivityRemark.do")
    @ResponseBody
    public Object saveEditActivityRemark(String id ,String noteContent,HttpSession session){
        ActRemark actRemark = actRemarkService.selectActRemarkById(id);
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        actRemark.setNoteContent(noteContent);
        actRemark.setEditFlag("1");
        actRemark.setEditBy(user.getName());
        actRemark.setEditTime(DateUtils.formatDateAndTime(new Date()));
        int i = actRemarkService.saveActRemark(actRemark);
        if (i>0){
            returnObject.setCode("1");
            returnObject.setOtherData(actRemark);
        }else {
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙，稍后再试");
        }
        return returnObject;
    }
}
