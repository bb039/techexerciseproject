

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Contactmaker
 */
@WebServlet("/Contactmaker")
public class Contactmaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String dbDriver = "com.mysql.cj.jdbc.Driver"; 
	static String url = "jdbc:mysql://ec2-18-223-134-219.us-east-2.compute.amazonaws.com:3306/techDB?allowPublicKeyRetrieval=true&useSSL=false";
	static String user = "newmysqlremoteuser"; 
	static String password = "mypassword";
	Connection connection = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contactmaker() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		try {
	         Class.forName(dbDriver);
	      } catch (ClassNotFoundException e) {
	         System.out.println("The MySQL JDBC Driver is missing :(");
	         e.printStackTrace();
	         return;
	      }
	      connection = null;
	      //this changes the connection from null to the sql database
	      try {
	          connection = DriverManager.getConnection(url, user, password);
	          connection.setCatalog("techDB"); 
	       }  
	      catch (SQLException e) {
	          System.out.println("Connection Failed! Check output console");
	          e.printStackTrace();
	          return;
	       }
	      //as long as the connection isn't null, you can now use it for sql queries
	      if (connection != null) {
	          //response.getWriter().println("Connected to database successfully.<br>");
	       }
	      else {
	          System.out.println("Failed to make connection!");
	      }
	      try {
	    	  String sqlcommand = "select * from contacts";
	    	  PreparedStatement prepState = connection.prepareStatement(sqlcommand);
	    	  ResultSet rs = prepState.executeQuery();
	    	  
	    	  while(rs.next()) {
		    	  StringBuilder returnString = new StringBuilder();
		    	  
		    	  returnString.append("<div style=\"border:thin; border-style: solid; border-color: black; \"name= \"" + rs.getString("id") + "\">");
		    	  returnString.append("<br>" + rs.getString("first_name")+"<br>");
	    		  returnString.append("<br>" + rs.getString("last_name") + "<br>");
	    		  returnString.append("<br>" + rs.getString("email") + "<br>");
	    		  returnString.append("<br>" + rs.getString("phone_number") + "<br>");
	    		  returnString.append("<br>" + rs.getString("address") + "<br>");
	    		  returnString.append("</div><br>");
		    	  response.getWriter().append(returnString);
		    	  
	    	  }
	      }
	      
	      
	      catch(Exception e) {
	    	  response.getWriter().println("MySQL exception occured. <br>");
	    	  e.printStackTrace();
	      }
	}
	      
	      

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	      } catch (ClassNotFoundException e) {
	         System.out.println("The MySQL JDBC Driver is missing :(");
	         e.printStackTrace();
	         return;
	      }
	      connection = null;
	      //this changes the connection from null to the sql database
	      try {
	          connection = DriverManager.getConnection(url, user, password);
	          connection.setCatalog("techDB"); 
	       } catch (SQLException e) {
	          System.out.println("Connection Failed! Check output console");
	          e.printStackTrace();
	          return;
	       }
	      //as long as the connection isn't null, you can now use it for sql queries
	      if (connection != null) {
	          //response.getWriter().println("Connected to database successfully.<br>");
	       }
	       else {
	          System.out.println("Failed to make connection!");
	       }
	      try {
				String first_name = request.getParameter("first_name").trim();
				String last_name = request.getParameter("last_name").trim();
				String email = request.getParameter("email").trim();
				String phone_number = request.getParameter("phone_number").trim();
				String address = request.getParameter("address").trim();
				String sqlcommand = "insert into contacts (first_name, last_name, email, phone_number, address) values ('" + first_name + "', '" + last_name + "', '" + email + "', '" + phone_number + "', '" + address + "');";
		    	PreparedStatement prepState = connection.prepareStatement(sqlcommand);
		    	System.out.println(sqlcommand);
		    	int rs = prepState.executeUpdate();
		    	
		    	doGet(request,response);
				
			} catch(Exception e) {
				System.out.println("error in Contact list creation");
				e.printStackTrace();
			}
	}

}
