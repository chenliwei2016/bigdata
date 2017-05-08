package win.chenliwei.simplemvc.enhance.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import win.chenliwei.simplemvc.database.ConnMysql;
import win.chenliwei.simplemvc.model.Employee;
import win.chenliwei.simplemvc.view.QuerySalaryForm;

public class QuerySalaryController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		QuerySalaryForm queryForm = new QuerySalaryForm();
		queryForm.setName(request.getParameter("name"));
//		String path = request.getServletContext().getRealPath("/");
//		System.out.println(path);
//		//request.getContextPath();
//		try {
//			ConnMysql.getProperties(path);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Connection conn = ConnMysql.conn();

		try {
			ResultSet rs = conn.createStatement().executeQuery("select salary from employee where name='" + queryForm.getName() + "'");
			float salary = 0;
			while(rs.next()) salary = rs.getFloat(1);
			rs.close();
			Employee emp = new Employee();
			emp.setName(queryForm.getName());
			emp.setSalary(salary);
			
			request.setAttribute("emp", emp);
			
			return "/WEB-INF/SalaryDetails.jsp";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return null;
	}

}
