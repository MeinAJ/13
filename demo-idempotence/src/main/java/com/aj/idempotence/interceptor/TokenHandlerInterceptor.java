/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.interceptor;

import com.aj.idempotence.annotations.CheckIdempotence;
import com.aj.idempotence.service.TokenService;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * TokenHandlerInterceptor
 *
 * @author An Jun
 * @date 2021-04-30
 */
@SuppressWarnings("ALL")
public class TokenHandlerInterceptor implements HandlerInterceptor {

    private TokenService tokenService;

    public TokenHandlerInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            CheckIdempotence annotation = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), CheckIdempotence.class);
            if (Objects.nonNull(annotation)) {
                String token = request.getHeader("token");
                if (StringUtils.isEmpty(token)) {
                    throw new Exception("无token");
                }
                boolean tokenIsOk = tokenService.tokenIsOk(token);
                if (!tokenIsOk) {
                    throw new Exception("token无效");
                }
                System.out.println("token有效,time=" + LocalDateTime.now());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        //do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        //do nothing
    }

}
