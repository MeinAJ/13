/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.aj.idempotence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface CheckIdempotence {

}
