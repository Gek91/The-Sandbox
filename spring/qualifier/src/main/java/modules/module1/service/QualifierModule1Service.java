package modules.module1.service;

import modules.core.service.QualifierService;
import modules.module1.Module1Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QualifierModule1Service extends QualifierService {

	Logger logger = LoggerFactory.getLogger(QualifierModule1Service.class);

	public void method1() {
		logger.info("MODULE 1 method 1");
	}
}
