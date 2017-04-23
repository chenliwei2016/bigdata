package win.chenliwei.simplemvc.enhance.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import win.chenliwei.simplemvc.enhance.controller.Controller;
import win.chenliwei.simplemvc.enhance.controller.InputProductController;
import win.chenliwei.simplemvc.enhance.controller.SaveProductController;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 8083403188843403513L;

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
		
		String dispatchUri = null;
		if(action.equalsIgnoreCase("product_input.enhance")){
			Controller inputProduct = new InputProductController();
			dispatchUri = inputProduct.handleRequest(req, resp);
		} else if(action.equalsIgnoreCase("product_save.enhance")){
			Controller saveProduct = new SaveProductController();
			dispatchUri = saveProduct.handleRequest(req, resp);
		}
		
		if(dispatchUri != null){
			RequestDispatcher rd = req.getRequestDispatcher(dispatchUri);
			rd.forward(req, resp);
		}
	}

}
