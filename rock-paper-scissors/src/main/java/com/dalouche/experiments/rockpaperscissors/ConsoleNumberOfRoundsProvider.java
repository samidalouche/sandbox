package com.dalouche.experiments.rockpaperscissors;

import java.io.InputStream;
import java.io.PrintStream;

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
		// TODO Auto-generated method stub
		return 0;
	}

}
