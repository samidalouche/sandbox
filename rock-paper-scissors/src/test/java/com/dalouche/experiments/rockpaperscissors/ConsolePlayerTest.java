package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Scissors.scissors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ConsolePlayerTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateConsolePlayerWithoutInputStream() {
		new ConsolePlayer(null, anyPrintStream());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateConsolePlayerWithoutOutputStream() {
		new ConsolePlayer(anyInputStream(), null);
	}
	
	private InputStream anyInputStream() {
		return System.in;
	}

	private PrintStream anyPrintStream() {
		return System.out;
	}

	@Test
	public void shouldAskForNextSymbolAndInterpretResponse() {
		InputStream is = new ByteArrayInputStream(toBytes("scissors"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Player player = player(is, os);
		assertThat(player.nextSymbol(), is((Symbol)scissors()));
	}

	private Player player(InputStream is, ByteArrayOutputStream os) {
		Player player = new ConsolePlayer(is, new PrintStream(os));
		return player;
	}
	
	@Test
	public void shouldRetryWhenSymbolIsNotUnderstood() {
		InputStream is = new ByteArrayInputStream(toBytes("oops scissors"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Player player = player(is, os);
		assertThat(player.nextSymbol(), is((Symbol)scissors()));
	}
	
	private byte[] toBytes(String string) {
		return string.getBytes();
	}
}
