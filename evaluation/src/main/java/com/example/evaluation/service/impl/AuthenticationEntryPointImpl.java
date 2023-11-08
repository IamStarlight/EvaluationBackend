package com.example.evaluation.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.evaluation.entity.Result;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String json = JSON.toJSONString(Result.error(HttpStatus.UNAUTHORIZED.value(),"密码错误请重新登录"));
        WebUtils.renderString(response,json);
//        throw new ServiceException(HttpStatus.UNAUTHORIZED.value(),"认证失败请重新登录");
    }
}
