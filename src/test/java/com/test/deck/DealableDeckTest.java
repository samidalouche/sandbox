package com.test.deck;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DealableDeckTest {

	@Test
	public void shouldDrawFirstCardFromDeck() {
		Deck deck = createDeck();
		assertThat(deck.draw(), is(aceOfClubs()));
		assertFalse(deck.getCards().contains(aceOfClubs()));
		assertEquals(cards().size()-1, deck.getCards().size());
	}
	
	@Test(expected=NoMoreCardsException.class)
	public void shouldThrowNoMoreCardsExceptionWhenNoMoreCardsToDraw() {
		Deck deck = createDeck();
		for(int i = 0 ; i < cards().size()+1 ; i++) {
			deck.draw();
		}
	}

	private DealableDeck createDeck() {
		return new DealableDeck(cardProvider());
	}
	
	private CardProvider cardProvider() {
		CardProvider cardProvider = mock(CardProvider.class);
		when(cardProvider.getCards()).thenReturn(cards());
		return cardProvider;
	}
	private List<PlayingCard> cards() {
		return Arrays.asList(
				(PlayingCard)aceOfClubs(),
				new StandardCard(Rank.TWO, Suit.CLUBS),
				new StandardCard(Rank.THREE, Suit.CLUBS),
				new StandardCard(Rank.FOUR, Suit.CLUBS),
				new StandardCard(Rank.FIVE, Suit.CLUBS)
		);
	}


	private PlayingCard aceOfClubs() {
		return new StandardCard(Rank.ACE, Suit.CLUBS);
	}
}
