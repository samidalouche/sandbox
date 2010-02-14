package com.dalouche.experiments.rockpaperscissors;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class GameOutcome {
	private ImmutableList<Round> rounds;

	public GameOutcome(Iterable<Round> rounds) {
		super();
		this.rounds = ImmutableList.copyOf(rounds);
	}
	
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
