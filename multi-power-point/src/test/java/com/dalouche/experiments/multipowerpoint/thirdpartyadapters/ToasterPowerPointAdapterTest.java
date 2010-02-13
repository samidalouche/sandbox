package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.dalouche.experiments.multipowerpoint.thirdparties.Toaster;

public class ToasterPowerPointAdapterTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutToaster() {
		new ToasterPowerPointAdapter(null);
	}
	
	@Test
	public void shouldTurnOff() {
		Toaster toaster = new Toaster();
		toaster.startToasting();
		new ToasterPowerPointAdapter(toaster).turnOff();
		assertThat(toaster.isShutDown(), is(true));
	}
	
	@Test
	public void shouldTurnOn() {
		Toaster toaster = new Toaster();
		toaster.stopToasting();
		new ToasterPowerPointAdapter(toaster).turnOn();
		assertThat(toaster.isShutDown(), is(false));
	}
}
