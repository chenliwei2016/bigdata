package win.chenliwei.simplemvc.enhance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import win.chenliwei.simplemvc.model.Product;
import win.chenliwei.simplemvc.view.ProductForm;

public class SaveProductController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		ProductForm form = new ProductForm();
		form.setName(request.getParameter("name"));
		form.setDescription(request.getParameter("description"));
		form.setPrice(request.getParameter("price"));
		
		Product product = new Product();
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setPrice(Float.parseFloat(form.getPrice()));
		
		request.setAttribute("product", product);
		
		return "/WEB-INF/ProductDetails.jsp";
	}

}
