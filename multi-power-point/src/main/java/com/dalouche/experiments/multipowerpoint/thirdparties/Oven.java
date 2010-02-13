package com.dalouche.experiments.multipowerpoint.thirdparties;

public final class Oven {

	private boolean heating = false;
	
	public void heatUp() {
		this.heating = true;
	}
	
	public void stopHeating() {
		this.heating = false;
	}
	
	public boolean isHeating() {
		return heating;
	}
}
