package ambossmann.annotationconfig.adapters;

import ambossmann.annotationconfig.ConfigManager;
import ambossmann.annotationconfig.ConfigOption;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public abstract class AbstractAdapter<T> {
	
	private ConfigManager configManager;
	private ConfigValue<T> value;
	
	private volatile boolean built;
	
	protected final T defaultValue;
	
	protected AbstractAdapter(T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public void setValue(T newValue) {
		value.set(newValue);
	}
	
	public T getValue() {
		return value.get();
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public void setConfigManager(ConfigManager configManager) {
		if (this.configManager == null) {
			this.configManager = configManager;
		} else {
			throw new UnsupportedOperationException("The ConfigManager can only be set once!");
		}
	}
	
	public ConfigValue<T> build(ForgeConfigSpec.Builder builder, ConfigOption options) {
		if (!built) {
			builder.comment(options.comment());
			if (!options.translationKey().isEmpty()) {
				builder.translation(options.translationKey());
			}
			if (options.worldRestart()) {
				builder.worldRestart();
			}
			built = true;
			ConfigValue<T> definedValue = define(builder, options);
			value = definedValue;
			return definedValue;
		} else {
			throw new UnsupportedOperationException("Can only be built once");
		}
	}
	
	protected abstract ConfigValue<T> define(ForgeConfigSpec.Builder builder, ConfigOption options);
	
}
