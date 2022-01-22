package ambossmann.annotationconfig;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;

import org.apache.commons.lang3.tuple.Pair;

import ambossmann.annotationconfig.adapters.AbstractAdapter;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigManager {

	private String modId;
	private EnumMap<ModConfig.Type, ConfigHolder> configHolderMap;

	public ConfigManager() {
		this.modId = ModLoadingContext.get().getActiveNamespace();
		configHolderMap = new EnumMap<>(ModConfig.Type.class);
		for (ModConfig.Type type : ModConfig.Type.values()) {
			configHolderMap.put(type, new ConfigHolder());
		}
	}

	public void registerField(Field setting) {
		int fieldModifiers = setting.getModifiers();
		try {
			if (setting.isAnnotationPresent(ConfigOption.class)) {
				if (AbstractAdapter.class.isAssignableFrom(setting.getType())) {
					if (Modifier.isFinal(fieldModifiers) && Modifier.isStatic(fieldModifiers)
							&& Modifier.isPublic(fieldModifiers)) {
						ConfigOption configOption = setting.getAnnotation(ConfigOption.class);
						AbstractAdapter<?> configValue = (AbstractAdapter<?>) setting.get(null);
						configHolderMap.get(configOption.type()).add(configValue, configOption);
					} else {
						throw new IllegalArgumentException(
								"Field " + setting + " annotated with @ConfigOption is not public static final!");
					}
				} else {
					throw new IllegalArgumentException(
							"Field " + setting + " annotated with @ConfigOption does not extend "
									+ AbstractAdapter.class.getSimpleName() + " !");
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void registerFields(Collection<Field> fields) {
		fields.forEach(this::registerField);
	}

	public void registerClass(Class<?> clazz) {
		Arrays.stream(clazz.getFields()).filter(field -> Modifier.isStatic(field.getModifiers()))
				.forEach(field -> registerField(field));
	}

	public void registerClasses(Collection<Class<?>> clazzes) {
		clazzes.forEach(this::registerClass);
	}

	public void finish() {
		for (ModConfig.Type type : ModConfig.Type.values()) {
			if (!configHolderMap.get(type).isEmpty()) {
				Pair<ConfigHolder, ForgeConfigSpec> specPair = configHolderMap.get(type).build();
				ModLoadingContext.get().registerConfig(type, specPair.getRight());
			}
		}
		AnnotationConfig.LOGGER.info("Registration of Configs for " + modId + " finished sucessfully.");
	}

}
