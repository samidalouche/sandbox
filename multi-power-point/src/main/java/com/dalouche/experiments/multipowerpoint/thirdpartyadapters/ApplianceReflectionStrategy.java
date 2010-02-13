package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

public interface ApplianceReflectionStrategy {

	public abstract boolean isTurnedOn(Object appliance);

	public abstract void turnOff(Object appliance);

	public abstract void turnOn(Object appliance);

}