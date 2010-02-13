package com.dalouche.experiments.multipowerpoint.thirdparties;

public final class Blender {

	private boolean on = false;
	
	public void swizzle() {
		this.on = true;
	}
	
	public void stopBlending() {
		this.on = false;
	}
	
	public boolean isBlending() {
		return on;
	}
}
