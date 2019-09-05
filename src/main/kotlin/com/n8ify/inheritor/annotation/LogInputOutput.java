package com.n8ify.inheritor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a controller's api interface class for logging input, output and time usage detail.
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogInputOutput {
    boolean asJsonInput() default false;
    boolean asJsonOutput() default false;
}