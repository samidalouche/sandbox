package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Rectangle.rectangle;
import static com.dalouche.experiments.shapevisitor.Triangle.triangle;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TriangleTest {
	@Test
	public void defaultTriangleShouldHaveSaneDefaultValues() {
		assertThat(triangle().getBase(), is(0d));
		assertThat(triangle().getHeight(), is(0d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateTriangleWithNegativeBase() {
		triangle().withBase(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateTriangleWithNegativeHeight() {
		triangle().withHeight(-1);
	}
	
	@Test
	public void shouldCreateTriangleWithGivenProperties() {
		Triangle triangle = triangle()
			.withBase(20)
			.withHeight(30);
		
		assertThat(triangle.getBase(), is(20d));
		assertThat(triangle.getHeight(), is(30d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyTriangle());
		shouldNotEqualNull(anyTriangle());
		shouldNotEqualObjectOfDifferentType(anyTriangle());
	}

	@Test
	public void triangleWithSameWidthAndHeightShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myTriangle(), myTriangle());
	}
	

	@Test
	public void trianglesWithDifferentBaseShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			triangle().withBase(10), 
			triangle().withBase(100));
	}
	
	@Test
	public void trianglesWithDifferentHeightShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			triangle().withHeight(10), 
			triangle().withHeight(100));
	}
	
	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(myTriangle().toString(), is("Triangle[base=20.0,height=30.0]"));
	}
	
	
	private Object myTriangle() {
		return triangle()
		.withBase(20).withHeight(30);
	}

	private Object anyTriangle() {
		return triangle();
	}
}
