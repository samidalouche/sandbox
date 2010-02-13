package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.multipowerpoint.PowerPoint;
import com.dalouche.experiments.multipowerpoint.thirdparties.Oven;

public class OvenPowerPointAdapter implements PowerPoint {

	private Oven oven;
	
	public OvenPowerPointAdapter(Oven oven) {
		super();
		Validate.notNull(oven);
		this.oven = oven;
	}

	@Override
	public boolean isTurnedOn() {
		return oven.isHeating();
	}

	@Override
	public void turnOff() {
		oven.stopHeating();
	}

	@Override
	public void turnOn() {
		oven.heatUp();
	}

}
