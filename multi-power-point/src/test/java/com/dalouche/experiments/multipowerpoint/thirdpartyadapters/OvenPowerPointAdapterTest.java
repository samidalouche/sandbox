package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.dalouche.experiments.multipowerpoint.thirdparties.Oven;

public class OvenPowerPointAdapterTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutOven() {
		new OvenPowerPointAdapter(null);
	}
	
	@Test
	public void shouldTurnOff() {
		Oven oven = new Oven();
		oven.heatUp();
		new OvenPowerPointAdapter(oven).turnOff();
		assertThat(oven.isHeating(), is(false));
	}
	
	@Test
	public void shouldTurnOn() {
		Oven oven = new Oven();
		oven.stopHeating();
		new OvenPowerPointAdapter(oven).turnOn();
		assertThat(oven.isHeating(), is(true));
	}
}
