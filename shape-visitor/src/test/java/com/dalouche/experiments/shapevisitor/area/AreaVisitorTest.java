package com.dalouche.experiments.shapevisitor.area;

import static com.dalouche.experiments.shapevisitor.shapes.Circle.circle;
import static com.dalouche.experiments.shapevisitor.shapes.Rectangle.rectangle;
import static com.dalouche.experiments.shapevisitor.shapes.Triangle.triangle;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.dalouche.experiments.shapevisitor.shapes.Shape;
import com.dalouche.experiments.shapevisitor.shapes.ShapeVisitor;

public class AreaVisitorTest {

	private ShapeVisitor areaVisitor;

	@Before
	public void onSetUp() {
		this.areaVisitor = new AreaVisitor();
	}
	
	@Test
	public void shouldCalculateCircleArea() {
		assertEquals(1963.49d, calculateArea(circle().withRadius(25)), precision());
	}

	private double precision() {
		return .01;
	}
	
	@Test
	public void shouldCalculateRectangleArea() {
		assertEquals(616.5d, calculateArea(rectangle().withBreadth(22.5).withHeight(27.4)), precision());
	}
	
	@Test
	public void shouldCalculateTriangleArea() {
		assertEquals(324.6384d, calculateArea(triangle().withBase(22.56d).withHeight(28.78)), precision());
	}

	private double calculateArea(Shape shape) {
		return ((Double) shape.accept(areaVisitor)).doubleValue();
	}
}
