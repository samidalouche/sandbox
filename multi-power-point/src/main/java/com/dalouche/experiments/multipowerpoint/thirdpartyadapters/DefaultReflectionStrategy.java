package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import static org.fest.reflect.core.Reflection.method;

import org.apache.commons.lang.Validate;

public final class DefaultReflectionStrategy implements ApplianceReflectionStrategy {
	private String turnOnMethod = "turnOn";
	private String turnOffMethod = "turnOff";
	private String isTurnedOnMethod = "isTurnedOn";
	private String isTurnedOffMethod = null;
	
	public static DefaultReflectionStrategy defaultReflectionStrategy() {
		return new DefaultReflectionStrategy();
	}
	
	private DefaultReflectionStrategy() { }
	private DefaultReflectionStrategy(String turnOnMethod, String turnOffMethod, String isTurnedOnMethod, String isTurnedOffMethod) {
		super();
		Validate.notNull(turnOnMethod);
		Validate.notNull(turnOffMethod);
		Validate.notNull(isTurnedOnMethod);
		this.turnOnMethod = turnOnMethod;
		this.turnOffMethod = turnOffMethod;
		this.isTurnedOnMethod = isTurnedOnMethod;
		this.isTurnedOffMethod = isTurnedOffMethod;
	}

	public DefaultReflectionStrategy withTurnOnMethod(String turnOnMethod){
		return new DefaultReflectionStrategy(turnOnMethod, turnOffMethod, isTurnedOnMethod, null);
	}
	
	public DefaultReflectionStrategy withTurnOffMethod(String turnOffMethod){
		return new DefaultReflectionStrategy(turnOnMethod, turnOffMethod, isTurnedOnMethod, isTurnedOffMethod);
	}
	
	public DefaultReflectionStrategy withIsTurnedOnMethod(String isTurnedOnMethod){
		return new DefaultReflectionStrategy(turnOnMethod, turnOffMethod, isTurnedOnMethod, isTurnedOffMethod);
	}
	
	public DefaultReflectionStrategy withIsTurnedOffMethod(String isTurnedOffMethod){
		return new DefaultReflectionStrategy(turnOnMethod, turnOffMethod, isTurnedOnMethod, isTurnedOffMethod);
	}
	
	@Override
	public boolean isTurnedOn(Object appliance) {
		if(shouldUseTurnedOffMethod()) {
			return method(isTurnedOnMethod)
				.withReturnType(boolean.class)
				.in(appliance)
				.invoke();	
		} else {
			return ! method(isTurnedOffMethod)
				.withReturnType(boolean.class)
				.in(appliance)
				.invoke();
		}
		
	}

	private boolean shouldUseTurnedOffMethod() {
		return isTurnedOffMethod == null;
	}

	@Override
	public void turnOff(Object appliance) {
		method(turnOffMethod)
			.in(appliance)
			.invoke();
	}

	@Override
	public void turnOn(Object appliance) {
		method(turnOnMethod)
		.in(appliance)
		.invoke();
	}
}
