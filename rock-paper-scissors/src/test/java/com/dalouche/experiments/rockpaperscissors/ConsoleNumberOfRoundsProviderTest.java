package com.dalouche.experiments.rockpaperscissors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ConsoleNumberOfRoundsProviderTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutInputStream() {
		new ConsoleNumberOfRoundsProvider(null, anyPrintStream());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutOutputStream() {
		new ConsoleNumberOfRoundsProvider(anyInputStream(), null);
	}

	@Test
	public void shouldAskForNumberOfRoundsAndInterpretResponse() {
		InputStream is = new ByteArrayInputStream(toBytes("50"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		NumberOfRoundsProvider numberOfRoundsProvider = numberOfRoundsProvider(is, os);
		assertThat(numberOfRoundsProvider.getNumberOfRounds(), is(50));
	}
	
	@Test
	public void shouldRetryWhenNumberIsNotUnderstood() {
		InputStream is = new ByteArrayInputStream(toBytes("oops \n 50"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		NumberOfRoundsProvider numberOfRoundsProvider = numberOfRoundsProvider(is, os);
		assertThat(numberOfRoundsProvider.getNumberOfRounds(), is(50));
	}
	
	@Test
	public void shouldRetryWhenNumberIsLessthanOne() {
		InputStream is = new ByteArrayInputStream(toBytes("-5 \n 50"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		NumberOfRoundsProvider numberOfRoundsProvider = numberOfRoundsProvider(is, os);
		assertThat(numberOfRoundsProvider.getNumberOfRounds(), is(50));
	}
	
	private ConsoleNumberOfRoundsProvider numberOfRoundsProvider(InputStream is, ByteArrayOutputStream os) {
		return new ConsoleNumberOfRoundsProvider(is, new PrintStream(os));
	}
	
	private byte[] toBytes(String string) {
		return string.getBytes();
	}
	
	private InputStream anyInputStream() {
		return System.in;
	}

	private PrintStream anyPrintStream() {
		return System.out;
	}
}
