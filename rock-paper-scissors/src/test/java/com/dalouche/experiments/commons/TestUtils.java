package com.dalouche.experiments.commons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class TestUtils {
	public static <T> void equalShouldBeReflexive(T o) {
		assertThat(o, is(equalTo(o)));
	}
	
	public static <T> void shouldNotEqualNull(T o) {
		assertFalse(o.equals(null));
		//assertThat(o, is(not(equalTo(null))));
	}
	
	public static <T> void shouldNotEqualObjectOfDifferentType(T o) {
		assertThat(o, is(not(equalTo(anyObjectOfUnkownType()))));
	}

	public static <T> void shouldBeEqualAndHaveSameHashCode(T o1, T o2) {
		assertThat(o1, is(equalTo(o2)));
		assertThat(o1.hashCode(), is(equalTo(o2.hashCode())));
	}
	
	public static <T> void shouldNotBeEqualAndHaveDifferentHashCode(T o1, T o2) {
		assertThat(o1, is(not(equalTo(o2))));
		assertThat(o1.hashCode(), is(not(equalTo(o2.hashCode()))));
	}
	
	private static Object anyObjectOfUnkownType() {
		return "anyObjectOfUnkownType";
	}
}
