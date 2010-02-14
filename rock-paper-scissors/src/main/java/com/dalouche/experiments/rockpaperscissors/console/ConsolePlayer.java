package com.dalouche.experiments.rockpaperscissors.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.dalouche.experiments.rockpaperscissors.symbols.DefaultSymbolRepository;
import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;
import com.dalouche.experiments.rockpaperscissors.symbols.SymbolNotFoundException;
import com.dalouche.experiments.rockpaperscissors.symbols.SymbolRepository;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * A player that interacts with the console (input/output streams)
 * 
 * @author sdalouche
 *
 */
public final class ConsolePlayer implements Player{

	private static final String SYMBOL_PATTERN = "[A-Za-z]+";
	private SymbolRepository symbolRepository;
	private InputStream inputStream;
	private PrintStream outputStream;
	
	public ConsolePlayer(InputStream inputStream, PrintStream outputStream) {
		this(inputStream, outputStream, new DefaultSymbolRepository());
	}

	public ConsolePlayer(InputStream inputStream, PrintStream outputStream, SymbolRepository symbolRepository) {
		super();
		Validate.notNull(inputStream);
		Validate.notNull(outputStream);
		Validate.notNull(symbolRepository);
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.symbolRepository = symbolRepository;
	}

	@Override
	public Symbol nextGesture() {
		Scanner scanner = new Scanner(inputStream);
		Symbol symbol = null;
		
		while(symbol == null) {
			try {
				symbol = askNextMoveToUser(scanner);		
			} catch(SymbolNotFoundException e) {
				displayErrorMessage(e);
			}
		}
		
		return symbol;
	}

	private void displayErrorMessage(SymbolNotFoundException e) {
		outputStream.println(
			String.format(
				"Your move is not recognized (%s). Please try again", 
				e.getName()));
		outputStream.println();
	}

	private Symbol askNextMoveToUser(Scanner scanner) {
		outputStream.print(String.format("Please enter your next move %s: ", symbolNames()));
		String symbolName = scanner.next(SYMBOL_PATTERN);
		return this.symbolRepository.findSymbolByName(symbolName);
	}

	private ImmutableList<String> symbolNames() {
		return ImmutableList.copyOf(Iterables.transform(symbolRepository.findAllSymbols(), new Function<Symbol, String>() {
			@Override
			public String apply(Symbol from) {
				return from.name();
			}
		}));
	}
}
