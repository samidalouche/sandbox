package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffledDeck implements Deck {

	private List<PlayingCard> cards;
	
	public static Deck shuffledDeck(Deck deckToShuffle) {
		return new ShuffledDeck(deckToShuffle);
	}
	
	public ShuffledDeck(Deck deckToShuffle) {
		super();
		cards = new ArrayList<PlayingCard>(deckToShuffle.getCards());
		Collections.shuffle(cards);
	}

	public List<PlayingCard> getCards() {
		return cards;
	}

}
