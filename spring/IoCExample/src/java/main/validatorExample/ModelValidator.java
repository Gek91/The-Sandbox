package validatorExample;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ModelValidator implements Validator {

	private final SubModelValidator subModelValidator;

	public ModelValidator(SubModelValidator subModelValidator) {
		this.subModelValidator = subModelValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ModelValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Model model = (Model) target;
		if(model.getField1() > 10) {
			errors.rejectValue("field1", "major 10");
		}

		ValidationUtils.rejectIfEmpty(errors, "field2", "is empty");

		try {
			errors.pushNestedPath("field3");
			ValidationUtils.invokeValidator(this.subModelValidator, model.getField3(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}
