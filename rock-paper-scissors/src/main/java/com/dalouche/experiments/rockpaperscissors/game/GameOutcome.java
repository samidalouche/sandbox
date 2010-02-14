package com.dalouche.experiments.rockpaperscissors.game;

import java.util.Map;

import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * The result of a {@link Game}
 * 
 * <p> FIXME: this class is still a little bit too anaemic. 
 * It should be a little smarter and better prepare the data so that the
 * formatting layer only handles formatting.
 * </p>
 * @author sdalouche
 *
 */
public final class GameOutcome {
	private ImmutableList<Round> rounds;

	public GameOutcome(Iterable<Round> rounds) {
		super();
		this.rounds = ImmutableList.copyOf(rounds);
	}
	
	/**
	 * FIXME: instead of a Map, we should return an abstraction that allows to tell 
	 * the number of wins per player, without any risk for null values..
	 * @return
	 */
	public ImmutableMap<Player, Integer> numberOfWinsPerPlayer() {
		Map<Player, Integer> numberOfWinsPerPlayer = Maps.newHashMap();
		for(Round r : rounds) {
			incrementNumberOfWinsForPlayer(r.getRoundWinner(), numberOfWinsPerPlayer);
		}
		
		return ImmutableMap.copyOf(numberOfWinsPerPlayer);
	}

	private void incrementNumberOfWinsForPlayer(Player roundWinner, Map<Player, Integer> numberOfWinsPerPlayer) {
		Integer i = numberOfWinsPerPlayer.get(roundWinner);
		if(i == null) {
			i = 0;
		}
		numberOfWinsPerPlayer.put(roundWinner, ++i);
	}
}
