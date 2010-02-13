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
	 * Turns the device on or off
	 * @param on true if the device is to be turned on, false otherwise
	 */
	void turn(boolean on);
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
