package com.test.commons;

import org.junit.Test;


public class ValidateTest {
	@Test(expected=IllegalArgumentException.class)
	public void nullValueShouldThrowException() {
		Validate.notNull(null);
	}
	
	@Test()
	public void nonNullValueShouldNotThrowException() {
		Validate.notNull(anyNotNullObject());
	}

	private Object anyNotNullObject() {
		return "any not null object";
	}
}
