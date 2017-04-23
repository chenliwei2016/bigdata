package win.chenliwei.simplemvc.validator;

import java.util.List;
import java.util.ArrayList;

import win.chenliwei.simplemvc.view.ProductForm;

public class ProductValidator {
	
	public List<String> validate(ProductForm form){
		List<String> errors = new ArrayList<String>();
		String name = form.getName();
		if(name == null || name.equals("")){
			errors.add("Product must have a name");
		}
		
		String price = form.getPrice();

		if(price == null || price.equals("")){
			errors.add("Product must have a price");
		} else {
			try{
				Float.parseFloat(price);
			} catch(Exception e){
				errors.add("price is not valid");
			}
		}
		return errors;
	}

}
