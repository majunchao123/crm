package com.bjpowernode.workbench.controller;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.workbench.beans.ClueRemark;
import com.bjpowernode.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:ClueRemarkController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/4/10 16:14
 * @author:白白白
 */
@Controller
public class ClueRemarkController {

    @Autowired
    ClueRemarkService clueRemarkService;



    @RequestMapping("workbench/clue/saveClueRemark.do")
    @ResponseBody
    public Object saveClueRemark(String noteContent, String clueId, HttpSession session){
        ClueRemark clueRemark = new ClueRemark();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        clueRemark.setNoteContent(noteContent);
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setClueId(clueId);
        clueRemark.setCreateBy(user.getName());
        clueRemark.setCreateTime(DateUtils.formatDateAndTime(new Date()));
        clueRemark.setEditFlag(Constant.EDIT_FLAG_NO);
        ReturnObject returnObject = new ReturnObject();


        int i = clueRemarkService.saveClueRemark(clueRemark);
        if (i>0){
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
            returnObject.setOtherData(clueRemark);
        }else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,稍后再试");
        }

        return returnObject;

    }


    @RequestMapping("workbench/clue/deleteClueRemark.do")
    @ResponseBody
    public Object deleteClueRemark(String id){

        ReturnObject returnObject = new ReturnObject();
        int i = clueRemarkService.deleteClueRemark(id);

        if (i>0){
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);

        }else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,稍后再试");
        }

        return returnObject;
    }



}
