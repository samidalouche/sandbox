package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;

import org.junit.Assert;
import org.junit.Test;

public class ScissorsTest {

	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(scissors());
		shouldNotEqualNull(scissors());
		shouldNotEqualObjectOfDifferentType(scissors());
	}
	@Test
	public void rockShouldBeEqualToRock() {
		shouldBeEqualAndHaveSameHashCode(scissors(),scissors());
	}
	
	@Test
	public void shouldDefeatPaper() {
		Assert.assertEquals(paper(), scissors().defeats());
	}
}
