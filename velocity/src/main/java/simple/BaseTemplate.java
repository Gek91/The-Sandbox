package simple;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

public class BaseTemplate {

	public static void main(String... args) {

		Velocity.init();

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
