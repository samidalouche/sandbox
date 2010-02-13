package com.dalouche.experiments.shapevisitor.shapes;

public interface ShapeVisitor<T> {
	T visitCircle(Circle circle);
	T visitTriangle(Triangle triangle);
	T visitRectangle(Rectangle rectangle);
}
