package servlet.pag;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import servlet.pag.model.Employee;
import servlet.pag.dao.EmployeeDao; 

@WebServlet("/ViewEmployees")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDao employeeDao;
	
	@Resource(name="jdbc/company")
	private static DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			
			employeeDao = new EmployeeDao(dataSource);
		}
		catch (Exception exc) {
			
			throw new ServletException(exc);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {  
	        
	        String strPageID = request.getParameter("page");  
	        
			request.setAttribute("page", strPageID);
	        
	        int pageID = Integer.parseInt(strPageID);  
	        int total = 5; 
	        
	        if(pageID != 1){ 
	        
	            pageID = pageID - 1;  
	            pageID = pageID*total + 1;  
	        }  
	        
	        List<Employee> listEmployees = employeeDao.getEmployees(pageID, total);
			
	        for (Employee emp : listEmployees) {
	        	
	        	System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getSalary()); 
	        }
	        
			request.setAttribute("employees", listEmployees);
	        
			RequestDispatcher rd = request.getRequestDispatcher("showEmployee.jsp");
			rd.forward(request, response);
 		} catch (Exception exc) {
			
			throw new ServletException(exc);
		}
	}
}
