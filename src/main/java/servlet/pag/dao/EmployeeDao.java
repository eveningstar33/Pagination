package servlet.pag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import servlet.pag.model.Employee;


public class EmployeeDao { 
	
	private DataSource dataSource;

	public EmployeeDao(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}

    public List<Employee> getEmployees(int start, int total) throws Exception {  
    	
        List<Employee> list = new ArrayList<Employee>();  
        String query = "SELECT * FROM employee LIMIT " + (start - 1) + ", " + total;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try{  
        	
        	myConn = dataSource.getConnection(); 
        	myStmt = myConn.prepareStatement(query);  
        	myRs = myStmt.executeQuery();
            
            while(myRs.next()) {
 
                Employee employee = new Employee();  
                employee.setId(myRs.getInt(1));  
                employee.setName(myRs.getString(2));  
                employee.setSalary(myRs.getFloat(3));  
                list.add(employee);  
            }  
              
        } finally {
			
			try {
				
				if (myRs != null) {
					
					myRs.close();
				}
				
				if (myStmt != null) {
					
					myStmt.close();
				}
				
				if (myConn != null) {
					
					myConn.close();
				}
			} catch (SQLException e) {

				System.out.println(e);
			}
        }
        return list;  
    }  
}