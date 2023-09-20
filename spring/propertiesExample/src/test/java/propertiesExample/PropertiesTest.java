package propertiesExample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import propertiesExample.service.EnvironmentFieldService;
import propertiesExample.service.OtherPropertiesService;
import propertiesExample.service.PropertiesService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PropertiesTest {

	@Autowired
	private PropertiesService propertiesService;
	@Autowired
	private OtherPropertiesService otherPropertiesService;

	@Autowired
	private EnvironmentFieldService environmentFieldService;

	@Test
	public void test() {

		Assert.assertEquals("field1", propertiesService.getField1());
		Assert.assertEquals("field2", propertiesService.getField2());
		Assert.assertEquals("nestedfield1", propertiesService.getNested().getField1());
		Assert.assertEquals("nestedfield2", propertiesService.getNested().getField2());

		Assert.assertEquals("nopropertiesfield1", otherPropertiesService.getField1());
		Assert.assertEquals("nopropertiesfield2", otherPropertiesService.getField2());
	}

	@Test
	public void enviromentFieldTest() {
		Assert.assertEquals("environmentfield1", environmentFieldService.getEnvironmentField());
	}
}
