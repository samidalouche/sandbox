package com.dalouche.experiments.shapevisitor;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Stores coordinates on the 2-dimensional Cartesian plane
 * 
 * @author sdalouche
 *
 */
public final class Coordinate {
	private double x;
	private double y;
	
	/**
	 * @return a {@link Coordinate} at (0,0)
	 */
	public static Coordinate zeroZero() {
		return new Coordinate(0, 0);
	}
	
	/**
	 * @return a {@link Coordinate} at (0,0)
	 */
	public static Coordinate coordinate() {
		return zeroZero();
	}
	
	private Coordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Coordinate withX(double x) {
		return new Coordinate(x, this.y);
	}
	
	public Coordinate withY(double y) {
		return new Coordinate(this.x, y);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(x)
			.append(y)
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
		Coordinate other = (Coordinate) obj;
		
		return new EqualsBuilder()
			.append(x, other.x)
			.append(y, other.y)
			.isEquals();
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)", x, y);
	}
	
	
}
