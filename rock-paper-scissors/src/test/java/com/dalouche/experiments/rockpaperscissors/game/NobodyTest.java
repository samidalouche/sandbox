package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.rockpaperscissors.players.Nobody.nobody;

import org.junit.Test;

public class NobodyTest {

	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(nobody());
		shouldNotEqualNull(nobody());
		shouldNotEqualObjectOfDifferentType(nobody());
	}
	@Test
	public void paperShouldBeEqualToPaper() {
		shouldBeEqualAndHaveSameHashCode(nobody(), nobody());
	}

}
