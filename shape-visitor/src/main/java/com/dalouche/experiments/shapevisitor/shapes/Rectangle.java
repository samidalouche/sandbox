package com.dalouche.experiments.shapevisitor.shapes;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Rectangle implements Shape {
	private double breadth;
	private double height;
	
	/**
	 * 
	 * @return a {@link Rectangle} whose breadth is 0 and height is 0
	 */
	public static Rectangle rectangle() {
		return new Rectangle(0, 0);
	}
	
	private Rectangle(double breadth, double height) {
		super();
		Validate.isTrue(breadth >= 0);
		Validate.isTrue(height >= 0);
		this.breadth = breadth;
		this.height = height;
	}
	
	public Rectangle withBreadth(double breadth) {
		return new Rectangle(breadth, this.height);
	}
	
	public Rectangle withHeight(double height) {
		return new Rectangle(this.breadth, height);
	}
	
	public double getBreadth() {
		return breadth;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(breadth)
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
		Rectangle other = (Rectangle) obj;
		
		return new EqualsBuilder()
			.append(breadth, other.breadth)
			.append(height, other.height)
			.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("breadth", breadth)
			.append("height", height)
			.toString();
	}

	@Override
	public <T> T accept(ShapeVisitor visitor) {
		return visitor.visitRectangle(this);
	}
	
}
