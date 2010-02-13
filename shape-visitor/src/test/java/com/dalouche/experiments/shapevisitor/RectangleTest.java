package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Rectangle.rectangle;
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

public class RectangleTest {

	@Test
	public void defaultRectangleShouldHaveSaneDefaultValues() {
		assertThat(rectangle().getWidth(), is(0d));
		assertThat(rectangle().getHeight(), is(0d));
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
			.withWidth(20).withHeight(30);
		
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
	public void rectanglesWithSameWidthAndHeightShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myRectangle(), myRectangle());
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
		assertThat(myRectangle().toString(), is("Rectangle[width=20.0,height=30.0]"));
	}

	@Test
	public void shouldAcceptRectangleVisitorAndReturnItsResult() {
		ShapeVisitor visitor = mock(ShapeVisitor.class);
		when(visitor.visitRectangle((Rectangle) Mockito.anyObject()))
			.thenReturn("VisitorCalculation");
		Rectangle shape = myRectangle();
		
		String value = ((Shape)shape).accept(visitor);
		Assert.assertEquals("VisitorCalculation", value);
		Mockito.verify(visitor).visitRectangle(Mockito.same(shape));
	}
	
	private Object anyRectangle() {
		return rectangle();
	}
	
	private Rectangle myRectangle() {
		return rectangle()
			.withWidth(20).withHeight(30);
	}
	
}
