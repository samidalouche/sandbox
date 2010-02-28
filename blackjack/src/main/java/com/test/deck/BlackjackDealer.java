package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.commons.Validate;

public final class BlackjackDealer implements Player {
	private Deck deck;
	private List<PlayingCard> cards = new ArrayList<PlayingCard>();
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
		dealTwoRounds();
	}

	private void dealTwoRounds() {
		for(int i = 0 ; i < 2 ; i++ ) {
			dealOneRound();	
		}
	}
	
	private void dealOneRound() {
		for(Player p : allPlayersIncludingDealer()) {
			p.acceptCard(deck.draw());
		}
	}
	
	public void acceptCard(PlayingCard card) {
		cards.add(card);
	}
	
	private List<Player> allPlayersIncludingDealer() {
		 List<Player> players = new ArrayList<Player>();
		 players.addAll(otherPlayers);
		 players.add(this);
		 return players;
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
