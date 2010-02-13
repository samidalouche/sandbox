package com.dalouche.experiments.shapevisitor;

import static com.dalouche.experiments.shapevisitor.Coordinate.coordinate;
import static com.dalouche.experiments.shapevisitor.Coordinate.zeroZero;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.shapevisitor.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class CoordinateTest {

	@Test
	public void defaultCoordinatesShouldBeZeroZero() {
		Coordinate coordinate = zeroZero();
		assertThat(coordinate.getX(), is(0d));
		assertThat(coordinate.getY(), is(0d));
	}
	
	@Test
	public void shouldCreateCoordinatesWithGivenValues() {
		Coordinate coordinate = coordinate().withX(5).withY(20);
		assertThat(coordinate.getX(), is(5d));
		assertThat(coordinate.getY(), is(20d));
	}
	
	@Test
	public void shouldAcceptNegativeValuesForXAndY() {
		Coordinate coordinate = coordinate().withX(-5).withY(-20);
		assertThat(coordinate.getX(), is(-5d));
		assertThat(coordinate.getY(), is(-20d));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(sampleCoordinateObject());
		shouldNotEqualNull(sampleCoordinateObject());
		shouldNotEqualObjectOfDifferentType(sampleCoordinateObject());
	}

	@Test
	public void coordinatesWithSameXAndYShouldBeEqualAndHaveSameHashCode() {
		shouldBeEqualAndHaveSameHashCode(
				coordinate().withX(5).withY(20), 
			coordinate().withX(5).withY(20));
		
	}
	
	@Test
	public void coordinatesWithDifferentYShouldNotBeEqualAndHaveDifferentHashCode() {
		shouldNotBeEqualAndHaveDifferentHashCode(
				coordinate().withX(5).withY(20), 
			coordinate().withX(5).withY(50));
	}
	
	@Test
	public void coordinatesWithDifferentXShouldNotBeEqualAndHaveDifferentHashCode() {
		shouldNotBeEqualAndHaveDifferentHashCode(
				coordinate().withX(5).withY(20), 
			coordinate().withX(50).withY(50));
	}
	
	@Test
	public void toStringShouldBeDescriptive() {
		assertThat(coordinate().withX(5).withY(-20).toString(), is("(5.0,-20.0)"));
	}
	
	private Coordinate sampleCoordinateObject() {
		return coordinate();
	}
	
}
