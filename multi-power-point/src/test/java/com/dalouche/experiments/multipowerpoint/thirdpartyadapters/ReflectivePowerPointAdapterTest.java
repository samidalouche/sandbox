package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

import com.dalouche.experiments.multipowerpoint.PowerPoint;

public class ReflectivePowerPointAdapterTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutAppliance() {
		new ReflectivePowerPointAdapter(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreatewithoutApplianceReflectionStrategy() {
		new ReflectivePowerPointAdapter(anyAppliance(), null);
	}
	
	@Test
	public void shouldDelegateIsTurnedOnToStrategy() {
		Object myAppliance = myAppliance();
		
		ApplianceReflectionStrategy strategy = mock(ApplianceReflectionStrategy.class);
		Mockito.when(strategy.isTurnedOn(myAppliance)).thenReturn(true);
		
		PowerPoint reflectivePowerPointAdapter = new ReflectivePowerPointAdapter(myAppliance, strategy);
		assertTrue(reflectivePowerPointAdapter.isTurnedOn());
		
		verify(strategy).isTurnedOn(Mockito.same(myAppliance));
	}
	
	@Test
	public void shouldDelegateTurnOnToStrategy() {
		Object myAppliance = myAppliance();
		
		ApplianceReflectionStrategy strategy = mock(ApplianceReflectionStrategy.class);
		
		PowerPoint reflectivePowerPointAdapter = new ReflectivePowerPointAdapter(myAppliance, strategy);
		reflectivePowerPointAdapter.turnOn();
		
		verify(strategy).turnOn(Mockito.same(myAppliance));
	}
	
	@Test
	public void shouldDelegateTurnOffToStrategy() {
		Object myAppliance = myAppliance();
		
		ApplianceReflectionStrategy strategy = mock(ApplianceReflectionStrategy.class);
		
		PowerPoint reflectivePowerPointAdapter = new ReflectivePowerPointAdapter(myAppliance, strategy);
		reflectivePowerPointAdapter.turnOff();
		
		verify(strategy).turnOff(Mockito.same(myAppliance));
	}

	private Object anyAppliance() {
		return "any appliance";
	}
	
	private Object myAppliance() {
		return "my appliance";
	}
}
