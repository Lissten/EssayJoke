package com.test.project.modulecommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/9 16:04
 * Modify by
 * Modification date：
 * Modify content：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {

}
