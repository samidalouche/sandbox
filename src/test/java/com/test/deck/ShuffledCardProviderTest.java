package com.test.deck;

import static com.test.deck.ShuffledCardProvider.shuffledDeck;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ShuffledCardProviderTest {

	@Test
	public void shouldNotAlterCards() {
		CardProvider shuffledDeck = shuffledDeck(originalDeck());
		Assert.assertThat(setOf(shuffledDeck.getCards()), is(setOf(sampleCards())));
	}
	
	/**
	 * FIXME: this test is theoretically not valid, as there is a _slight_ probability that the
	 * shuffling produces exactly the same deck of playing cards. 
	 * This test would be a good use of TestNG's "Success Rate" feature.
	 * 
	 * However, to keep things simple, let's keep things as-is for now
	 * 
	 */
	@Test
	public void orderOfCardsShouldBeDifferent() {
		CardProvider shuffledDeck = ShuffledCardProvider.shuffledDeck(originalDeck());
		assertThat(shuffledDeck.getCards(), is(not(sampleCards())));
	}

	
	private Set<PlayingCard> setOf(List<PlayingCard> cards) {
		return new HashSet<PlayingCard>(cards);
	}
	
	private CardProvider originalDeck() {
		CardProvider deck = mock(CardProvider.class);
		Mockito.when(deck.getCards()).thenReturn(sampleCards()); 
		return deck;
	}
	
	private List<PlayingCard> sampleCards() {
		return Arrays.asList(
				(PlayingCard)new StandardCard(Rank.ACE, Suit.CLUBS),
				new StandardCard(Rank.TEN, Suit.DIAMONDS),
				new StandardCard(Rank.KING, Suit.SPADES),
				new StandardCard(Rank.KING, Suit.DIAMONDS),
				new StandardCard(Rank.QUEEN, Suit.SPADES),
				new StandardCard(Rank.QUEEN, Suit.HEARTS),
				new StandardCard(Rank.FOUR, Suit.CLUBS),
				new StandardCard(Rank.FIVE, Suit.CLUBS),
				new StandardCard(Rank.SIX, Suit.CLUBS),
				new StandardCard(Rank.HEIGHT, Suit.CLUBS)
		);
	}
}
