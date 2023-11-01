package com.example.evaluation.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.evaluation.domain.Result;
import com.example.evaluation.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(Result.error(HttpStatus.UNAUTHORIZED.value(), "权限不足"));
        WebUtils.renderString(response,json);
    }
}
