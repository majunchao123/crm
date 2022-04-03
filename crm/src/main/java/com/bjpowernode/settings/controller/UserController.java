package com.bjpowernode.settings.controller;
import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.domain.ReturnObject;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.settings.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:UserController
 * Package:com.bjpowernode.web.controller
 * Description: 描述信息
 *
 * @date:2022/3/10 9:22
 * @author:白白白
 */
@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("settings/qx/user/tologin.do")
    public String Login() {
        return "settings/qx/user/login";
    }

    @RequestMapping("settings/qx/user/login.do")
    @ResponseBody
    public Object LoginTo(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("loginAct",loginAct);
        hashMap.put("loginPwd",loginPwd);
        User user = userService.queryUserByLoginActAndLoginPwd(hashMap);
        ReturnObject returnObject = new ReturnObject();

        if (user == null) {
            //用户不存在 返回失败
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户不存在");

        } else {
            if (DateUtils.formatDate(new Date()).compareTo(user.getExpireTime()) > 0) {
                //用户到期，返回失败
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户到期");

            } else if (Constant.RETURN_OBJECT_CODE_FAIL.equals(user.getLockState())) {
                //用户锁定
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户锁定");

            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //用户ip不正确
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户ip不正确");
            } else {
                if ("true".equals(isRemPwd)){
                    Cookie cookie1 = new Cookie("loginAct", user.getLoginAct());
                    cookie1.setMaxAge(10*24*60*60);
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("loginPwd", user.getLoginPwd());
                    cookie2.setMaxAge(10*24*60*60);
                    response.addCookie(cookie2);

                }else{
                    Cookie cookie1 = new Cookie("loginAct", "1");
                    cookie1.setMaxAge(0);
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("loginPwd", "1");
                    cookie2.setMaxAge(0);
                    response.addCookie(cookie2);
                }

                session.setAttribute(Constant.SESSION_USER,user);
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUC);
            }
        }
        return returnObject;
    }

    @RequestMapping("settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //销毁所有的cookie
        Cookie cookie1 = new Cookie("loginAct", "1");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("loginPwd", "1");
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);
        //销毁所有的session
        session.invalidate();
        return "redirect:/";
    }


}
