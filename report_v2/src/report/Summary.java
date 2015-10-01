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

public class Summary extends HttpServlet{ 
	
	private final Log log = LogFactory.getLog(getClass());
	
	private List<LicenseData> getSummaryResults(String reseller, String endUser) {

		List<LicenseData> list =new ArrayList<LicenseData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select distinct o.orderkey,so_header.so_number,so_header.end_user,TO_CHAR(TO_TIMESTAMP(so_header.ship_date/1000), 'YYYY-MM-DD'), ");
	    sql.append("o.hmid, o.apnumber,CASE o.type WHEN 1 THEN 'Evaluation HiveManager' WHEN 2 THEN 'Evaluation GuestManager'  WHEN 3 THEN 'Permanent HiveManager' ");       
	    sql.append("  WHEN 4 THEN 'Permanent GuestManager'  WHEN 5 THEN 'VMWare HiveManager' WHEN 6 THEN 'VMWare GuestManager'  WHEN 7 THEN 'Evaluation GM Light' ");
	    sql.append("  WHEN 8 THEN 'Permanent UserManager' WHEN 10 THEN 'Renewal' WHEN 11 THEN 'Expansion HiveManager' ELSE 'Unknown' END,");
	    sql.append("  CASE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN '' WHEN TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') END, ");
		sql.append("  CASE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN ''  WHEN TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') END,");
		sql.append("o.vhmnumber,o.evaluedays,TO_CHAR(TO_TIMESTAMP(o.startdate/1000), 'YYYY-MM-DD'), ");
		sql.append(" TO_CHAR(TO_TIMESTAMP(o.enddate/1000), 'YYYY-MM-DD'),so_header.po_check_number,so_header.supportquantity, ");
		sql.append(" CASE hmtype WHEN 21 THEN 'HiveManager' WHEN 17 THEN '1U HM'  WHEN 18 THEN 'VMWare HM' WHEN 36 THEN 'HMOL VHM'  WHEN 20 THEN 'HMOL VHM' ");
        sql.append("  WHEN 19 THEN '2U HM' ELSE '' END,CASE o.status  WHEN 1 THEN 'Unused' WHEN 2 THEN 'Active' ELSE 'Inactive' END,so_header.reseller  ");
		sql.append("  from ns.so_header so_header ");   
		sql.append("  inner join orderkey_information o  on so_header.entitlement_key=o.orderkey ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( " where so_header.reseller ILIKE '"+reseller.trim()+"'");
		if(endUser!=null && !endUser.isEmpty())
			sql.append( " and so_header.end_user ILIKE '"+endUser.trim().replace("'","''")+"'");
		  
	    pst = con.createStatement();
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	   
	    log.info("Summary : SQL Query " + sql); 
	    
	    while(rs.next()){
	 	   LicenseData data=new LicenseData();
	 	   
	 	   	//long supportQuants=0l;
	 	  // 	String quantS="select item.quantity from ns.temp_so_item item where item.producttype='Support' and item.entitlementkey='"+rs.getString(1)+"'";
	 	   // pst = con.createStatement();
	 	   // ResultSet  rs1= pst.executeQuery(quantS);
	 	   // while(rs1.next()){
	 	    //	supportQuants=supportQuants+rs1.getLong(1);
	 	   // }
	 	   data.setEntitlementKey(rs.getString(1));
	 	   data.setSoNumbber(rs.getString(2));;
	 	   data.setEndUser(rs.getString(3).replace("'"," "));
	 	   data.setShipDate(rs.getString(4));
	 	   data.setHmId(rs.getString(5));
	 	   data.setApNumber(rs.getString(6));
	 	   data.setKeyType(rs.getString(7));
	 	   data.setLicenseStartDate(rs.getString(8));
	 	   data.setLicenseEndDate(rs.getString(9));
	 	   data.setVhmNumber(rs.getString(10));
	 	   data.setNumberOfDays(rs.getString(11));
	 	   data.setSupportStartDate(rs.getString(12));
	 	   data.setSupportEndDate(rs.getString(13));
	 	   data.setPoNumber(rs.getString(14));
	 	   data.setSupportQuantity(rs.getString(15));
	 	   data.setSystemType(rs.getString(16));
	 	   data.setKeyStatus(rs.getString(17));
	 	   data.setBillingCustomer(rs.getString(18));
	 	   list.add(data);
	    }
	
	}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	return list;
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
		 
			  log.info("Summary Page: Filtering based on Entry "); 
			
			  response.setContentType("text/html");
			  HttpSession session=request.getSession(false);
			  String endUser=null;
			  String reseller=null;
		      if(request.getParameter("endUser") != null && !request.getParameter("endUser").isEmpty()){
		    	  endUser ="%"+request.getParameter("endUser")+"%";
		      	  session.setAttribute("endUserName", request.getParameter("endUser"));
		      	  session.setAttribute("label1", "End User :" + session.getAttribute("endUserName"));
		      }
		      if(request.getParameter("reseller")!=null && !request.getParameter("reseller").isEmpty()){
		    	  reseller="%"+request.getParameter("reseller")+"%";
		    	  session.setAttribute("resellerName", request.getParameter("reseller"));
		    	  session.setAttribute("label", "Reseller :" + session.getAttribute("resellerName"));
		      }else if(session!=null && session.getAttribute("reseller")!=null){
		    	  reseller="%"+session.getAttribute("reseller")+"%";
		      }
		      
		      String isAdmin= (String) session.getAttribute("reseller");
		      boolean isResellerBlank=request.getParameter("reseller")!=null && request.getParameter("reseller").isEmpty() ? true:false;
		      boolean isEndUserBlank=request.getParameter("endUser")!=null && request.getParameter("endUser").isEmpty() ? true:false;
		      boolean isItemsPerPageBlank=request.getParameter("pagesize")!=null && !request.getParameter("pagesize").isEmpty() ? true:false;
		      List<LicenseData> searchResult=null;
		      
		      if(isEndUserBlank && isResellerBlank && isAdmin.equalsIgnoreCase("admin") && !isItemsPerPageBlank){
		    	   session.removeAttribute("summarylist");
		    	   session.removeAttribute("resellerName");
		    	   session.removeAttribute("endUserName");
		    	   String nextJSP = "/summary.jsp";
		    	   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			       dispatcher.forward(request,response);
		      }else if(isItemsPerPageBlank && isEndUserBlank && isResellerBlank && isAdmin.equalsIgnoreCase("admin")){
		    	   if(null !=session.getAttribute("summarylist")){
		    		   	//do nothing
		    	   }else{
		    		   searchResult=getSummaryResults((String) session.getAttribute("resellerName"),(String) session.getAttribute("endUserName"));
		    		   session.setAttribute("summarylist", searchResult);
		    	   }
		    	 
		    	   request.setAttribute("pagesize",(request.getParameter("pagesize")!=null ?request.getParameter("pagesize") :"30"));
		    	   String nextJSP = "/summary.jsp";
			       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			       dispatcher.forward(request,response);
		      }else if(reseller!=null || endUser!=null){
		    	   searchResult=getSummaryResults(reseller,endUser);
		    	   session.removeAttribute("summarylist");
		    	   session.setAttribute("summarylist", searchResult);
		    	   request.setAttribute("pagesize",(request.getParameter("pagesize")!=null ?request.getParameter("pagesize") :"30"));
		    	   String nextJSP = "/summary.jsp";
			       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			       dispatcher.forward(request,response);
		      }else{
		    	  String nextJSP = "/login.jsp";
			      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			      dispatcher.forward(request,response);
		      }
		       
		}

}
