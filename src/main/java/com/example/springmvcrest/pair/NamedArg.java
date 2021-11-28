package com.example.springmvcrest.pair;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(PARAMETER)
public @interface NamedArg {
    public String value();

    public String defaultValue() default "";
}
