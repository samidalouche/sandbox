package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Coordinate.x;
import static com.dalouche.experiments.shapevisitor.Coordinate.zeroZero;
import static com.dalouche.experiments.shapevisitor.Rectangle.rectangle;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void defaultRectangleShouldHaveSaneDefaultValues() {
		assertThat(rectangle().getMinXminY(), is(zeroZero()));
		assertThat(rectangle().getWidth(), is(0d));
		assertThat(rectangle().getHeight(), is(0d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateRectangleWithNullMinXMinY() {
		rectangle().withMinXminY(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateRectangleWithNegativeWidth() {
		rectangle().withWidth(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateRectangleWithNegativeHeight() {
		rectangle().withHeight(-1);
	}
	
	@Test
	public void shouldCreateRectangleWithGivenProperties() {
		Rectangle rectangle = rectangle()
			.withMinXminY(x(10).y(10))
			.withWidth(20).withHeight(30);
		
		assertThat(rectangle.getMinXminY(), is(equalTo(x(10).y(10))));
		assertThat(rectangle.getWidth(), is(20d));
		assertThat(rectangle.getHeight(), is(30d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyRectangle());
		shouldNotEqualNull(anyRectangle());
		shouldNotEqualObjectOfDifferentType(anyRectangle());
	}
	
	@Test
	public void rectanglesWithSameMinXMinYAndWidthAndHeightShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myRectangle(), myRectangle());
	}
	

	@Test
	public void rectanglesWithDifferentMinXminYShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			rectangle().withMinXminY(x(10).y(20)), 
			rectangle().withMinXminY(x(100).y(200)));
	}
	
	@Test
	public void rectanglesWithDifferentWidthShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			rectangle().withWidth(10), 
			rectangle().withWidth(100));
	}
	
	@Test
	public void rectanglesWithDifferentHeightShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			rectangle().withHeight(10), 
			rectangle().withHeight(100));
	}
	
	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(myRectangle().toString(), is("Rectangle[minXminY=(10.0,10.0),width=20.0,height=30.0]"));
	}

	private Object anyRectangle() {
		return rectangle();
	}
	
	private Object myRectangle() {
		return rectangle()
			.withMinXminY(x(10).y(10))
			.withWidth(20).withHeight(30);
	}
	
}
