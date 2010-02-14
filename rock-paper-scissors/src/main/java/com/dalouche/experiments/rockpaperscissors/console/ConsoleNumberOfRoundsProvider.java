package com.dalouche.experiments.rockpaperscissors.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.rockpaperscissors.game.NumberOfRoundsProvider;

/**
 * Asks the console user how many games he wants to play.
 * 
 * <p>
 * Retries in case of an error
 * </p>
 * 
 * @author sdalouche
 *
 */
public class ConsoleNumberOfRoundsProvider implements NumberOfRoundsProvider {

	private InputStream inputStream;
	private PrintStream outputStream;
	
	public ConsoleNumberOfRoundsProvider(InputStream inputStream,
			PrintStream outputStream) {
		super();
		Validate.notNull(inputStream);
		Validate.notNull(outputStream);
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}

	@Override
	public int getNumberOfRounds() {
		Scanner scanner = new Scanner(inputStream);
		Integer numberOfRounds = null;
		
		while(numberOfRounds == null) {
			try {
				numberOfRounds = askNumberOfRounds(scanner);		
			} catch(InputMismatchException e) {
				handleError(scanner);
			} catch(IllegalArgumentException e) {
				handleError(scanner);
			}
		}
		
		return numberOfRounds;
	}

	private void handleError(Scanner scanner) {
		consumeLine(scanner);
		displayErrorMessage();
	}

	private void consumeLine(Scanner scanner) {
		scanner.nextLine();
	}
	
	private void displayErrorMessage() {
		outputStream.println("Your number is not recognized. Please retry with a number greater than 1. ");
		outputStream.println();
	}

	
	private int askNumberOfRounds(Scanner scanner) {
		outputStream.print("Please enter the number of rounds you want to play: ");
		int i = scanner.nextInt();
		Validate.isTrue(i >= 1);
		return i;
	}

}
