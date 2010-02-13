package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static com.dalouche.experiments.multipowerpoint.thirdpartyadapters.DefaultReflectionStrategy.defaultReflectionStrategy;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.multipowerpoint.PowerPoint;

public final class ReflectivePowerPointAdapter implements PowerPoint{
	private Object appliance;
	private ApplianceReflectionStrategy reflectionStrategy;

	public ReflectivePowerPointAdapter(Object appliance) {
		this(appliance, defaultReflectionStrategy());
	}

	public ReflectivePowerPointAdapter(Object appliance, ApplianceReflectionStrategy reflectionStrategy) {
		super();
		Validate.notNull(appliance);
		Validate.notNull(reflectionStrategy);
		this.appliance = appliance;
		this.reflectionStrategy = reflectionStrategy;
	}

	@Override
	public boolean isTurnedOn() {
		return reflectionStrategy.isTurnedOn(appliance);
	}

	@Override
	public void turnOff() {
		reflectionStrategy.turnOff(appliance);
	}

	@Override
	public void turnOn() {
		reflectionStrategy.turnOn(appliance);
	}
}
