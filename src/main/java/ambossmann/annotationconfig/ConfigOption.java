package ambossmann.annotationconfig;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.minecraftforge.fml.config.ModConfig;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ConfigOption {
	
	ModConfig.Type type();
	
	String name();
	
	String[] comment();
	
	String translationKey() default "";
	
	boolean worldRestart() default false;
}
