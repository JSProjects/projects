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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class login extends HttpServlet{ 

	private final Log log = LogFactory.getLog(getClass());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		  
		  response.setContentType("text/html");
		  HttpSession session=request.getSession(true);
		  String admin=null;
		  try{
			  
		    Connection con=getConnectiontoDB();
		
			StringBuffer sql= new StringBuffer();
			sql.append("select username from ns.admin where username= ? AND password=?");
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst = con.prepareStatement(sql.toString());
		
			pst.setString(1,request.getParameter("uname").trim());
			pst.setString(2,request.getParameter("pass").trim());
			
			
		    ResultSet rs = pst.executeQuery(); 
		
		    while(rs.next()){
		    	admin=rs.getString(1);
		    	
		    }

	      if(admin!=null){
	    	  List<User> users=new ArrayList<User>();
	    	  	StringBuffer sql1= new StringBuffer();
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
	    	  pst1.close();
	    	  session.setAttribute("uname", admin);
	    	  session.setAttribute("users", users);
	    	  String nextJSP = "/register.jsp"; 
	          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	          dispatcher.forward(request,response);
	      }else{
	    	  log.info("Login Failed for User " +request.getParameter("uname"));
	    	  request.setAttribute("errormessage","Please enter valid Username/Password");
	    	  String nextJSP = "/login.jsp"; 
	          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	          dispatcher.forward(request,response);
	      }
	      
		  } catch(Exception e){
			log.error("Login Attempt Failed for User "+admin+" due to :" +e.getMessage());
		  }

	 }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		log.info("User Tried to login using Get call");
	    String nextJSP = "/login.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	    dispatcher.forward(request,response);
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
}
