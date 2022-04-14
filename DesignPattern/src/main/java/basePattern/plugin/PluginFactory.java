package basePattern.plugin;

import java.io.FileInputStream;
import java.util.Properties;

public class PluginFactory {

	private static Properties properties = new Properties();
	
	static {
		try {
			String propertiesFile = System.getProperty("plugins");
			properties.load(new FileInputStream(propertiesFile));
		} catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Object getPlugin(Class interfaceClass) {
		
		String implementationName = properties.getProperty(interfaceClass.getName());
		if(implementationName == null) {
			throw new RuntimeException("Implementation not specified for " + interfaceClass.getName() + " in PluginFactory properties.");
		}
		
		try {
			return Class.forName(implementationName).newInstance();
		} catch(Exception e) {
			throw new RuntimeException("Factory unable to construct instance of " + interfaceClass.getName());
		}
	}
}
