package report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActiveSummary  extends HttpServlet{ 

	private final Log log = LogFactory.getLog(getClass());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		 log.info("Activation Summary Page: Filtering based on Entry "); 
			
		  response.setContentType("text/html");
		  HttpSession session=request.getSession(false);
		  String endUser=null;
		  String reseller=null;
		  String hmid=null;
		  session.removeAttribute("endUserName");
   	      session.removeAttribute("hmid");
		  if(request.getParameter("endUser") != null && !request.getParameter("endUser").isEmpty()){
			  endUser=request.getParameter("endUser");
			  session.setAttribute("endUserName", request.getParameter("endUser"));
	      	  session.setAttribute("label", "End User :" + session.getAttribute("endUserName"));
		  }
		  if(session!=null && session.getAttribute("reseller")!=null){
	    	  reseller="%"+session.getAttribute("reseller")+"%";
	      }
		  if(request.getParameter("hmid") != null && !request.getParameter("hmid").isEmpty()){
			  hmid=request.getParameter("hmid");
			  session.setAttribute("hmid", request.getParameter("hmid"));
	      	  session.setAttribute("label1", "HM ID :" + session.getAttribute("hmid"));
		  }
			
	      boolean isEndUserBlank=request.getParameter("endUser")!=null && !request.getParameter("endUser").isEmpty() ? false:true;
	      boolean isHmIdBlank=request.getParameter("hmid")!=null && !request.getParameter("hmid").isEmpty() ? false:true;
	      if(isEndUserBlank && isHmIdBlank){
	    	   session.removeAttribute("endUserName");
	    	   session.removeAttribute("hmid");
	    	   String nextJSP = "/activesummary.jsp";
	    	   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	    	   dispatcher.forward(request,response);
	      }else if(!isEndUserBlank){
	    	  List<SupportData> support=getSupportDetails(endUser,reseller);
	    	  
	    	  List<SupportData> activelist= new ArrayList<SupportData>();
	    	  List<SupportData> expiredlist= new ArrayList<SupportData>();
	    	  
	    	  for (SupportData split:support){
	    		  if(split.getKeyStatus().equalsIgnoreCase("Expired")){
	    			  expiredlist.add(split);
	    		  }else{
	    			  activelist.add(split);
	    		  }
	    	  }
	    	  
	    	  List<SubscriptionData> subscriptionlist=getSubscriptionDetails(endUser,reseller);
	    	  
	    	  List<APLicenseData> licenselist=getLicenseDetails(endUser,reseller);
	    	  
	    	
	    	  
	    	  List<HardwareData> hardware=getHardwareDetails(endUser,reseller);
	    	  for(HardwareData data: hardware){
	    		  System.out.println(data);
	    	  }
	    	  
	    	   session.setAttribute("activesupport", activelist);
	    	   session.setAttribute("expiredsupport",expiredlist);
	    	   session.setAttribute("hardwarelist", hardware);
	    	   session.setAttribute("subscriptionlist", subscriptionlist);
	    	   session.setAttribute("licenselist", licenselist);
	    	   
	    	   String nextJSP = "/viewActivation.jsp";
		       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		       dispatcher.forward(request,response);
	      }else{
	    	  List<SupportData> support=getSupportDetailsforHM(hmid,reseller);
	    	  
	    	  List<SupportData> activelist= new ArrayList<SupportData>();
	    	  List<SupportData> expiredlist= new ArrayList<SupportData>();
	    	  
	    	  for (SupportData split:support){
	    		  if(split.getKeyStatus().equalsIgnoreCase("Expired")){
	    			  expiredlist.add(split);
	    		  }else{
	    			  activelist.add(split);
	    		  }
	    	  }
	    	  
	    	  List<SubscriptionData> subscriptionlist=getSubscriptionDetailsforHM(hmid,reseller);
	    	  
	    	  List<APLicenseData> licenselist=getLicenseDetailsforHM(hmid,reseller);
	    	  
	    	
	    	  
	    	  List<HardwareData> hardware=getHardwareDetailsforHM(hmid,reseller);
	    	  for(HardwareData data: hardware){
	    		  System.out.println(data);
	    	  }
	    	  
	    	   session.setAttribute("activesupport", activelist);
	    	   session.setAttribute("expiredsupport",expiredlist);
	    	   session.setAttribute("hardwarelist", hardware);
	    	   session.setAttribute("subscriptionlist", subscriptionlist);
	    	   session.setAttribute("licenselist", licenselist);
	    	   
	    	   String nextJSP = "/viewActivation.jsp";
		       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		       dispatcher.forward(request,response);
	      }
	}

	private List<HardwareData> getHardwareDetailsforHM(String hmid,
			String reseller) {
		Map<String,HardwareData> list =new HashMap<String,HardwareData>();
		
		List<HardwareData> hlist=new ArrayList<HardwareData>();
		
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select item,quantity from ns.temp_so_detail inner join ns.so_header   ");
	    sql.append(" on ns.temp_so_detail.\"number\"=ns.so_header.so_number ");       
	    sql.append(" inner join orderkey_information on ns.so_header.entitlement_key=orderkey_information.orderkey ");
	    sql.append(" where product_type in ('AP','BR','SR') and orderkey_information.hmid is not null and orderkey_information.hmid!=''  ");
	    sql.append("  and rev_rec_start_date is not null and rev_rec_end_date is not null ");   
	    if(hmid!=null && !hmid.isEmpty())
			sql.append( "and orderkey_information.hmid ILIKE  '%"+hmid.trim().replace("'","''")+"%'");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( " and ns.so_header.reseller ILIKE'"+reseller.trim().replace("'","''")+"'") ;
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
	    pst = con.createStatement();
	    
	    log.info("Hardware Summary for HM : SQL Query " + sql);  
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	
	    
	    	while(rs.next()){
	    		HardwareData data =new HardwareData();
	    		
	    		if(null!=list && null!=list.get(rs.getString(1).replaceAll("[^0-9]", ""))){
	    			data=list.get(rs.getString(1).replaceAll("[^0-9]", ""));
	    			List<String> skuList = new ArrayList<String>(Arrays.asList(data.getSku().split(",")));
	    			if(!skuList.contains(rs.getString(1)))
	    				data.setSku(data.getSku()+"," +rs.getString(1));
	    			data.setQuantity(data.getQuantity()+rs.getLong(2));
	    			data.setHmId("'"+hmid+"'");
	    					
	    		}else{
	    			data.setSku(rs.getString(1));
	    			data.setQuantity(rs.getLong(2));
	    			data.setHmId("'"+hmid+"'");
	    		}
	   
	    		 list.put(rs.getString(1), data);
	    	}
	    	
	    	for (Entry<String, HardwareData> entry : list.entrySet()) {
	    			int count=getUnitsinUse(entry.getValue().getHmId(),entry.getKey());
	    			entry.getValue().setUnitsUsed(count);
	    			hlist.add(entry.getValue());
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	
	return hlist;
	}

	private List<APLicenseData> getLicenseDetailsforHM(String hmid,
			String reseller) {
		List<APLicenseData> list =new ArrayList<APLicenseData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select item,quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and   TO_TIMESTAMP(enddate/1000) > current_date   ");
	    sql.append(" THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date  THEN 'Active' ELSE 'Expired' END as KeyStatus  ");       
	    sql.append(" from orderkey_information inner join ns.temp_item_info on orderkey_information.orderkey=ns.temp_item_info.entitlementkey ");
	    sql.append("  where product_type ILIKE '%License%' ");
	    if(hmid!=null && !hmid.isEmpty())
	    	sql.append(" and orderkey_information.hmid ILIKE '%"+hmid.trim().replace("'","''")+"%' ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append(" and orderkey_information.orderkey in (select entitlement_key from ns.so_header  where reseller ILIKE '"+reseller.trim().replace("'","''")+"' ");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
		  
	    pst = con.createStatement();
	    log.info("License Summary for HM : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	    
	    
	    	while(rs.next()){
	    		APLicenseData data=new APLicenseData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	return list;
	}

	private List<SubscriptionData> getSubscriptionDetailsforHM(String hmid,
			String reseller) {
		List<SubscriptionData> list =new ArrayList<SubscriptionData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select item,quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and   TO_TIMESTAMP(enddate/1000) > current_date   ");
	    sql.append(" THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date  THEN 'Active' ELSE 'Expired' END as KeyStatus  ");       
	    sql.append(" from orderkey_information inner join ns.temp_item_info on orderkey_information.orderkey=ns.temp_item_info.entitlementkey ");
	    sql.append("  where product_type='Subscription' ");
	    
	    if(hmid!=null && !hmid.isEmpty())
	    	sql.append(" and orderkey_information.hmid ILIKE '%"+hmid.trim().replace("'","''")+"%' ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append(" and orderkey_information.orderkey in (select entitlement_key from ns.so_header  where reseller ILIKE '"+reseller.trim().replace("'","''")+"' ");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
		  
	    pst = con.createStatement();
	    log.info("Subscription Summary for HM : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	    
	    
	    	while(rs.next()){
	    		SubscriptionData data=new SubscriptionData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	return list;
	}

	private List<SupportData> getSupportDetailsforHM(String hmid,
			String reseller) {
		List<SupportData> list =new ArrayList<SupportData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select item,quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and   TO_TIMESTAMP(enddate/1000) > current_date   ");
	    sql.append(" THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date  THEN 'Active' ELSE 'Expired' END as KeyStatus  ");       
	    sql.append(" from orderkey_information inner join ns.temp_item_info on orderkey_information.orderkey=ns.temp_item_info.entitlementkey ");
	    sql.append("  where product_type='Support' ");
	    
	    if(hmid!=null && !hmid.isEmpty())
	    	sql.append(" and orderkey_information.hmid ILIKE '%"+hmid.trim().replace("'","''")+"%' ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append(" and orderkey_information.orderkey in (select entitlement_key from ns.so_header  where reseller ILIKE '"+reseller.trim().replace("'","''")+"' ");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
		  
	    pst = con.createStatement();
	    log.info("Support Summary for HM : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	    
	    
	    	while(rs.next()){
	    		SupportData data=new SupportData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		data.setKeyStatus(rs.getString(4));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	return list;
	}

	private List<SubscriptionData> getSubscriptionDetails(String endUser,
			String reseller) {
		List<SubscriptionData> list =new ArrayList<SubscriptionData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT item as SKU, quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and  ");
	    sql.append(" TO_TIMESTAMP(enddate/1000) > current_date THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date ");       
	    sql.append(" THEN 'Active' ELSE 'Expired' END as KeyStatus ");
	    sql.append("  FROM ns.temp_item_info inner join orderkey_information on ns.temp_item_info.entitlementkey=orderkey_information.orderkey ");
	    sql.append("  where product_type='Subscription' and orderkey_information.orderkey in (select entitlement_key from ns.so_header where ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( "  reseller ILIKE '"+reseller.trim().replace("'","''")+"' and ") ;
		if(endUser!=null && !endUser.isEmpty())
			sql.append( "  end_user ILIKE  '%"+endUser.trim().replace("'","''")+"%' )");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;  
	    pst = con.createStatement();
	    log.info("Subscription Summary : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	    
	    	while(rs.next()){
	    		SubscriptionData data=new SubscriptionData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	return list;
	}

	private List<APLicenseData> getLicenseDetails(String endUser,
			String reseller) {
		List<APLicenseData> list =new ArrayList<APLicenseData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT item as SKU, quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and  ");
	    sql.append(" TO_TIMESTAMP(enddate/1000) > current_date THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date ");       
	    sql.append(" THEN 'Active' ELSE 'Expired' END as KeyStatus ");
	    sql.append("  FROM ns.temp_item_info inner join orderkey_information on ns.temp_item_info.entitlementkey=orderkey_information.orderkey ");
	    sql.append("  where product_type ILIKE '%License%' and orderkey_information.orderkey in (select entitlement_key from ns.so_header where ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( "  reseller ILIKE '"+reseller.trim().replace("'","''")+"' and ") ;
		if(endUser!=null && !endUser.isEmpty())
			sql.append( "  end_user ILIKE  '%"+endUser.trim().replace("'","''")+"%' )");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;  
	    pst = con.createStatement();
	    log.info("License Summary : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	    
	    
	    	while(rs.next()){
	    		APLicenseData data=new APLicenseData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	return list;
	}

	private List<HardwareData> getHardwareDetails(String endUser,
			String reseller) {
		Map<String,HardwareData> list =new HashMap<String,HardwareData>();
		
		List<HardwareData> hlist=new ArrayList<HardwareData>();
		
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select item,quantity,orderkey_information.hmid from ns.temp_so_detail inner join ns.so_header   ");
	    sql.append(" on ns.temp_so_detail.\"number\"=ns.so_header.so_number ");       
	    sql.append(" inner join orderkey_information on ns.so_header.entitlement_key=orderkey_information.orderkey ");
	    sql.append(" where product_type in ('AP','BR','SR') ");
	    sql.append("  and rev_rec_start_date is not null and rev_rec_end_date is not null ");   
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( " and ns.so_header.reseller ILIKE'"+reseller.trim().replace("'","''")+"'") ;
		if(endUser!=null && !endUser.isEmpty())
			sql.append( " and ns.so_header.end_user ILIKE  '%"+endUser.trim().replace("'","''")+"%'");
		 sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
	    pst = con.createStatement();
	    
	    log.info("Hardware Summary : SQL Query " + sql);  
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	
	    
	    	while(rs.next()){
	    		HardwareData data =new HardwareData();
	    		
	    		if(null!=list && null!=list.get(rs.getString(1).replaceAll("[^0-9]", ""))){
	    			data=list.get(rs.getString(1).replaceAll("[^0-9]", ""));
	    			List<String> skuList = new ArrayList<String>(Arrays.asList(data.getSku().split(",")));
	    			if(!skuList.contains(rs.getString(1)))
	    				data.setSku(data.getSku()+"," +rs.getString(1));
	    			data.setQuantity(data.getQuantity()+rs.getLong(2));
	    			data.setHmId(data.getHmId()+",'"+rs.getString(3)+"'");
	    					
	    		}else{
	    			data.setSku(rs.getString(1));
	    			data.setQuantity(rs.getLong(2));
	    			data.setHmId("'"+rs.getString(3)+"'");
	    		}
	   
	    		 list.put(rs.getString(1).replaceAll("[^0-9]", ""), data);
	    	}
	    	
	    	for (Entry<String, HardwareData> entry : list.entrySet()) {
	    			int count=getUnitsinUse(entry.getValue().getHmId(),entry.getKey());
	    			entry.getValue().setUnitsUsed(count);
	    			hlist.add(entry.getValue());
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
		}
	
	return hlist;
	
	}
	

	private int getUnitsinUse(String hmId, String key) {
		Connection con=null;
		 int count=0;
		try {
		   
		    Statement pst = null;
		    con=getConnectiontoDB();
		    StringBuffer sql = new StringBuffer();
		    
		    sql.append(" SELECT count (serialnumber) from hiveap_info_from_hm where hmid in ("+hmId.trim()+")   ");
		    sql.append(" AND TO_TIMESTAMP(lastconnecttime/1000)> (CURRENT_DATE-90) ");
		    sql.append(" and substring(serialnumber,0,5) ILIKE '%"+key+"%'");
		    log.info("Units in Use : SQL Query " + sql); 
		    pst = con.createStatement();
		    ResultSet  rs= pst.executeQuery(sql.toString());
		   

		  	while(rs.next()){
		  		count=rs.getInt(1);
		  	}
		}catch(Exception e){
			e.printStackTrace();
		}
		    
		return count;
	}

	private List<SupportData> getSupportDetails(String endUser,String reseller) {
		List<SupportData> list =new ArrayList<SupportData>();
		Connection con=null;
	try {
	    
	    Statement pst = null;
	    con=getConnectiontoDB();
	    
	    StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT item as SKU, quantity,TO_CHAR(TO_TIMESTAMP(enddate/1000), 'YYYY-MM-DD') as EndDate,CASE  WHEN status=1 and  ");
	    sql.append(" TO_TIMESTAMP(enddate/1000) > current_date THEN 'Unused' WHEN status=2 and  TO_TIMESTAMP(enddate/1000) > current_date ");       
	    sql.append(" THEN 'Active' ELSE 'Expired' END as KeyStatus ");
	    sql.append("  FROM ns.temp_item_info inner join orderkey_information on ns.temp_item_info.entitlementkey=orderkey_information.orderkey ");
	    sql.append("  where product_type='Support' and orderkey_information.orderkey in (select entitlement_key from ns.so_header where ");
		if(reseller!=null && !reseller.isEmpty() && !reseller.equalsIgnoreCase("%admin%"))
			sql.append( "  reseller ILIKE '"+reseller.trim().replace("'","''")+"' and ") ;
		if(endUser!=null && !endUser.isEmpty())
			sql.append( "  end_user ILIKE  '%"+endUser.trim().replace("'","''")+"%' )");
		sql.append( " and item not ILIKE '%EVAL%' order by item desc ") ;
	    pst = con.createStatement();
	    log.info("Support Summary : SQL Query " + sql); 
	    
	    ResultSet  rs= pst.executeQuery(sql.toString());
	   
	    
	    
	    	while(rs.next()){
	    		SupportData data=new SupportData();
	    		data.setSku(rs.getString(1));
	    		data.setQuantity(rs.getString(2));
	    		data.setEndDate(rs.getString(3));
	    		data.setKeyStatus(rs.getString(4));
	    		
	    		list.add(data);
	    	}
		
		} catch(Exception e){
			e.printStackTrace();
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
