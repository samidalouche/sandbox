package com.dalouche.experiments.shapevisitor.area;

import com.dalouche.experiments.shapevisitor.shapes.Circle;
import com.dalouche.experiments.shapevisitor.shapes.Rectangle;
import com.dalouche.experiments.shapevisitor.shapes.ShapeVisitor;
import com.dalouche.experiments.shapevisitor.shapes.Triangle;

public class AreaVisitor implements ShapeVisitor<Double> {

	/**
	 * Calculates the Area of the given circle
	 * 
	 * area = PI r^2
	 */
	@Override
	public Double visitCircle(Circle circle) {
		return Math.PI*(circle.getRadius()*circle.getRadius());
	}

	/**
	 * Calculates the Area of the given rectangle
	 * 
	 * area = h x w 
	 */
	@Override
	public Double visitRectangle(Rectangle rectangle) {
		return rectangle.getHeight()*rectangle.getBreadth();
	}

	/**
	 * Calculates the Area of the given triangle
	 * 
	 * area = 1/2 x b x h
	 */
	@Override
	public Double visitTriangle(Triangle triangle) {
		return 0.5d*triangle.getBase()*triangle.getHeight();
	}

}
