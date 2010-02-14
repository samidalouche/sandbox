package com.dalouche.experiments.rockpaperscissors;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.lang.Validate;

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
				consumeLine(scanner);
				displayErrorMessage(e);
			}
		}
		
		return numberOfRounds;
	}

	private void consumeLine(Scanner scanner) {
		scanner.nextLine();
	}
	
	private void displayErrorMessage(InputMismatchException e) {
		outputStream.println("Your number is not recognized. Please try again");
		outputStream.println();
	}

	
	private int askNumberOfRounds(Scanner scanner) {
		outputStream.print("Please enter the number of rounds you want to play: ");
		return scanner.nextInt();
	}

}
