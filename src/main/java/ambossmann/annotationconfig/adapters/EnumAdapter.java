package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class EnumAdapter<T extends Enum<T>> extends AbstractAdapter<T> {

	public EnumAdapter(T defaultValue) {
		super(defaultValue);
	}
	
	public void setValue(T newValue) {
		super.setValue(newValue);
	}

	@Override
	protected ConfigValue<T> define(Builder builder, ConfigOption options) {
		return builder.defineEnum(options.name(), defaultValue);
	}

}
