package com.test.project.modulecommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author    zhouchuan
 * Describe：检测网络
 * Data:      2019/10/16 15:38
 * Modify by
 * Modification date：
 * Modify content：
 */

//@Target(ElementType.METHOD)代表annotation的位置， FIELD属性  TYPE类上 CONSTRUCTOR构造函数上
//@Retention(RetentionPolicy.RUNTIME)什么时候生效， CLASS编译时  RUNTIME运行时 SOURCE源码
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNet {
    String value() default "网络未连接！";
}
