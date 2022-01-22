package ambossmann.annotationconfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import ambossmann.annotationconfig.adapters.AbstractAdapter;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigHolder {

	private Pair<ConfigHolder, ForgeConfigSpec> specPair;
	private Map<AbstractAdapter<?>, MutablePair<ConfigOption, ConfigValue<?>>> configMap;

	public ConfigHolder() {
		configMap = new HashMap<>();
	}

	public void add(AbstractAdapter<?> adapter, ConfigOption options) {
		MutablePair<ConfigOption, ConfigValue<?>> pair = new MutablePair<>();
		pair.setLeft(options);
		configMap.put(adapter, pair);
	}

	public Pair<ConfigHolder, ForgeConfigSpec> build() {
		specPair = new ForgeConfigSpec.Builder().configure(this::build0);
		return specPair;
	}
	
	private ConfigHolder build0(ForgeConfigSpec.Builder builder) {
		for (Map.Entry<AbstractAdapter<?>, MutablePair<ConfigOption, ConfigValue<?>>> entry : configMap.entrySet()) {
			AbstractAdapter<?> key = entry.getKey();
			MutablePair<ConfigOption, ConfigValue<?>> val = entry.getValue();
			val.setRight(key.build(builder, val.getLeft()));
		}
		return this;
	}

	public boolean isEmpty() {
		return configMap.isEmpty();
	}

}
