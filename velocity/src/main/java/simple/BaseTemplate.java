package simple;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Properties;

public class BaseTemplate {

	public static void main(String... args) {

		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init( p );

		VelocityContext context = new VelocityContext();
		context.put("value", "value");

		Template template = null;

		try {
			template = Velocity.getTemplate("base_template.html");

			StringWriter sw = new StringWriter();

			template.merge(context, sw);

			System.out.println(sw.toString());

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
