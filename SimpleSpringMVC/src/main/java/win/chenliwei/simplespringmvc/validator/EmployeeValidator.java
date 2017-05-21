package win.chenliwei.simplespringmvc.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import win.chenliwei.simplespringmvc.model.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee = (Employee) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "emloyee.name.required");
		ValidationUtils.rejectIfEmpty(errors, "birthday", "employee.birthday.required");
		Date birthday = employee.getBirthday();
		if(birthday != null && birthday.after(new Date())) errors.rejectValue("birthday", "employee.birthday.beforeNow");
		
	}
	
}
