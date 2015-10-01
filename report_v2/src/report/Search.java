package report;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.*;

public class Search extends HttpServlet{ 
	
	private final Log log = LogFactory.getLog(getClass());

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
  
	
	  response.setContentType("text/html");
	  HttpSession session=request.getSession(false);
	  String  reseller=null;
      if(session!=null && session.getAttribute("reseller")!=null)
      reseller  = "%"+session.getAttribute("reseller")+"%";
      List<LicenseData> searchResult=null;
      if(reseller!=null){
    	/*  session.setAttribute("fieldsearch", true);
    	  session.removeAttribute("datesearch");*/
          
          if(request.getParameter("so")!=null && !request.getParameter("so").isEmpty()){
        	  
        	  	log.info("Search Fields : Sales Order Search " + request.getParameter("so") + " for reseller " + session.getAttribute("reseller"));
        	  	
        	  	searchResult=getSearchByFieldResults(reseller,request.getParameter("so"),"so");
        	  
        	    session.setAttribute("so", request.getParameter("so"));
        	    session.removeAttribute("enduser");
        	    session.removeAttribute("ek");
        	    session.setAttribute("label", "Sales Order");
        	    session.setAttribute("value",  request.getParameter("so"));
        	    
          }else if(request.getParameter("enduser")!=null && !request.getParameter("enduser").isEmpty()){
        	  
        	  log.info("Search Fields : End User Search " + request.getParameter("enduser") + " for reseller " + session.getAttribute("reseller"));
        	  
        	  searchResult=getSearchByFieldResults(reseller,request.getParameter("enduser"),"enduser");
        	  session.setAttribute("enduser", request.getParameter("enduser"));
        	  session.removeAttribute("so");
        	  session.removeAttribute("sno");
      	      session.removeAttribute("ek");
      	    session.setAttribute("label", "End User");
    	    session.setAttribute("value",  request.getParameter("enduser"));
        	  
          }else if(request.getParameter("ek")!=null && !request.getParameter("ek").isEmpty()){
        	  
        	  log.info("Search Fields : Entitlement Key Search " + request.getParameter("ek") + " for reseller " + session.getAttribute("reseller")); 
        	  
        	  searchResult=getSearchByFieldResults(reseller,request.getParameter("ek"),"ek");
        	  session.setAttribute("ek", request.getParameter("ek"));
        	  session.removeAttribute("so");
        	  session.removeAttribute("sno");
      	      session.removeAttribute("enduser");
      	    session.setAttribute("label", "Entitlement Key");
    	    session.setAttribute("value",  request.getParameter("ek"));
      	      
          }else if(request.getParameter("sno")!=null && !request.getParameter("sno").isEmpty()){
        	  
        	  log.info("Search Fields : Serial Number Search " + request.getParameter("sno") + " for reseller " + session.getAttribute("reseller")); 
        	  
        	  searchResult=getSearchByFieldResults(reseller,request.getParameter("sno"),"sno");
        	  session.setAttribute("sno", request.getParameter("sno"));
        	  session.removeAttribute("ek");
        	  session.removeAttribute("so");
      	      session.removeAttribute("enduser");
      	      request.setAttribute("sno", 1);
      	      session.setAttribute("label", "Serial Number");
      	      session.setAttribute("value",  request.getParameter("sno"));
      	      
          }else if(request.getParameter("po")!=null && !request.getParameter("po").isEmpty()){
        	  
        	  log.info("Search Fields : Purchase Order Search " + request.getParameter("po") + " for reseller " + session.getAttribute("reseller")); 
        	  
        	  searchResult=getSearchByFieldResults(reseller,request.getParameter("po"),"po");
        	  session.setAttribute("po", request.getParameter("po"));
        	  session.setAttribute("label", "Purchase Order");
      	      session.setAttribute("value",  request.getParameter("po"));
        	  
          }else if(request.getParameter("hm")!=null && !request.getParameter("hm").isEmpty()){
        	  
        	  log.info("Search Fields : HM ID Search " + request.getParameter("hm") + " for reseller " + session.getAttribute("hm")); 
        	  
        	  searchResult=getSearchByFieldResults(reseller,request.getParameter("hm"),"hm");
        	  session.setAttribute("hm", request.getParameter("hm"));
        	  session.setAttribute("label", "Hive Manager ID");
      	      session.setAttribute("value",  request.getParameter("hm"));
          }

           session.setAttribute("fieldSearchList", searchResult);
           String nextJSP = "/viewFieldSearchResult.jsp";
           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
           dispatcher.forward(request,response);
      }else{
    	  
    	  log.info("Search Fields : Reseller Blank  "); 
    	  String nextJSP = "/login.jsp";
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
          dispatcher.forward(request,response);
      }
     
   
  }

private List<LicenseData> getSearchByFieldResults(String reseller, String parameter, String type) {

	List<LicenseData> list =new ArrayList<LicenseData>();
	Connection con=null;
try {
    
    Statement pst = null;
    con=getConnectiontoDB();
    
    StringBuffer sql = new StringBuffer();
    if(type.equalsIgnoreCase("sno")){
    	  sql.append(" select distinct ib.item,o.orderkey,'1',so_header.so_number,so_header.end_user,TO_CHAR(TO_TIMESTAMP(so_header.ship_date/1000), 'YYYY-MM-DD'), ");
    }else{
        sql.append(" select distinct so_item.item,so_item.entitlementkey,so_item.quantity,so_header.so_number,so_header.end_user,TO_CHAR(TO_TIMESTAMP(so_header.ship_date/1000), 'YYYY-MM-DD'), ");
    }
    sql.append("o.hmid,	CASE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN '' WHEN TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') END, ");
	sql.append("CASE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') WHEN '1969-12-31' THEN ''  WHEN TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD') THEN '' ELSE TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') END, so_header.po_check_number,so_header.reseller, ");
	sql.append(" CASE WHEN so_item.producttype='Support' THEN TO_CHAR(TO_TIMESTAMP(o.startdate/1000), 'YYYY-MM-DD') ELSE '' END, ");
	sql.append(" CASE WHEN so_item.producttype='Support' THEN TO_CHAR(TO_TIMESTAMP(o.enddate/1000), 'YYYY-MM-DD') ELSE '' END  ");
	if(type.equalsIgnoreCase("sno")){
		sql.append(" ,ib.serialnumber");
		sql.append("  from ns.so_header so_header  inner join ns.ib ib on ib.salesordernumber =so_header.so_number ");   
		sql.append("  inner join orderkey_information o  on so_header.entitlement_key=o.orderkey");
		sql.append(" inner join ns.temp_so_item so_item on so_header.entitlement_key=so_item.entitlementkey");
	}else{
		sql.append(" from ns.so_header so_header inner join ns.temp_so_item so_item on so_header.entitlement_key=so_item.entitlementkey ");
		sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
	}

	if(type.equalsIgnoreCase("sno"))
		sql.append(" where ib.serialnumber ILIKE '%"+parameter.trim()+"%' ");
	if(type.equalsIgnoreCase("so"))
		sql.append("where so_header.so_number='"+parameter.trim()+"' ");
	if(type.equalsIgnoreCase("enduser"))
		sql.append("where so_header.end_user ILIKE '%"+parameter.trim().replace("'","''")+"%'");
	if(type.equalsIgnoreCase("ek"))
		sql.append("where so_header.entitlement_key ILIKE '%"+parameter.trim()+"%'");
	if(type.equalsIgnoreCase("po"))
		sql.append("where so_header.po_check_number ILIKE '%"+parameter.trim()+"%'");
	if(type.equalsIgnoreCase("hm"))
		sql.append("where o.hmid ILIKE '%"+parameter.trim()+"%'");
	if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
		sql.append( " and so_header.reseller ILIKE '"+reseller.trim()+"'");
	 if(type.equalsIgnoreCase("sno"))
		 sql.append(" order by so_header.so_number desc ");
    pst = con.createStatement();
    ResultSet  rs= pst.executeQuery(sql.toString());
   
   
    log.info("Search Fields : SQL Query " + sql); 
    
    while(rs.next()){
 	   LicenseData data=new LicenseData();
 	   data.setEntitlementKey(rs.getString(2));
 	   data.setSku(rs.getString(1));
 	   data.setQuantity(rs.getString(3));
 	   data.setSoNumbber(rs.getString(4));;
 	   data.setEndUser(rs.getString(5));
 	   data.setShipDate(rs.getString(6));
 	   data.setHmId(rs.getString(7));
 	   data.setLicenseStartDate(rs.getString(8));
 	   data.setLicenseEndDate(rs.getString(9));
 	   data.setPoNumber(rs.getString(10));
 	   data.setNumber(rs.getString(4));
 	   data.setBillingCustomer(rs.getString(11));
 	   data.setSupportStartDate(rs.getString(12));
 	   data.setSupportEndDate(rs.getString(13));
 	  if(type.equalsIgnoreCase("sno"))
 		  data.setSerialNumber(rs.getString(14));
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

}
