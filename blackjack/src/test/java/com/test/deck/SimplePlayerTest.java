package com.test.deck;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SimplePlayerTest {

	@Test
	public void shouldAcceptCard() {
		Player player = new SimplePlayer();
		player.acceptCard(sampleCard());
		assertThat(player.getCards(), is(Arrays.asList(sampleCard())));
	}
	
	PlayingCard sampleCard() {
		return new StandardCard(Rank.ACE, Suit.CLUBS);
	}
}
