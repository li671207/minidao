package com.lihail.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 执行新增、修改、删除操作
 * @author lihl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Modify {

}
