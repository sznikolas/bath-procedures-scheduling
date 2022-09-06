package com.bathproceduresscheduling.web.config;

import java.lang.annotation.*;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
	String NONE = "none"; // no layout will be used

	String value();
}
