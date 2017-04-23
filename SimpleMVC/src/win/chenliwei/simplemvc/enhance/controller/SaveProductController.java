package win.chenliwei.simplemvc.enhance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import win.chenliwei.simplemvc.model.Product;
import win.chenliwei.simplemvc.validator.ProductValidator;
import win.chenliwei.simplemvc.view.ProductForm;

public class SaveProductController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		ProductForm form = new ProductForm();
		form.setName(request.getParameter("name"));
		form.setDescription(request.getParameter("description"));
		form.setPrice(request.getParameter("price"));
		
		List<String> errors = new ProductValidator().validate(form);
		if(errors.size() > 0){
			//if cannot pass validator, will return to original page
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "/WEB-INF/ProductForm.jsp";
		}
		
		Product product = new Product();
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setPrice(Float.parseFloat(form.getPrice()));
		
		request.setAttribute("product", product);
		
		return "/WEB-INF/ProductDetails.jsp";
	}

}
