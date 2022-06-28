package com.bjpowernode.settings.interceptor;

import com.bjpowernode.commons.constants.Constant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginInterceptor
 * Package:com.bjpowernode.settings.interceptor
 * Description: 拦截器拦截异常访问其他页面
 *
 * @date:2022/3/15 2:53
 * @author:白白白
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(Constant.SESSION_USER);
        if (attribute==null){
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }
}
