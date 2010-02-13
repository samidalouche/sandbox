package com.dalouche.experiments.multipowerpoint;

import java.util.Arrays;

import org.apache.commons.lang.Validate;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * @see composite design pattern
 * @author sdalouche
 *
 */
public final class MultiPowerPoint implements PowerPoint {

	private ImmutableList<PowerPoint> appliances;
	
	public MultiPowerPoint(PowerPoint...appliances) {
		this(Arrays.asList(appliances));
	}
	
	public MultiPowerPoint(Iterable<PowerPoint> appliances) {
		super();
		Validate.notNull(appliances);
		
		this.appliances = ImmutableList.copyOf(appliances);
		Validate.notEmpty(this.appliances);
	}

	@Override
	public boolean isTurnedOn() {
		return Iterables.any(this.appliances, new Predicate<PowerPoint>() {

			@Override
			public boolean apply(PowerPoint input) {
				return input.isTurnedOn();
			}
		});
	}

	@Override
	public void turnOff() {
		this.turn(off());
	}

	
	@Override
	public void turnOn() {
		this.turn(on());
	}

	@Override
	public void turn(boolean on) {
		for(PowerPoint appliance: this.appliances) {
			appliance.turn(on);
		}
	}

	private boolean off() {
		return false;
	}

	private boolean on() {
		return true;
	}

}
