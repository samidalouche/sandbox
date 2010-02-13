package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Circle.circle;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class CircleTest {

	@Test
	public void circleShouldHaveSaneDefaultRadius() {
		assertThat(circle().getRadius(), is(0d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateCircleWithNegativeRadius() {
		circle().withRadius(-1);
	}
	
	@Test
	public void shouldCreateCircleWithGivenRadius() {
		Circle circle = myCircle();
		assertThat(circle.getRadius(), is(100d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyCircle());
		shouldNotEqualNull(anyCircle());
		shouldNotEqualObjectOfDifferentType(anyCircle());
	}
	
	@Test
	public void circlesWithSameRadiusShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myCircle(), myCircle());
	}
	
	@Test
	public void circlesWithDifferentRadiusesShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			myCircle().withRadius(5), 
			myCircle().withRadius(10));
	}
	
	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(myCircle().toString(), is("Circle[radius=100.0]"));
	}
	
	@Test
	public void shouldAcceptCircleVisitorAndReturnItsResult() {
		ShapeVisitor visitor = mock(ShapeVisitor.class);
		when(visitor.visitCircle((Circle) Mockito.anyObject()))
			.thenReturn("VisitorCalculation");
		Circle shape = myCircle();
		
		String value = ((Shape)shape).accept(visitor);
		Assert.assertEquals("VisitorCalculation", value);
		Mockito.verify(visitor).visitCircle(Mockito.same(shape));
	}

	private Circle myCircle() {
		return circle().withRadius(100);
	}

	private Object anyCircle() {
		return circle();
	}
}
