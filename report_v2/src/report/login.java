package report;

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
		  UserLogin user = null;
		  try{
			  
		    Connection con=getConnectiontoDB();
		
			StringBuffer sql= new StringBuffer();
			sql.append("select username,resellername,firsttime,reportaccess,nfraccess from ns.login where username= ? AND password=?");
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst = con.prepareStatement(sql.toString());
		
			pst.setString(1,request.getParameter("uname").trim());
			pst.setString(2,request.getParameter("pass").trim());
			
			
		    ResultSet rs = pst.executeQuery(); 
		
		    while(rs.next()){
		    	user=new UserLogin();
		    	user.setUname(rs.getString(1));
		    	user.setResellerName(rs.getString(2));
		    	user.setFirstLogin(rs.getBoolean(3));
		    	user.setReportaccess(rs.getBoolean(4));
		    	user.setNfraccess(rs.getBoolean(5));
		    	
		    }

	      if(user!=null){
	    	  session.setAttribute("reseller", user.getResellerName());
	    	  session.setAttribute("uname", user.getUname());
	    	  
	    	
	    	  
	    	  if(user.isFirstLogin()){
	    		  log.info("Login Successful for First Time User " +request.getParameter("uname"));
	    		  String nextJSP = "/chgpwd.jsp";
	    		  request.setAttribute("errormessage",null);
		          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		          dispatcher.forward(request,response);
	    	  }else{
	    		  if(user.isReportaccess() && user.isNfraccess()){
	    			  	 log.info("Login Successful for User " +request.getParameter("uname") +" as reseller " + user.getResellerName());
	    			  	 if(user.getResellerName().equalsIgnoreCase("admin")){
		    	    		  log.info("Login Successful for User " +request.getParameter("uname") +" as reseller " + user.getResellerName());
		    	    		  List<String> list=getResellerNameList();
		    	    		  session.setAttribute("resellerList", list);
		    	    		  String nextJSP = "/home.jsp";
		    	    		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			    		      dispatcher.forward(request,response); 
		    	    	  }else{
		    	    		  log.info("Login Successful for User " +request.getParameter("uname"));
		    	    		  List<LicenseData>searchResult=getSummaryResults(session.getAttribute("reseller").toString());
		    	    		  session.setAttribute("summarylist", searchResult);
		    	    		  request.setAttribute("pagesize",  (request.getAttribute("pagesize")!=null ? request.getAttribute("pagesize") :"30"));
		    	    		  String nextJSP = "/home.jsp";
		    		          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		    		          dispatcher.forward(request,response);
		    	    	  }	 
	    		  }else if(user.isReportaccess()){
	    			  if(user.getResellerName().equalsIgnoreCase("admin")){
	    	    		  log.info("Login Successful for User " +request.getParameter("uname") +" as reseller " + user.getResellerName());
	    	    		  List<String> list=getResellerNameList();
	    	    		  session.setAttribute("resellerList", list);
	    	    		  String nextJSP = "/summary.jsp";
	    		          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	    		          dispatcher.forward(request,response);
	    	    	  }else{
	    	    		  log.info("Login Successful for User " +request.getParameter("uname"));
	    	    		  List<LicenseData>searchResult=getSummaryResults(session.getAttribute("reseller").toString());
	    	    		  session.setAttribute("summarylist", searchResult);
	    	    		  request.setAttribute("pagesize",  (request.getAttribute("pagesize")!=null ? request.getAttribute("pagesize") :"30"));
	    	    		  String nextJSP = "/summary.jsp";
	    		          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	    		          dispatcher.forward(request,response);
	    	    	  }
	    		  }else{
	    			  String nextJSP = "/denied.jsp";
    		          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    		          dispatcher.forward(request,response);
	    		  }
	    	  }
	         
	      }else{
	    	  log.info("Login Failed for User " +request.getParameter("uname"));
	    	  request.setAttribute("errormessage","Please enter valid Username/Password");
	    	  String nextJSP = "/login.jsp"; 
	          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	          dispatcher.forward(request,response);
	      }
	      
		  } catch(Exception e){
			log.error("Login Attempt Failed for User "+user.getUname()+" with password " +user.getPwd() +" due to :" +e.getMessage());
		  }

	 }

	private List<String> getResellerNameList() {
		List<String> list=new ArrayList<String>();
		try {

		 Connection conn =DriverManager.getConnection("jdbc:postgresql://localhost:5432/ls","root","test");
	     Statement st;
	     st = conn.createStatement();
		 ResultSet rs = st.executeQuery("SELECT distinct trim(regexp_replace(reseller,'[0-9]*' ,'')) FROM ns.so_header");
		 
		   while(rs.next())
	       {
			   list.add(rs.getString(1));
	       }

	     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<LicenseData> getSummaryResults(String reseller) {
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
		    sql.append(" CASE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN '' WHEN TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') END, ");
			sql.append("CASE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN ''  WHEN TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') END, ");
			sql.append("o.vhmnumber,o.evaluedays,TO_CHAR(TO_TIMESTAMP(o.startdate/1000), 'YYYY-MM-DD'), ");
			sql.append(" TO_CHAR(TO_TIMESTAMP(o.enddate/1000), 'YYYY-MM-DD'),so_header.po_check_number,so_header.supportquantity, ");
			sql.append(" CASE hmtype WHEN 21 THEN 'HiveManager' WHEN 17 THEN '1U HM'  WHEN 18 THEN 'VMWare HM' WHEN 36 THEN 'HMOL VHM'  WHEN 20 THEN 'HMOL VHM' ");
	        sql.append("  WHEN 19 THEN '2U HM' ELSE '' END,CASE o.status  WHEN 1 THEN 'Unused' WHEN 2 THEN 'Active' ELSE 'Inactive' END ");
			sql.append("  from ns.so_header so_header ");   
			sql.append("  inner join orderkey_information o  on so_header.entitlement_key=o.orderkey ");
			if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("admin"))
				sql.append( " where so_header.reseller ILIKE '"+reseller.trim()+"'");
			sql.append(" order by o.orderkey");
			  
		    pst = con.createStatement();
		    ResultSet  rs= pst.executeQuery(sql.toString());
		   
		   
		    log.info("Summary : SQL Query " + sql); 
		    
		    while(rs.next()){
			 	   LicenseData data=new LicenseData();
			 	   
			 	//  long supportQuants=0l;
			 	//   	String quantS="select item.quantity from ns.temp_so_item item where item.producttype='Support' and item.entitlementkey='"+rs.getString(1)+"'";
			 	//    pst = con.createStatement();
			 	//    ResultSet  rs1= pst.executeQuery(quantS);
			 	//    while(rs1.next()){
			 	   // 	supportQuants=supportQuants+rs1.getLong(1);
			 	 //   }
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
