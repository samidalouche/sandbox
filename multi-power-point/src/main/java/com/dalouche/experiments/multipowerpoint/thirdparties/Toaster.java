package com.dalouche.experiments.multipowerpoint.thirdparties;

public final class Toaster {
	private boolean off = true;
	
	public void startToasting() {
		this.off = false;
	}
	public void stopToasting() {
		this.off = true;
	}
	public boolean isShutDown() {
		return off;
	}
}
