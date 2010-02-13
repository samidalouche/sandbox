package com.dalouche.experiments.multipowerpoint.thirdpartyadapters;

import com.dalouche.experiments.multipowerpoint.PowerPoint;
import com.dalouche.experiments.multipowerpoint.thirdparties.Blender;
import com.dalouche.experiments.multipowerpoint.thirdparties.Oven;
import com.dalouche.experiments.multipowerpoint.thirdparties.Toaster;

public class Adapters {
	public static PowerPoint adapter(Blender blender) {
		return new BlenderPowerPointAdapter(blender);
	}
	public static PowerPoint adapter(Oven oven) {
		return new OvenPowerPointAdapter(oven);
	}
	public static PowerPoint adapter(Toaster toaster) {
		return new ToasterPowerPointAdapter(toaster);
	}
	public static PowerPoint adapter(
		Object appliance, 
		ApplianceReflectionStrategy reflectionStrategy) {
		return new ReflectivePowerPointAdapter(appliance, reflectionStrategy);
	}
}
