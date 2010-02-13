package com.dalouche.experiments.multipowerpoint;

import static com.dalouche.experiments.multipowerpoint.thirdpartyadapters.Adapters.adapter;
import static com.dalouche.experiments.multipowerpoint.thirdpartyadapters.DefaultReflectionStrategy.defaultReflectionStrategy;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dalouche.experiments.multipowerpoint.thirdparties.Blender;
import com.dalouche.experiments.multipowerpoint.thirdparties.Oven;
import com.dalouche.experiments.multipowerpoint.thirdparties.Toaster;
import com.dalouche.experiments.multipowerpoint.thirdpartyadapters.DefaultReflectionStrategy;
import com.google.common.collect.ImmutableList;

public class IntegrationTest {

	@Test
	public void shouldDemonstrateHowtoUseSpecificAndGenericAdapters() {
		Blender blender = new Blender();
		Oven oven1 = new Oven();
		Oven oven2 = new Oven();
		Toaster toaster = new Toaster();
		
		PowerPoint multiPowerPoint = 
			new MultiPowerPoint(
				adapter(blender),
				adapter(oven1),
				adapter(toaster),
				adapter(oven2, reflectionStrategyForOven())
				);
		multiPowerPoint.turnOn();
		
		for(boolean turnedOn : statuses(blender, oven1, oven2, toaster)) {
			assertTrue(turnedOn);
		}
	}

	private DefaultReflectionStrategy reflectionStrategyForOven() {
		return defaultReflectionStrategy()
			.withTurnOnMethod("heatUp")
			.withTurnOffMethod("stopHeating")
			.withIsTurnedOnMethod("isHeating");
	}

	private ImmutableList<Boolean> statuses(Blender blender, Oven oven1,
			Oven oven2, Toaster toaster) {
		return ImmutableList.of(
			blender.isBlending(), 
			oven1.isHeating(), 
			oven2.isHeating(), 
			!toaster.isShutDown());
	}
}
