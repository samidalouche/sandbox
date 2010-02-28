package com.test.deck;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class JokerCardTest {

	@Test
	public void jokerCardsShouldBeEqualAndHaveSameHashcode() {
		assertThat(new JokerCard(), is(new JokerCard()));
		assertThat(new JokerCard().hashCode(), is(new JokerCard().hashCode()));
	}
	
	@Test
	public void jokerCardsShouldNotBeEqualToAnythingElse() {
		assertThat(joker(), is(not(equalTo(anyStandardCard()))));
		assertThat(joker().hashCode(), is(not(equalTo(anyStandardCard().hashCode()))));
	}

	private PlayingCard joker() {
		return (PlayingCard)new JokerCard();
	}

	private PlayingCard anyStandardCard() {
		return new StandardCard(Rank.ACE, Suit.CLUBS);
	}
}
