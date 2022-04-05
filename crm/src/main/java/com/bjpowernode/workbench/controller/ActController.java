package com.bjpowernode.workbench.controller;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.settings.service.UserService;
import com.bjpowernode.workbench.beans.Act;
import com.bjpowernode.workbench.service.ActService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName:ActController
 * Package:com.bjpowernode.workbench.controller
 * Description: 描述信息
 *
 * @date:2022/3/15 3:58
 * @author:白白白
 */
@Controller
public class ActController {
    @Autowired
    UserService userService;
    @Autowired
    ActService actService;


    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList", userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveAct.do")
    public @ResponseBody
    Object saveAct(Act act, HttpSession session) {
        /*
        id:
        createby:
        createTime:
         */
        act.setId(UUIDUtils.getUUID());
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        act.setCreateBy(user.getName());
        act.setCreateTime(DateUtils.formatDate(new Date()));
        ReturnObject returnObject = new ReturnObject();

        int i = actService.saveAct(act);
        if (i > 0) {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
        } else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试...");
        }
        return returnObject;
    }

    @RequestMapping("workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody
    Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate,
                                           int pageNo, int pageSize) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Act> actList = actService.queryActivityByConditionForPage(map);

        int totalRows = actService.queryCountOfActivityByCondition(map);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("actList", actList);
        hashMap.put("totalRows", totalRows);
        return hashMap;
    }

    @RequestMapping("workbench/activity/deleteActByIds.do")
    public @ResponseBody
    Object deleteActByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        int i = actService.deleteActByIds(id);
        if (i > 0) {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
        } else {
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后再试...");
        }
        return returnObject;
    }


    @RequestMapping("workbench/activity/queryActById.do")
    public @ResponseBody
    Object queryActById(String id) {
        return actService.queryActById(id);
    }

    @RequestMapping("workbench/activity/exportActivityAll.do")
    public void exportActivityAll(HttpServletResponse response) throws IOException {

        List<Act> actList = actService.queryAllAct();
        //创建excel表格文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        if (actList != null && actList.size() > 0) {

            for (int i = 0; i < actList.size(); i++) {
                Act act = actList.get(i);
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(act.getId());
                cell = row.createCell(1);
                cell.setCellValue(act.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(act.getName());
                cell = row.createCell(3);
                cell.setCellValue(act.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(act.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(act.getCost());
                cell = row.createCell(6);
                cell.setCellValue(act.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(act.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(act.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(act.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(act.getEditBy());
            }
        }
        //把生成的excel文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=activityList.xls");
        ServletOutputStream out = response.getOutputStream();

        wb.write(out);
        wb.close();
        out.flush();
    }

    @RequestMapping("workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request) {
        Act act = actService.queryActForDetailByActId(id);


        request.setAttribute("activity",act);

        return "workbench/activity/detail";
    }


}
