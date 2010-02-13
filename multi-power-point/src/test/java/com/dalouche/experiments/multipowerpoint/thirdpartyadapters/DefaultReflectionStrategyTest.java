package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static com.dalouche.experiments.multipowerpoint.thirdpartyadapters.DefaultReflectionStrategy.defaultReflectionStrategy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class DefaultReflectionStrategyTest {

	private interface ApplianceWithDefaultNaming {
		void turnOn();
		void turnOff();
		boolean isTurnedOn();
	}
	
	private interface ApplianceCustomizedNaming {
		void start();
		void stop();
		boolean isStarted();
	}
	
	private interface ApplianceWithIsStoppedMethodNaming {
		void start();
		void stop();
		boolean isStopped();
	}
	
	@Test
	public void shouldInvokeDefaultTurnOnMethod() {
		ApplianceWithDefaultNaming appliance = mock(ApplianceWithDefaultNaming.class); 
		defaultReflectionStrategy().turnOn(appliance);
		Mockito.verify(appliance).turnOn();
	}
	@Test
	public void shouldInvokeDefaultTurnOffMethod() {
		ApplianceWithDefaultNaming appliance =  mock(ApplianceWithDefaultNaming.class); 
		defaultReflectionStrategy().turnOff(appliance);
		Mockito.verify(appliance).turnOff();
	}
	@Test
	public void shouldInvokeDefaultIsTurnedOnMethod() {
		ApplianceWithDefaultNaming appliance =  mock(ApplianceWithDefaultNaming.class); 
		when(appliance.isTurnedOn()).thenReturn(true);
		assertTrue(defaultReflectionStrategy().isTurnedOn(appliance));
		Mockito.verify(appliance).isTurnedOn();
	}
	
	@Test
	public void shouldInvokeCustomizedTurnOnMethod() {
		ApplianceCustomizedNaming appliance =  mock(ApplianceCustomizedNaming.class); 
		customizedStrategy().turnOn(appliance);
		Mockito.verify(appliance).start();
	}
	
	@Test
	public void shouldInvokeCustomizedTurnOffMethod() {
		ApplianceCustomizedNaming appliance =  mock(ApplianceCustomizedNaming.class); 
		customizedStrategy().turnOff(appliance);
		Mockito.verify(appliance).stop();
	}
	@Test
	public void shouldInvokeCustomizedIsTurnedOnMethod() {
		ApplianceCustomizedNaming appliance =  mock(ApplianceCustomizedNaming.class); 
		when(appliance.isStarted()).thenReturn(true);
		assertTrue(customizedStrategy().isTurnedOn(appliance));
		Mockito.verify(appliance).isStarted();
	}
	
	
	@Test
	public void shouldNegateResultOfIsTurnedOffMethodMethod() {
		ApplianceWithIsStoppedMethodNaming appliance =  mock(ApplianceWithIsStoppedMethodNaming.class); 
		when(appliance.isStopped()).thenReturn(true);
		assertFalse(customizedStrategy().withIsTurnedOffMethod("isStopped").isTurnedOn(appliance));
		Mockito.verify(appliance).isStopped();
	}
	
	private DefaultReflectionStrategy customizedStrategy() {
		return defaultReflectionStrategy()
		.withTurnOnMethod("start")
		.withTurnOffMethod("stop")
		.withIsTurnedOnMethod("isStarted");
	}
	
}
