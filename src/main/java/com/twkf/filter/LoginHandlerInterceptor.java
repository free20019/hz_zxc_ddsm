package com.twkf.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/4/29 - 11:29
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String user = (String) request.getSession().getAttribute("username");
        String contextPath = request.getRequestURL().toString();
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin==null?"*":origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
//        if(null == user && !method.toUpperCase().equals("OPTIONS")){
//            response.sendRedirect("../");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
