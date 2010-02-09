package com.test.deck;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
	public void cardsWithSameRankAndSuitShouldBeEqualAndHaveSameHashCode(){
		assertThat(new StandardCard(Rank.ACE, Suit.DIAMONDS), 
				is(new StandardCard(Rank.ACE, Suit.DIAMONDS)));
		Assert.assertEquals(new StandardCard(Rank.ACE, Suit.DIAMONDS).hashCode(), 
				new StandardCard(Rank.ACE, Suit.DIAMONDS).hashCode());
	}
	
	@Test
	public void cardsWithDifferentRanksShouldNotBeEqual() {
		assertThat(new StandardCard(Rank.ACE, Suit.DIAMONDS), 
				is(not(new StandardCard(Rank.TWO, Suit.DIAMONDS))));
		Assert.assertFalse(new StandardCard(Rank.ACE, Suit.DIAMONDS).hashCode() == 
				new StandardCard(Rank.TWO, Suit.DIAMONDS).hashCode());
	}
	
	@Test
	public void cardsWithDifferentSuitsShouldNotBeEqual() {
		assertThat(new StandardCard(Rank.ACE, Suit.DIAMONDS), 
				is(not(new StandardCard(Rank.ACE, Suit.CLUBS))));
		Assert.assertFalse(new StandardCard(Rank.ACE, Suit.DIAMONDS).hashCode() == 
				new StandardCard(Rank.ACE, Suit.CLUBS).hashCode());
	}

	private Rank anyRank() {
		return Rank.TWO;
	}

	private Suit anySuit() {
		return Suit.CLUBS;
	}
}
