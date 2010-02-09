package com.test.deck;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StandardCardTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateCardWithNullRank() {
		new StandardCard(null, anySuit());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateCardWithNullSuit() {
		new StandardCard(anyRank(), null);
	}
	
	@Test
	public void shouldCreateStandardCard() {
		StandardCard card = new StandardCard(Rank.ACE, Suit.DIAMONDS);
		assertThat(card.getRank(), is(Rank.ACE));
		assertThat(card.getSuit(), is(Suit.DIAMONDS));
	}
	
	@Test
	public void cardsWithSameRankAndSuitShouldBeEqual(){
		// TODO
	}
	
	@Test
	public void cardsWithDifferentRanksShouldNotBeEqual() {
		// TODO
	}
	
	@Test
	public void cardsWithDifferentSuitsShouldNotBeEqual() {
		// TODO
	}

	private Rank anyRank() {
		return Rank.TWO;
	}

	private Suit anySuit() {
		return Suit.CLUBS;
	}
}
