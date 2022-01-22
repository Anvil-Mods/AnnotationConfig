package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class DoubleAdapter extends NumberAdapter<Double> {

	public DoubleAdapter(Double defaultValue, Double minValue, Double maxValue, float stepSizeIn, int decimalCountIn) {
		super(defaultValue, minValue, maxValue, stepSizeIn, decimalCountIn);
	}
	
	public void setValue(Number newValue) {
		setValue(newValue.doubleValue());
	}

	@Override
	protected ConfigValue<Double> define(Builder builder, ConfigOption options) {
		return builder.defineInRange(options.name(), defaultValue, minValue, maxValue);
	}

}
