package com.dalouche.experiments.shapevisitor.shapes;

public interface ShapeVisitor {
	<T> T visitCircle(Circle circle);
	<T> T visitTriangle(Triangle triangle);
	<T> T visitRectangle(Rectangle rectangle);
}
