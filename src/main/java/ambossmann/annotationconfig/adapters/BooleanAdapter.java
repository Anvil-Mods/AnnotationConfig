package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class BooleanAdapter extends AbstractAdapter<Boolean> {

	public BooleanAdapter(Boolean defaultValue) {
		super(defaultValue);
	}
	
	public void toggle() {
		setValue(!getValue());
	}
	
	public void enable() {
		setValue(true);
	}
	
	public void disable() {
		setValue(false);
	}

	@Override
	protected ConfigValue<Boolean> define(Builder builder, ConfigOption options) {
		return builder.define(options.name(), defaultValue);
	}
}
