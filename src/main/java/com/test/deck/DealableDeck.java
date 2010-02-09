package com.test.deck;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public final class DealableDeck implements Deck {

	private Stack<PlayingCard> cards;
	
	public DealableDeck(CardProvider deckToShuffle) {
		super();
		cards = new Stack<PlayingCard>();
		for(int i = deckToShuffle.getCards().size()-1 ; i >=0 ; i--) {
			cards.add(deckToShuffle.getCards().get(i));
		}
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public PlayingCard draw() {
		return cards.pop();
	}

}
