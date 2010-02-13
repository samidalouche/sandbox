package com.dalouche.experiments.shapevisitor;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Rectangle implements Shape {
	private double width;
	private double height;
	
	/**
	 * 
	 * @return a {@link Rectangle} whose width is 0 and height is 0
	 */
	public static Rectangle rectangle() {
		return new Rectangle(0, 0);
	}
	
	private Rectangle(double width, double height) {
		super();
		Validate.isTrue(width >= 0);
		Validate.isTrue(height >= 0);
		this.width = width;
		this.height = height;
	}
	
	public Rectangle withWidth(double width) {
		return new Rectangle(width, this.height);
	}
	
	public Rectangle withHeight(double height) {
		return new Rectangle(this.width, height);
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(width)
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
			.append(width, other.width)
			.append(height, other.height)
			.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("width", width)
			.append("height", height)
			.toString();
	}

	@Override
	public <T> T accept(ShapeVisitor visitor) {
		return visitor.visitRectangle(this);
	}
	
}
