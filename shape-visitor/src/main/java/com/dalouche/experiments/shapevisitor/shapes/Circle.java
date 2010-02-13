package com.dalouche.experiments.shapevisitor.shapes;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Circle implements Shape {
	private double radius;
	
	/**
	 * @return a {@link Circle} with a radius of 0
	 */
	public static Circle circle() {
		return new Circle(0d);
	}
	
	private Circle(double radius) {
		super();
		Validate.isTrue(radius >= 0);
		this.radius = radius;
	}

	public Circle withRadius(double radius) {
		return new Circle(radius);
	}
	
	public double getRadius() {
		return radius;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(radius)
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
		Circle other = (Circle) obj;
		
		return new EqualsBuilder()
			.append(radius, other.radius)
			.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("radius", radius)
			.toString();
	}

	@Override
	public <T> T accept(ShapeVisitor<T> visitor) {
		return visitor.visitCircle(this);
	}
	
}
