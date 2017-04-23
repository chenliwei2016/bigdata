package win.chenliwei.simplemvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import win.chenliwei.simplemvc.model.Product;
import win.chenliwei.simplemvc.view.ProductForm;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 8169677901928669620L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		
		if(action.equalsIgnoreCase("product_input.action")){
		} else if(action.equalsIgnoreCase("product_save.action")){
			//create a form
			ProductForm productForm = new ProductForm();
			productForm.setName(req.getParameter("name"));
			productForm.setDescription(req.getParameter("description"));
			productForm.setPrice(req.getParameter("price"));
			
			//create a class
			Product product = new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setPrice(Float.parseFloat(productForm.getPrice()));
			req.setAttribute("product", product);
		}
		// forward to a view
		String dispatchUrl = null;
		if(action.equalsIgnoreCase("product_input.action")){
			dispatchUrl = "/WEB-INF/ProductForm.jsp";
		} else if (action.equalsIgnoreCase("product_save.action")){
			dispatchUrl = "/WEB-INF/ProductDetails.jsp";
		}
		if(dispatchUrl != null){
			RequestDispatcher rd = req.getRequestDispatcher(dispatchUrl);
			rd.forward(req, resp);
		}
		
	}

}
