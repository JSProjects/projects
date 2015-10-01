package report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class SOSearch extends HttpServlet{ 
	 
	private final Log log = LogFactory.getLog(getClass());
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		 
		  response.setContentType("text/html");
	      HttpSession session=request.getSession(false);
	      if(session!=null && session.getAttribute("reseller")!=null){
	    	  String reseller=(String) session.getAttribute("reseller");
	    	  log.info("Sales Order Search " + request.getParameter("sonumber") + " for reseller " + session.getAttribute("reseller")); 
	   	      List<LicenseData> searchResult=new ArrayList<LicenseData>();
	   	      Statement pst = null;
	   	      Connection con=getConnectiontoDB();
	   	      session.setAttribute("sonumber", request.getParameter("sonumber"));
	   	      StringBuffer sql = new StringBuffer();
	   	      sql.append("select distinct o.orderkey,ib.salesordernumber,ib.item,so_header.end_user,ib.serialnumber,");
	   	      sql.append("so_header.po_check_number ");
	   	  	  sql.append(" from  ns.so_header so_header  inner join ns.ib ib on ib.salesordernumber =so_header.so_number");
	   	  	  sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
	   	  	  sql.append("where (ib.producttype='AP' or ib.producttype='License - AP') and so_header.so_number='"+request.getParameter("sonumber")+"' ");
	   	  	  if(reseller!=null &&  !reseller.equalsIgnoreCase("admin"))
	   	  	  sql.append(" and so_header.reseller ILIKE '%"+session.getAttribute("reseller")+"%'");
	   	  	  
	   	      try {
	   			pst = con.createStatement();
	   		
	   		  log.info("Sales Order Search " + sql); 
	   		  session.setAttribute("sovalue", request.getParameter("sonumber"));
	   	      ResultSet  rs= pst.executeQuery(sql.toString());
	   	     
	   	      while(rs.next()){
	   	   	   LicenseData data=new LicenseData();
	   	   	   data.setEntitlementKey(rs.getString(1));
	   	   	   data.setSoNumbber(rs.getString(2));
	   	   	   data.setSku(rs.getString(3));
	   	   	   data.setEndUser(rs.getString(4));
	   	   	   data.setSerialNumber(rs.getString(5));
	   	   	   data.setPoNumber(rs.getString(6));
	   	   	   searchResult.add(data);
	   	      }
	   	       con.close();
	   	       session.setAttribute("solist", searchResult);
	   	       String nextJSP = "/viewSOSearchResult.jsp";
	   	       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	   	       dispatcher.forward(request,response); 
	   	   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	  }else{
		  
		   log.info("Sales Order Search : Reseller Blank  "); 
		   String nextJSP = "/login.jsp";
  	       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
  	       dispatcher.forward(request,response); 
	  }
	 
	       
	    
 }
	 
 private Connection getConnectiontoDB() {
	
	Connection con = null;
	/*String url = "jdbc:postgresql://10.16.194.69:5432/ls";
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
