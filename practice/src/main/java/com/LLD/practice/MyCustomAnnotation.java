package com.LLD.practice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyCustomAnnotation {

	String name(); // Field name
    int length() default 255; // Max length (default 255)
    boolean required() default false; // Required field or not
}
