package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.multipowerpoint.PowerPoint;
import com.dalouche.experiments.multipowerpoint.thirdparties.Toaster;

public class ToasterPowerPointAdapter implements PowerPoint {

	private Toaster toaster;
	
	public ToasterPowerPointAdapter(Toaster toaster) {
		super();
		Validate.notNull(toaster);
		this.toaster = toaster;
	}

	@Override
	public boolean isTurnedOn() {
		return ! toaster.isShutDown();
	}

	@Override
	public void turnOff() {
		toaster.stopToasting();
	}

	@Override
	public void turnOn() {
		toaster.startToasting();
	}

}
