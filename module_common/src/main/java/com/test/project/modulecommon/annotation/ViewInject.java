package com.test.project.modulecommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/16 11:45
 * Modify by
 * Modification date：
 * Modify content：
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {

    int value();

    //view parentId
    int parentId() default 0;
}
