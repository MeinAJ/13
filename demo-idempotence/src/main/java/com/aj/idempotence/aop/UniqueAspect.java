/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.aop;

import com.aj.idempotence.annotations.UniqueValue;
import com.aj.idempotence.dao.UniqueRecordDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * UniqueAspect
 *
 * @author An Jun
 * @date 2021-05-26
 */
@SuppressWarnings("ALL")
@Aspect
@Component
public class UniqueAspect {

    @Autowired
    private UniqueRecordDao uniqueRecordDao;

    @Pointcut("@annotation(com.aj.idempotence.annotations.Unique)")
    public void annotationPoinCut() {
    }

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            throw new Exception("参数错误");
        }

        Object targetParam = null;

        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = args[i];
            Annotation[] paramAnn = annotations[i];
            if (param == null || paramAnn.length == 0) {
                continue;
            }
            for (Annotation annotation : paramAnn) {
                if (annotation.annotationType().equals(UniqueValue.class)) {
                    targetParam = param;
                    break;
                }
            }
        }

        if (targetParam == null || !(targetParam instanceof String)) {
            throw new Exception("参数错误");
        }

        String requestId = (String) targetParam;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();

        String uniqueValue = getUniqueValue(className, methodName, requestId);
        System.out.println("uniqueValue=" + uniqueValue);
        uniqueRecordDao.insert(uniqueValue);
    }

    private String getUniqueValue(String className, String methodName, String requestId) {
        return "dedup:" + className + "#" + methodName + "#" + requestId;
    }

}
