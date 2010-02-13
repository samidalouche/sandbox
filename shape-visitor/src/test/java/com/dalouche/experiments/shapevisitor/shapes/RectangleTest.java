package com.dalouche.experiments.shapevisitor.shapes;

import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.shapevisitor.shapes.Rectangle.rectangle;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.dalouche.experiments.shapevisitor.shapes.Rectangle;
import com.dalouche.experiments.shapevisitor.shapes.Shape;
import com.dalouche.experiments.shapevisitor.shapes.ShapeVisitor;

public class RectangleTest {

	@Test
	public void defaultRectangleShouldHaveSaneDefaultValues() {
		assertThat(rectangle().getBreadth(), is(0d));
		assertThat(rectangle().getHeight(), is(0d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateRectangleWithNegativeBreadth() {
		rectangle().withBreadth(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateRectangleWithNegativeHeight() {
		rectangle().withHeight(-1);
	}
	
	@Test
	public void shouldCreateRectangleWithGivenProperties() {
		Rectangle rectangle = rectangle()
			.withBreadth(20).withHeight(30);
		
		assertThat(rectangle.getBreadth(), is(20d));
		assertThat(rectangle.getHeight(), is(30d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyRectangle());
		shouldNotEqualNull(anyRectangle());
		shouldNotEqualObjectOfDifferentType(anyRectangle());
	}
	
	@Test
	public void rectanglesWithSameBreadthAndHeightShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(myRectangle(), myRectangle());
	}
	
	@Test
	public void rectanglesWithDifferentBreadthShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			rectangle().withBreadth(10), 
			rectangle().withBreadth(100));
	}
	
	@Test
	public void rectanglesWithDifferentHeightShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(
			rectangle().withHeight(10), 
			rectangle().withHeight(100));
	}
	
	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(myRectangle().toString(), is("Rectangle[breadth=20.0,height=30.0]"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldAcceptRectangleVisitorAndReturnItsResult() {
		ShapeVisitor<String> visitor = mock(ShapeVisitor.class);
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
			.withBreadth(20).withHeight(30);
	}
	
}
