package com.test.deck;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class BlackjackDealerTest {

	@Test
	public void shouldDealOpeningHandStartingEachRoundWithPlayerOne() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		
		BlackjackDealer dealer = new BlackjackDealer(sampledeck(), Arrays.asList(player1, player2));
		dealer.dealOpeninghand();
		
		Mockito.verify(player1).acceptCard(new StandardCard(Rank.ACE, Suit.CLUBS));
		Mockito.verify(player2).acceptCard(new StandardCard(Rank.TWO, Suit.CLUBS));
		Mockito.verify(player1).acceptCard(new StandardCard(Rank.FOUR, Suit.CLUBS));
		Mockito.verify(player2).acceptCard(new StandardCard(Rank.FIVE, Suit.CLUBS));
		Assert.assertEquals(
				Arrays.asList(new StandardCard(Rank.THREE, Suit.CLUBS), new StandardCard(Rank.SIX, Suit.CLUBS)), 
				dealer.getCards());
	}
	
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
			players.add(mock(Player.class));
		}
		return players;
	}
	
	private Deck sampledeck() {
		CardProvider cardProvider = Mockito.mock(CardProvider.class);
		Mockito.when(cardProvider.getCards()).thenReturn(cards());
		return new DealableDeck(cardProvider);
	}
	private List<PlayingCard> cards() {
		return Arrays.asList(
				(PlayingCard)new StandardCard(Rank.ACE, Suit.CLUBS),
				new StandardCard(Rank.TWO, Suit.CLUBS),
				new StandardCard(Rank.THREE, Suit.CLUBS),
				new StandardCard(Rank.FOUR, Suit.CLUBS),
				new StandardCard(Rank.FIVE, Suit.CLUBS),
				new StandardCard(Rank.SIX, Suit.CLUBS),
				new StandardCard(Rank.SEVEN, Suit.CLUBS),
				new StandardCard(Rank.HEIGHT, Suit.CLUBS)
		);
	}
}
