package com.dalouche.experiments.shapevisitor;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Stores coordinates on the 2-dimensional Cartesian plane
 * 
 * @author sdalouche
 *
 */
public final class TwoDimensionalCartesianCoordinate {
	private double x;
	private double y;
	
	/**
	 * @return a {@link TwoDimensionalCartesianCoordinate} at (0,0)
	 */
	public static TwoDimensionalCartesianCoordinate zeroZero() {
		return new TwoDimensionalCartesianCoordinate(0, 0);
	}
	
	/**
	 * @return a {@link TwoDimensionalCartesianCoordinate} at (0,0)
	 */
	public static TwoDimensionalCartesianCoordinate coordinate() {
		return zeroZero();
	}
	
	private TwoDimensionalCartesianCoordinate(double x, double y) {
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

	public TwoDimensionalCartesianCoordinate withX(double x) {
		return new TwoDimensionalCartesianCoordinate(x, this.y);
	}
	
	public TwoDimensionalCartesianCoordinate withY(double y) {
		return new TwoDimensionalCartesianCoordinate(this.x, y);
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
		TwoDimensionalCartesianCoordinate other = (TwoDimensionalCartesianCoordinate) obj;
		
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
