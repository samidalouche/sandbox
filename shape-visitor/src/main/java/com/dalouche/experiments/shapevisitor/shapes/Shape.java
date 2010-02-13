package com.dalouche.experiments.shapevisitor.shapes;

/**
 * A geometric shape
 * 
 * @author sdalouche
 *
 */
public interface Shape {
	<T> T accept(ShapeVisitor visitor);
}
