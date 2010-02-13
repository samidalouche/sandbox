package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.shapes.Circle.circle;
import static com.dalouche.experiments.shapevisitor.shapes.Rectangle.rectangle;
import static com.dalouche.experiments.shapevisitor.shapes.Triangle.triangle;

import com.dalouche.experiments.shapevisitor.area.AreaVisitor;
import com.dalouche.experiments.shapevisitor.shapes.Circle;
import com.dalouche.experiments.shapevisitor.shapes.Rectangle;
import com.dalouche.experiments.shapevisitor.shapes.Shape;
import com.dalouche.experiments.shapevisitor.shapes.Triangle;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImmutableSet<Shape> shapes = 
			ImmutableSet.of(
				triangle().withBase(22.57).withHeight(56),
				rectangle().withBreadth(20).withHeight(40),
				circle().withRadius(78)
			);
		
		displayAreas(shapes);
	}

	private static void displayAreas(ImmutableSet<Shape> shapes) {
		for(Shape shape : shapes) {
			System.out.println(String.format("Area of %s: %s",shape, area(shape)));
		}
	}

	private static double area(Shape shape) {
		return shape.accept(new AreaVisitor());
	}

	

}
