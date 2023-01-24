package validatorExample;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SubModelValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Submodel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "field1", "field is empty");
	}
}
