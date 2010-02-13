package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.rockpaperscissors.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.Rock.rock;

import org.junit.Assert;
import org.junit.Test;

public class PaperTest {

	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(paper());
		shouldNotEqualNull(paper());
		shouldNotEqualObjectOfDifferentType(paper());
	}
	@Test
	public void paperShouldBeEqualToPaper() {
		shouldBeEqualAndHaveSameHashCode(paper(), paper());
	}
	
	@Test
	public void shouldDefeatRock() {
		Assert.assertEquals(rock(), paper().defeats());
	}
}
