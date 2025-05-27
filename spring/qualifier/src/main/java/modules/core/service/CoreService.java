package modules.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CoreService {
    
	Logger logger = LoggerFactory.getLogger(CoreService.class);

	private final List<QualifierService> qualifierServices;

	public CoreService(List<QualifierService> qualifierServices) {
		this.qualifierServices = qualifierServices;
	}

    public void doSomething() {
        logger.info("Core do something");

		qualifierServices.forEach(QualifierService::method1);
    }

}
