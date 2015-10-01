package report;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchDates extends HttpServlet{ 
 
	private final Log log = LogFactory.getLog(getClass());
	
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
  
	  response.setContentType("text/html");
	  HttpSession session=request.getSession(false);

	   String  reseller=null;
      List<LicenseData> searchResult=null;
      if(session!=null && session.getAttribute("reseller")!=null)
      reseller  = "%"+session.getAttribute("reseller")+"%";
      if(reseller!=null){
    	  String startdate=request.getParameter("startdate")!=null && !request.getParameter("startdate").isEmpty() ? request.getParameter("startdate").trim():null;
          String enddate=request.getParameter("enddate")!=null &&  !request.getParameter("enddate").isEmpty() ? request.getParameter("enddate").trim():null;
          String expdays=request.getParameter("expdays")!=null &&  !request.getParameter("expdays").isEmpty() ? request.getParameter("expdays").trim():null;
          String lstartdate=request.getParameter("lstartdate")!=null && !request.getParameter("lstartdate").isEmpty() ? request.getParameter("lstartdate").trim():null;
          String lenddate=request.getParameter("lenddate")!=null &&  !request.getParameter("lenddate").isEmpty() ? request.getParameter("lenddate").trim():null;

          if(startdate!=null || enddate!=null){
        	 
        	 	log.info("Search Dates : Start/End Dates Search Start Date:" + request.getParameter("startdate") +" End Date :"+ request.getParameter("enddate") 
        	 			+ " for reseller " + session.getAttribute("reseller"));
        	  	searchResult=getSearchByDateResults(reseller,startdate,enddate,"dates",null);
        	   
        	    session.setAttribute("startdate", startdate);
        	    session.setAttribute("enddate", enddate);
        	    session.removeAttribute("expdays");
        	    session.setAttribute("label", "Sales Order");
        	    session.setAttribute("value",  request.getParameter("startdate") + " - " +request.getParameter("enddate") );
          }else if(expdays!=null){
        	  
        	  log.info("Search Dates : Expiry Days Search:" + request.getParameter("expdays") + " for reseller " + session.getAttribute("reseller"));
        	  searchResult=getSearchByDateResults(reseller,null,null,"expdays",expdays);
        	
        	  session.setAttribute("expdays", expdays);
        	  session.removeAttribute("startdate");
        	  session.removeAttribute("enddate");
        	  session.setAttribute("label", "License Expiring (days)");
        	  session.setAttribute("value",  request.getParameter("expdays"));
        	  
          }else if(lstartdate!=null || lenddate!=null){
         	 
      	 	log.info("Search Dates : License Start/End Dates Search Start Date:" + request.getParameter("lstartdate") +" End Date :"+ request.getParameter("lenddate") 
      	 			+ " for reseller " + session.getAttribute("reseller"));
      	  	searchResult=getSearchByDateResults(reseller,lstartdate,lenddate,"ldates",null);
      	   
      	    session.setAttribute("lstartdate", startdate);
      	    session.setAttribute("lenddate", enddate);
      	    session.removeAttribute("expdays");
      	    session.setAttribute("label", "Subscription End Dates");
      	  	session.setAttribute("value",  request.getParameter("lstartdate") + " - " +request.getParameter("lenddate") );
        }
      }else{
    	  	 
    	     log.info("Search Dates : Reseller Blank  "); 
    	     String nextJSP = "/login.jsp";
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
             dispatcher.forward(request,response);
      }
      
      

      if(searchResult!=null){
    	
          session.setAttribute("dateSearchList", searchResult);
          String nextJSP = "/viewDateSearchResult.jsp";
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
          dispatcher.forward(request,response);
      }

  }


private List<LicenseData> getSearchByDateResults( String reseller, String startdate, String enddate,
		String type,String expdays) {
	List<LicenseData> list =new ArrayList<LicenseData>();
	 Connection con=null;
	 
try {
    
    Statement pst = null;
    con=getConnectiontoDB();
    
    StringBuffer sql = new StringBuffer();
    sql.append(" select so_item.item,so_item.entitlementkey,so_item.quantity,so_header.so_number,so_header.end_user,TO_CHAR(TO_TIMESTAMP(so_header.ship_date/1000), 'YYYY-MM-DD'), ");
    sql.append(" CASE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN '' WHEN TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') END, ");
	sql.append(" CASE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN ''  WHEN TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') END, so_header.po_check_number,so_header.reseller ");
	sql.append(" from ns.so_header so_header inner join ns.temp_so_item so_item on so_header.entitlement_key=so_item.entitlementkey ");
	sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
	if(type.equalsIgnoreCase("dates")){
		if(startdate!=null)
			sql.append("where so_header.date>='"+getDateLongFromStrNoTimeZone(startdate.trim())+"'");
		if(enddate!=null)
			sql.append("and so_header.date<='"+getDateLongFromStrNoTimeZone(enddate.trim())+"'");
	}
	if(type.equalsIgnoreCase("expdays")){
		if(expdays!=null){
			sql.append("where o.subenddate>='"+getDate(null)+"'");
			sql.append("and o.subenddate<='"+getDate(expdays.trim())+"'");
		}
			
	}	
	if(type.equalsIgnoreCase("ldates")){
		if(startdate!=null)
			sql.append(" where o.subenddate>='"+getDateLongFromStrNoTimeZone(startdate.trim())+"'");
		if(enddate!=null)
			sql.append(" and o.subenddate<='"+getDateLongFromStrNoTimeZone(enddate.trim())+"'");
	}
	if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
		sql.append( " and so_header.reseller ILIKE '"+reseller.trim()+"'");
	
		
		pst = con.createStatement();
	    ResultSet  rs= pst.executeQuery(sql.toString());
	    log.info("Search Dates : SQL Query " + sql); 
	    while(rs.next()){
	    	LicenseData data=new LicenseData();
	  	   data.setEntitlementKey(rs.getString(2));
	  	   data.setSku(rs.getString(1));
	  	   data.setQuantity(rs.getString(3));
	  	   data.setSoNumbber(rs.getString(4));;
	  	   data.setEndUser(rs.getString(5));
	  	   data.setShipDate(rs.getString(6));
	  	   data.setLicenseStartDate(rs.getString(7));
	  	   data.setLicenseEndDate(rs.getString(8));
	  	   data.setPoNumber(rs.getString(9));
	  	   data.setNumber(rs.getString(4));
	  	   data.setBillingCustomer(rs.getString(10));
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

public static long getDateLongFromStrNoTimeZone(String dateStr) {
	Calendar cld = Calendar.getInstance();
	String[] date = dateStr.split("/");			
	cld.set(Integer.parseInt(date[2]), Integer.parseInt(date[0])-1, Integer.parseInt(date[1]), 0, 0, 0);
	return cld.getTimeInMillis();
}

public static long getDate(String str){
		Calendar cal=new GregorianCalendar();
		if(str!=null)
	    cal.add(Calendar.DATE, Integer.parseInt(str));
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    String dateStr=sdf.format(cal.getTime());
	    String[] date = dateStr.split("/");	
		cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[0])-1, Integer.parseInt(date[1]), 0, 0, 0);
		return cal.getTimeInMillis();
}


}

