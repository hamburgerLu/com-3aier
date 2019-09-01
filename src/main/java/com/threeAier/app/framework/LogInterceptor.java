package com.threeAier.app.framework;

import com.threeAier.app.configuration.NeedLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //判断是否登录


        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)handler;
            //当是需要登录的接口的时候
            if(h.getMethodAnnotation(NeedLogin.class)!=null){
                //判断是否登录
                //跳转登录
                HttpSession session=httpServletRequest.getSession();

                if(session.getAttribute("USER")!=null){
                    return true;
                }
//                httpServletRequest.getServletContext().getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
//                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        try {
            String apiName = ((HandlerMethod) handler).getMethod().getName();
            String className = ((HandlerMethod) handler).getMethod().getDeclaringClass().getName();
        } catch (Exception exitException) {

        }
    }
}
