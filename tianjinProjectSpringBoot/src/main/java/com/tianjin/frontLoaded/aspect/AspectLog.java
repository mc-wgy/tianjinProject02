package com.tianjin.frontLoaded.aspect;

import java.lang.annotation.*;

/**
 * @Author RainRAY
 * @Date 2022/8/26
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLog {
}
