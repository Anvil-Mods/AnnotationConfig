package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class LongAdapter extends NumberAdapter<Long> {

	public LongAdapter(Long defaultValue, Long minValue, Long maxValue, float stepSizeIn) {
		super(defaultValue, minValue, maxValue, stepSizeIn, 0);
	}
	
	@Override
	public void setValue(Number newValue) {
		setValue(newValue.longValue());
	}

	@Override
	protected ConfigValue<Long> define(Builder builder, ConfigOption options) {
		return builder.defineInRange(options.name(), defaultValue, minValue, maxValue);
	}

}
