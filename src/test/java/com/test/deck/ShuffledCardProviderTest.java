package com.test.deck;

import static com.test.deck.ShuffledCardProvider.shuffledCardProvider;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ShuffledCardProviderTest {

	@Test
	public void shouldNotAlterCards() {
		CardProvider shuffledCardProvider = shuffledCardProvider(originalCardProvider());
		Assert.assertThat(setOf(shuffledCardProvider.getCards()), is(setOf(sampleCards())));
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
		CardProvider shuffledCardProvider = ShuffledCardProvider.shuffledCardProvider(originalCardProvider());
		assertThat(shuffledCardProvider.getCards(), is(not(sampleCards())));
	}

	
	private Set<PlayingCard> setOf(List<PlayingCard> cards) {
		return new HashSet<PlayingCard>(cards);
	}
	
	private CardProvider originalCardProvider() {
		CardProvider cardProvider = mock(CardProvider.class);
		Mockito.when(cardProvider.getCards()).thenReturn(sampleCards()); 
		return cardProvider;
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
