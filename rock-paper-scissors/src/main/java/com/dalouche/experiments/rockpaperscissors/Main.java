package com.dalouche.experiments.rockpaperscissors;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player player = new ConsolePlayer(System.in, System.out);
		
		//player.nextSymbol();
		ConsoleNumberOfRoundsProvider provider = new ConsoleNumberOfRoundsProvider(System.in, System.out);
		System.out.println(provider.getNumberOfRounds());
	}

}
