package com.wpp.greenorange.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 处理403，用户权限不足的返回
 * @author 吴鹏鹏ppp
 */
@Component
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("403");
        String ajaxHeader = httpServletRequest.getHeader("X-Requested-With");
        String XMLHttpRequest = "XMLHttpRequest";
        if (XMLHttpRequest.equals(ajaxHeader)){
            //ajax请求
            httpServletResponse.setContentType("application/json; charset=utf-8");
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("error",true);
            map.put("message","您的权限不足");
        }else{
            //非ajax请求,跳转页面
            super.handle(httpServletRequest, httpServletResponse, e);
//            httpServletRequest.getRequestDispatcher("/error/403.html").forward(httpServletRequest,httpServletResponse);
        }
    }
}
