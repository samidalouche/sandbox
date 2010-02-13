package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.multipowerpoint.PowerPoint;
import com.dalouche.experiments.multipowerpoint.thirdparties.Blender;

public final class BlenderPowerPointAdapter implements PowerPoint {

	private Blender blender;
	
	public BlenderPowerPointAdapter(Blender blender) {
		super();
		Validate.notNull(blender);
		this.blender = blender;
	}

	@Override
	public boolean isTurnedOn() {
		return blender.isBlending();
	}

	@Override
	public void turnOff() {
		blender.stopBlending();
	}

	@Override
	public void turnOn() {
		blender.swizzle();
	}

}
