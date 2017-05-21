package win.chenliwei.simplespringmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import win.chenliwei.simplespringmvc.model.Employee;
import win.chenliwei.simplespringmvc.validator.EmployeeValidator;

@Controller
public class EmployeeController {
	private static final Log logger = LogFactory.getLog(EmployeeController.class);
	
	@RequestMapping(value="employee_input")
	public String inputEmployee(Model model){
		logger.info("invoke employee_input");
		model.addAttribute("employee",new Employee());
		return "EmployeeForm";
	}
	
	@RequestMapping(value="employee_save")
	public String saveEmployee(@ModelAttribute Employee employee,BindingResult bindingResult, Model model){
		logger.info("invoke employee_save");
		EmployeeValidator employeeValidator = new EmployeeValidator();
		employeeValidator.validate(employee, bindingResult);
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			logger.info(fieldError.getCode() + ":" + fieldError.getField());
			return "EmployeeForm";
		}
		return "EmployeeDetail";
	}
}
