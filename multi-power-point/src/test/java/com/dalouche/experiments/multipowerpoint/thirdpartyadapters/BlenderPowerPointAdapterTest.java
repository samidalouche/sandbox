package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.dalouche.experiments.multipowerpoint.thirdparties.Blender;

public class BlenderPowerPointAdapterTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutBlender() {
		new BlenderPowerPointAdapter(null);
	}
	
	@Test
	public void shouldTurnOff() {
		Blender blender = new Blender();
		blender.swizzle();
		new BlenderPowerPointAdapter(blender).turnOff();
		assertThat(blender.isBlending(), is(false));
	}
	
	@Test
	public void shouldTurnOn() {
		Blender blender = new Blender();
		blender.stopBlending();
		new BlenderPowerPointAdapter(blender).turnOn();
		assertThat(blender.isBlending(), is(true));
	}
}
