package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public final class OtherDealableDeck implements Deck {

	private Stack<PlayingCard> cards;
	
	public static CardProvider shuffledDeck(CardProvider deckToShuffle) {
		return new OtherDealableDeck(deckToShuffle);
	}
	
	public OtherDealableDeck(CardProvider deckToShuffle) {
		super();
		List<PlayingCard> randomizedCards = new ArrayList<PlayingCard>(deckToShuffle.getCards());
		Collections.shuffle(randomizedCards);
		cards = new Stack<PlayingCard>();
		cards.addAll(randomizedCards);
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public PlayingCard draw() {
		return cards.pop();
	}

}
