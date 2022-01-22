package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class IntegerAdapter extends NumberAdapter<Integer> {

	public IntegerAdapter(Integer defaultValue, Integer minValue,
			Integer maxValue, float stepSizeIn) {
		super(defaultValue, minValue, maxValue, stepSizeIn, 0);
	}
	
	@Override
	public void setValue(Number newValue) {
		setValue(newValue.intValue());
	}

	@Override
	protected ConfigValue<Integer> define(Builder builder, ConfigOption options) {
		return builder.defineInRange(options.name(), defaultValue, minValue, maxValue);
	}

}
