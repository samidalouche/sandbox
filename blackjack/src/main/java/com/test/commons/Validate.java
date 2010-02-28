package com.test.commons;

/**
 * To replace commons-lang Validate class, since the use of third party libs is not allowed
 */
public class Validate {
	public static void notNull(Object o) {
		if(o == null) {
			throw new IllegalArgumentException("Cannot be null");
		}
	}
}
