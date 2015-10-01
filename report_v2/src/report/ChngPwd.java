package report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChngPwd extends HttpServlet {

	private final Log log = LogFactory.getLog(getClass());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		  response.setContentType("text/html");
		  HttpSession session=request.getSession();
	
		  try{
			  
		    Connection con=getConnectiontoDB();
		    
			StringBuffer sql= new StringBuffer();
			sql.append("Update ns.login set password= ?,firsttime=? where username=? and  password=?");
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst = con.prepareStatement(sql.toString());
		
			
			pst.setString(1,request.getParameter("new"));
			pst.setBoolean(2, false);
			pst.setString(3,(String)session.getAttribute("uname"));
			pst.setString(4,request.getParameter("current"));
			
		    int row =pst.executeUpdate(); 
		
		    con.setAutoCommit(true);
		    con.close();
		    if(row>0){
		    	log.info("Updated User info for user " +(String)session.getAttribute("uname") );
		    	String nextJSP = "/login.jsp";
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			    dispatcher.forward(request,response);
		    }else{
		    	log.info("Update User Info Failed for user " +(String)session.getAttribute("uname") );
		    	request.setAttribute("errormessage","Please enter valid Current Password");
		    	String nextJSP = "/chgpwd.jsp";
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			    dispatcher.forward(request,response);
		    }
	    	
	    
	      
		  } catch(Exception e){
			  System.out.println("Failed due to :" +e.getMessage());
		  }
	 }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		HttpSession session=request.getSession(false);
		  
		log.info("ChngPwd : User tried a GET Call " + session.getAttribute("uname")); 
	    String nextJSP = "/chgpwd.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	    dispatcher.forward(request,response);
	}
	
    private Connection getConnectiontoDB() {
		
		Connection con = null;
	//	String url = "jdbc:postgresql://10.16.194.69:5432/ls";
	//	String user = "test";
	//	String password = "aerohive123!";
		
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
