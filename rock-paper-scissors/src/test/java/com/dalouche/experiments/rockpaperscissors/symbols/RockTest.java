package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;

import org.junit.Assert;
import org.junit.Test;

public class RockTest {

	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(rock());
		shouldNotEqualNull(rock());
		shouldNotEqualObjectOfDifferentType(rock());
	}
	@Test
	public void rockShouldBeEqualToRock() {
		shouldBeEqualAndHaveSameHashCode(rock(), rock());
	}
	
	@Test
	public void shouldDefeatScissors() {
		Assert.assertEquals(scissors(), rock().defeats());
	}
}
