/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.commons.annotations.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 
 **/
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CsvSerializer {

	public String value() default "toString";

}
