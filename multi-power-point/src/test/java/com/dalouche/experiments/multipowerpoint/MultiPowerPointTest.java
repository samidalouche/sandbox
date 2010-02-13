package com.dalouche.experiments.multipowerpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;

public class MultiPowerPointTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateMultiPowerPointWithNullAplliances() {
		new MultiPowerPoint((Iterable<PowerPoint>)null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateMultiPowerPointWithEmptyListOfAplliances() {
		new MultiPowerPoint(ImmutableList.<PowerPoint>of());
	}
	
	@Test
	public void shouldBeTurnedOnIfAnyOfTheDevicesIsOn() {
		PowerPoint multiPowerPoint = new MultiPowerPoint(applianceThatIsTurnedOff(), applianceThatIsTurnedOn());
		assertThat(multiPowerPoint.isTurnedOn(), is(true));
	}
	
	@Test
	public void shouldBeTurnedOnAllIfTheDevicesAreOn() {
		PowerPoint multiPowerPoint = new MultiPowerPoint(applianceThatIsTurnedOn(), applianceThatIsTurnedOn());
		assertThat(multiPowerPoint.isTurnedOn(), is(true));
	}
	
	@Test
	public void shouldBeTurnedOffIfTheDevicesAreOff() {
		PowerPoint multiPowerPoint = new MultiPowerPoint(applianceThatIsTurnedOff(), applianceThatIsTurnedOff());
		assertThat(multiPowerPoint.isTurnedOn(), is(false));
	}
	
	@Test
	public void shouldTurnOnAllDevices() {
		PowerPoint appliance1 = mock(PowerPoint.class);
		PowerPoint appliance2 = mock(PowerPoint.class);
		PowerPoint multiPowerPoint = new MultiPowerPoint(appliance1, appliance2);
		multiPowerPoint.turnOn();
		Mockito.verify(appliance1).turnOn();
		Mockito.verify(appliance2).turnOn();
	}
	
	@Test
	public void shouldTurnOffAllDevices() {
		PowerPoint appliance1 = mock(PowerPoint.class);
		PowerPoint appliance2 = mock(PowerPoint.class);
		PowerPoint multiPowerPoint = new MultiPowerPoint(appliance1, appliance2);
		multiPowerPoint.turnOff();
		Mockito.verify(appliance1).turnOff();
		Mockito.verify(appliance2).turnOff();
	}

	private PowerPoint applianceThatIsTurnedOn() {
		PowerPoint applianceThatIsturnedOn = mock(PowerPoint.class);
		when(applianceThatIsturnedOn.isTurnedOn()).thenReturn(true);
		return applianceThatIsturnedOn;
	}

	private PowerPoint applianceThatIsTurnedOff() {
		PowerPoint applianceThatIsTurnedOff = mock(PowerPoint.class);
		when(applianceThatIsTurnedOff.isTurnedOn()).thenReturn(false);
		return applianceThatIsTurnedOff;
	}
}
