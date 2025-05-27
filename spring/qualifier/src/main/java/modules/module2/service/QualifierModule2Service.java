package modules.module2.service;

import modules.core.service.QualifierService;
import modules.module1.service.QualifierModule1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QualifierModule2Service extends QualifierService {

	Logger logger = LoggerFactory.getLogger(QualifierModule2Service.class);

	public void method1() {
		logger.info("MODULE 2 method 1");
	}
}
