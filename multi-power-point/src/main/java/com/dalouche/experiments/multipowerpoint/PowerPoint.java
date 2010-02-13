package com.dalouche.experiments.multipowerpoint;


/**
 * The generic interface that must be implemented by every appliance 
 * (or by its adapter)
 * 
 * @author sdalouche
 *
 */
public interface PowerPoint {
	/**
	 * turn on the device
	 */
	void turnOn();
	/**
	 * turn off the device
	 */
	void turnOff();
	/**
	 * 
	 * @return whether the device is turned on
	 */
	boolean isTurnedOn();
}
