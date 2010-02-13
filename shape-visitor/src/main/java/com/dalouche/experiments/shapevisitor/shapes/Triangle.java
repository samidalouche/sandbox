package com.dalouche.experiments.shapevisitor.shapes;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Triangle implements Shape {
	private double base;
	private double height;
	
	/**
	 * @return a {@link Triangle} with a base of 0 and height of 0
	 */
	public static Triangle triangle() {
		return new Triangle(0, 0);
	}
	private Triangle(double base, double height) {
		super();
		Validate.isTrue(base >= 0);
		Validate.isTrue(height >= 0);
		this.base = base;
		this.height = height;
	}
	
	public Triangle withBase(double base) {
		return new Triangle(base, this.height);
	}
	
	public Triangle withHeight(double height) {
		return new Triangle(this.base, height);
	}
	
	public double getBase() {
		return base;
	}
	public double getHeight() {
		return height;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(base)
			.append(height)
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		
		return new EqualsBuilder()
			.append(base, other.base)
			.append(height, other.height)
			.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("base", base)
			.append("height", height)
			.toString();
	}
	@Override
	public <T> T accept(ShapeVisitor<T> visitor) {
		return visitor.visitTriangle(this);
	}
}
