package com.test.deck;

import java.util.ArrayList;
import java.util.List;

import com.test.commons.Validate;

public final class BlackjackDealer implements Player {
	private Deck deck;
	private List<PlayingCard> cards;
	private List<? extends Player> otherPlayers;
	
	public BlackjackDealer(Deck deck, List<? extends Player> otherPlayers) {
		super();
		
		validateOneToSevenPlayers(otherPlayers.size());
		Validate.notNull(deck);
		this.deck = deck;
		this.otherPlayers = otherPlayers;
	}

	private void validateOneToSevenPlayers(int numberOfPlayers) {
		if(numberOfPlayers < 1 || numberOfPlayers > 7) {
			throw new IllegalArgumentException("Standard casino blackjack requires 1 to 7 players in addition of the dealer");
		}
	}

	public void dealOpeninghand() {
		
	}
	
	public void acceptCard(PlayingCard card) {
		cards.add(card);
	}
	
	private List<Player> allPlayersIncludingDealer() {
		 List<Player> players = new ArrayList<Player>();
		 players.add(this);
		 players.addAll(otherPlayers);
		 return players;
	}
}
