package com.dalouche.experiments.rockpaperscissors.console;

import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.dalouche.experiments.rockpaperscissors.console.ConsolePlayer;
import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

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
		assertThat(player.nextGesture(), is((Symbol)scissors()));
	}

	private Player player(InputStream is, ByteArrayOutputStream os) {
		Player player = new ConsolePlayer(is, new PrintStream(os));
		return player;
	}
	
	@Test
	public void shouldRetryWhenSymbolIsNotUnderstood() {
		InputStream is = new ByteArrayInputStream(toBytes("oops\nscissors"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Player player = player(is, os);
		assertThat(player.nextGesture(), is((Symbol)scissors()));
	}
	
	@Test
	public void shouldNotCrashWhenNumbersAreUsed() {
		InputStream is = new ByteArrayInputStream(toBytes("somthingContainingNumber7834874\nscissors"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Player player = player(is, os);
		assertThat(player.nextGesture(), is((Symbol)scissors()));
	}
	
	private byte[] toBytes(String string) {
		return string.getBytes();
	}
}
