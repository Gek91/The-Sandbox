package modules.core;

import modules.core.service.CoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestQualifier {

	@Autowired
	private CoreService coreService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testQualifier() {
		coreService.doSomething();
	}

}
