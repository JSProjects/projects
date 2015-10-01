package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class register extends HttpServlet{ 

	private final Log log = LogFactory.getLog(getClass());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		  response.setContentType("text/html");
		  HttpSession session=request.getSession(false);
		  try{
			  
			    Connection con=getConnectiontoDB();
			    
			    String count="SELECT count(*) from ns.login where username = ? and resellername=?";
				PreparedStatement p = con.prepareStatement(count);
				p.setString(1, request.getParameter("name"));
				p.setString(2, request.getParameter("reseller"));
				
				ResultSet rs = p.executeQuery(); 
				
			    while(rs.next()){
			    	
			    	if(rs.getLong(1) > 0 ){
			    		String sql="Update ns.login set reportaccess=? ,nfraccess=? where username=? and resellername=?";
			    		PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(3, request.getParameter("name"));
						ps.setString(4, request.getParameter("reseller"));
						ps.setBoolean(1, request.getParameter("reportaccess")!=null && !request.getParameter("reportaccess").isEmpty() ? true :false);
						ps.setBoolean(2, request.getParameter("nfraccess")!=null && !request.getParameter("nfraccess").isEmpty() ? true :false);
						ps.executeUpdate();
						ps.close();
						request.setAttribute("message", "User Updated");
						
			    	}else{
			    	    String sql = "INSERT INTO ns.login " +
								"(username,password,resellername,firsttime,reportaccess,nfraccess) VALUES (?,?,?,?,?,?)";
									
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, request.getParameter("name"));
						ps.setString(2, "test123");
						ps.setString(3, request.getParameter("reseller"));
						ps.setBoolean(4, true);
						ps.setBoolean(5, request.getParameter("reportaccess")!=null && !request.getParameter("reportaccess").isEmpty() ? true :false);
						ps.setBoolean(6, request.getParameter("nfraccess")!=null && !request.getParameter("nfraccess").isEmpty() ? true :false);
						ps.executeUpdate();
						ps.close();
						request.setAttribute("message", "New User Created");
			    	}
						List<User> users=getUsers();
						session.setAttribute("users", users);
						
				    	String nextJSP = "/register.jsp"; 
				        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				        dispatcher.forward(request,response);
			    	
			    }
				
			
			
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	}
	

	private List<User> getUsers() throws SQLException {
	  	  List<User> users=new ArrayList<User>();
  	  	StringBuffer sql1= new StringBuffer();
	    Connection con=getConnectiontoDB();
			sql1.append("select username,resellername,reportaccess,nfraccess from ns.login");
			PreparedStatement pst1 = con.prepareStatement(sql1.toString());
			 ResultSet rs1 = pst1.executeQuery(); 
			 while(rs1.next()){
				 User user=new User();
				 user.setUname(rs1.getString(1));
				 user.setResellerName(rs1.getString(2));
				 user.setReportaccess(rs1.getBoolean(3));
				 user.setNfraccess(rs1.getBoolean(4));
				 users.add(user);
			 }
			 return users;
	}


	private Connection getConnectiontoDB() {
		Connection con = null;
		/*	String url = "jdbc:postgresql://10.16.194.69:5432/ls";
			String user = "test";
			String password = "aerohive123!";*/
			
			String url = "jdbc:postgresql://localhost:5432/ls";
			String user = "root";
			String password = "test";

			try {
				
			    Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(url, user, password);
			
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		return con;
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

		response.setContentType("text/html"); 
		  HttpSession session=request.getSession(false);
		  try{
			  
			    Connection con=getConnectiontoDB();
			
			    String sql = "Update ns.login " +
						"set password='test123',firsttime='t' where username=? and resellername=?";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, request.getParameter("name"));
				ps.setString(2, request.getParameter("reseller"));
				ps.executeUpdate();
				ps.close();
				List<User> users=getUsers();
				session.setAttribute("users", users);
				request.setAttribute("message1", "Password Reset");
				
		    	String nextJSP = "/register.jsp"; 
		        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		        dispatcher.forward(request,response);
		  }catch(Exception e){
			  e.getStackTrace();
		  }
	}
}