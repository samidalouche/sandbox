package com.dalouche.experiments.shapevisitor;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Circle implements Shape {
	private Coordinate center;
	private double radius;
	
	/**
	 * @return a {@link Circle} with a Center of (0,0) and a radius of 0
	 */
	public static Circle circle() {
		return new Circle(Coordinate.zeroZero(), 0d);
	}
	
	private Circle(Coordinate center, double radius) {
		super();
		Validate.notNull(center);
		Validate.isTrue(radius >= 0);
		this.center = center;
		this.radius = radius;
	}

	public Circle withCenter(Coordinate center) {
		return new Circle(center, this.radius);
	}
	
	public Circle withRadius(double radius) {
		return new Circle(this.center, radius);
	}
	
	public Coordinate getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(center)
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
			.append(center, other.center)
			.append(radius, other.radius)
			.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("center", center)
			.append("radius", radius)
			.toString();
	}
	
	
	
}
