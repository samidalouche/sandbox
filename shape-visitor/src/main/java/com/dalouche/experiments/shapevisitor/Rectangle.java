package com.dalouche.experiments.shapevisitor;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Rectangle implements Shape {
	private Coordinate minXminY;
	private double width;
	private double height;
	
	/**
	 * 
	 * @return a {@link Rectangle} whose (minX, minY) 
	 * coordinate is(0,0), width is 0 and height is 0
	 */
	public static Rectangle rectangle() {
		return new Rectangle(Coordinate.zeroZero(), 0, 0);
	}
	
	private Rectangle(Coordinate minXminY, double width, double height) {
		super();
		Validate.notNull(minXminY);
		Validate.isTrue(width >= 0);
		Validate.isTrue(height >= 0);
		this.minXminY = minXminY;
		this.width = width;
		this.height = height;
	}

	public Rectangle withMinXminY(Coordinate minXminY) {
		return new Rectangle(minXminY, this.width, this.height);
	}
	
	public Rectangle withWidth(double width) {
		return new Rectangle(this.minXminY, width, this.height);
	}
	
	public Rectangle withHeight(double height) {
		return new Rectangle(this.minXminY, this.width, height);
	}
	
	
	public Coordinate getMinXminY() {
		return minXminY;
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
			.append(minXminY)
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
			.append(minXminY, other.minXminY)
			.append(width, other.width)
			.append(height, other.height)
			.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("minXminY", minXminY)
			.append("width", width)
			.append("height", height)
			.toString();
	}
	
	
}
