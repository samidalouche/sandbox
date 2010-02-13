package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Circle.circle;
import static com.dalouche.experiments.shapevisitor.Coordinate.x;
import static com.dalouche.experiments.shapevisitor.Coordinate.zeroZero;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CircleTest {

	@Test
	public void circleShouldHaveSaneDefaultValues() {
		assertThat(circle().getCenter(), is(zeroZero()));
		assertThat(circle().getRadius(), is(0d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateCircleWithNullCenter() {
		circle().withCenter(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateCircleWithNegativeRadius() {
		circle().withRadius(-1);
	}
	
	@Test
	public void shouldCreateCircleWithGivenValues() {
		Circle circle = myCircle();
		assertThat(circle.getCenter(), is(equalTo(x(5).y(50))));
		assertThat(circle.getRadius(), is(100d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyCircle());
		shouldNotEqualNull(anyCircle());
		shouldNotEqualObjectOfDifferentType(anyCircle());
	}
	
	@Test
	public void circlesWithSameCenterAndRadiusShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myCircle(), myCircle());
	}
	
	@Test
	public void circlesWithDifferentRadiusesShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			myCircle().withRadius(5), 
			myCircle().withRadius(10));
	}
	
	@Test
	public void circlesWithDifferentCentersShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			myCircle().withCenter(x(10).y(10)), 
			myCircle().withCenter(x(20).y(20)));
	}

	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(myCircle().toString(), is("Circle[center=(5.0,50.0),radius=100.0]"));
	}
	
	private Circle myCircle() {
		return circle().withCenter(x(5).y(50)).withRadius(100);
	}

	private Object anyCircle() {
		return circle();
	}
}
