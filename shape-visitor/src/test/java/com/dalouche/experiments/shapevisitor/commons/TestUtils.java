package com.dalouche.experiments.shapevisitor.commons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

public class TestUtils {
	public static void equalShouldBeReflexive(Object o) {
		assertThat(o, is(equalTo(o)));
	}
	
	public static void shouldNotEqualNull(Object o) {
		assertThat(o, is(not(equalTo(null))));
	}
	
	public static void shouldNotEqualObjectOfDifferentType(Object o) {
		assertThat(o, is(not(equalTo(anyObjectOfUnkownType()))));
	}

	public static void shouldBeEqualAndHaveSameHashCode(Object o1, Object o2) {
		assertThat(o1, is(equalTo(o2)));
		assertThat(o1.hashCode(), is(equalTo(o2.hashCode())));
	}
	
	public static void shouldNotBeEqualAndHaveDifferentHashCode(Object o1, Object o2) {
		assertThat(o1, is(not(equalTo(o2))));
		assertThat(o1.hashCode(), is(not(equalTo(o2.hashCode()))));
	}
	
	private static Object anyObjectOfUnkownType() {
		return "anyObjectOfUnkownType";
	}
}
