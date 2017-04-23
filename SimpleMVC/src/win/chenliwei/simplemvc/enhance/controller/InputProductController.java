package win.chenliwei.simplemvc.enhance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputProductController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		return "/WEB-INF/ProductForm.jsp";
	}

}
