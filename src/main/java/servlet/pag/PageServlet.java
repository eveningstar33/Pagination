package servlet.pag;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/getEmployees")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/company")
	private static DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String strPageID = request.getParameter("page");  
        int pageID = Integer.parseInt(strPageID);  
        int total = 5; 
        
        if(pageID != 1){ 
        
            pageID = pageID - 1;  
            pageID = pageID*total + 1;  
        }  
        
        EmployeeDAO dao = new EmployeeDAO(dataSource);
        
        List<Employee> listEmployees = dao.getEmployees(pageID, total); 
        
        out.print("<h1>Page No: " + strPageID + "</h1>");  
        out.print("<table border='1' cellpadding='4' width='60%'>");  
        out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");  
        
        for(Employee employee : listEmployees){  
        	
            out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() 
            + "</td><td>" + employee.getSalary() + "</td></tr>");  
        }  
        out.print("</table>");  
          
        out.print("<a href=\"ViewEmployees?page=1\">1</a> ");  
        out.print("<a href=\"ViewEmployees?page=2\">2</a> ");  
        out.print("<a href=\"ViewEmployees?page=3\">3</a> "); 
	}
}
