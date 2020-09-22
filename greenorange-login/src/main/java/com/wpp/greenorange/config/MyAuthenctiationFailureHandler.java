package com.wpp.greenorange.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Configuration
public class MyAuthenctiationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("登录失败");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        HashMap<Object, Object> map = new HashMap<>(2);
        map.put("error",true);
        map.put("message",e.getMessage());
        httpServletResponse.getWriter().write(mapper.writeValueAsString(map));
    }
}
