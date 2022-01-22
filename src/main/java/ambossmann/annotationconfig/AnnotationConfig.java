package ambossmann.annotationconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(AnnotationConfig.MOD_ID)
public class AnnotationConfig {

	public static final String MOD_ID = "annotationconfig";
	public static final Logger LOGGER = LogManager.getLogger();

	public AnnotationConfig() {
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
				() -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
	}
}