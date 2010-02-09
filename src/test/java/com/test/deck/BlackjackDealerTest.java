package com.test.deck;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class BlackjackDealerTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateDealerWithLessThanOnePlayer() {
		new BlackjackDealer(anyDeck(), emptyList());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateDealerWithMoreThanSevenPlayer() {
		new BlackjackDealer(anyDeck(), listOfHeightPlayers());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateDealerWithNoDeck() {
		new BlackjackDealer(null, validListOfPlayers());
	}

	private int validNumberOfPlayers() {
		return 2;
	}

	private Deck anyDeck() {
		return mock(Deck.class);
	}
	
	private List<Player> emptyList() {
		return new ArrayList<Player>();
	}
	
	private List<Player> validListOfPlayers() {
		return listOfPlayers(validNumberOfPlayers());
	}
	
	private List<Player> listOfHeightPlayers() {
		return listOfPlayers(8);
	}

	private List<Player> listOfPlayers(int n) {
		List<Player> players = new ArrayList<Player>();
		for(int i = 0 ; i < n ; i++) {
			players.add(Mockito.mock(Player.class));
		}
		return players;
	}
}
