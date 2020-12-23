package com.mk.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author manki.kim
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD})
public @interface Repeat {

	/**
	 * The number of repetitions.
	 *
	 * @return the number of repetitions; must be greater than zero
	 */
	int value();
}
