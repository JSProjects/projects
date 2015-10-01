package report;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVWriter;

public class ViewSearch extends HttpServlet{
	
	private final Log log = LogFactory.getLog(getClass());
	
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		 
		HttpSession session=request.getSession(false);
		
		if(session!=null && session.getAttribute("fieldsearch")!=null){

				 	Connection con = null;
				 	
				 	String url = "jdbc:postgresql://10.16.194.69:5432/ls";
				 	String user = "test";
				 	String password = "aerohive123!";
				   
				 			
			try {
					PreparedStatement pst = null;
					Class.forName("org.postgresql.Driver");
					
					con = DriverManager.getConnection(url, user, password);
					
					//String  reseller  = "%"+request.getParameter("reseller")+"%";
					StringBuffer sql = new StringBuffer();
					if(session!=null && session.getAttribute("sno")!=null){
					 	sql.append("select ib.item AS \"SKU\",o.orderkey AS \"ENTITLEMENT KEY\",so_header.so_number AS \"SALES ORDER NUMBER\", so_header.po_check_number AS \"PO NUMBER\",so_header.reseller AS \"BILLING CUSTOMER\", ");
					}else{
					 	sql.append("select so_item.item AS \"SKU\",so_item.entitlementkey AS \"ENTITLEMENT KEY\",so_item.quantity AS \"QUANTITY\",so_header.so_number AS \"SALES ORDER NUMBER\", so_header.po_check_number AS \"PO NUMBER\",so_header.reseller AS \"BILLING CUSTOMER\", ");
					}
				  	//sql.append("select so_item.item AS \"SKU\",so_item.entitlementkey AS \"ENTITLEMENT KEY\",so_item.quantity AS \"QUANTITY\",so_header.so_number AS \"SALES ORDER NUMBER\", so_header.po_check_number AS \"PO NUMBER\",so_header.reseller AS \"BILLING CUSTOMER\", ");
				  	sql.append("so_header.end_user AS \"END USER\",so_header.ship_date AS \"SHIPPED DATE\",o.hmid AS \"HMID\", ");
					sql.append(" o.apnumber AS \"NUMBER OF HIVE OS DEVICES\", TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD')  AS \"LICENSE START DATE \",TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') AS \"LICENSE END DATE\",  o.cvgnumber \"NUMBER OF CVG\", ");
					sql.append("TO_CHAR(TO_TIMESTAMP(o.cvgsubstartdate/1000), 'YYYY-MM-DD') AS \"CVG SUBSCRIPTION START DATE\",TO_CHAR(TO_TIMESTAMP(o.cvgsubenddate/1000), 'YYYY-MM-DD') AS \"CVG SUBSCRIPTION  END DATE\",o.vhmnumber AS \"VHM NUMBER\",o.evaluedays AS \"NUMBER OF DAYS\", ");
					sql.append("TO_CHAR(TO_TIMESTAMP(o.startdate/1000), 'YYYY-MM-DD')  AS \"SUPPORT START DATE\" , TO_CHAR(TO_TIMESTAMP(o.enddate/1000), 'YYYY-MM-DD') AS \"SUPPORT END DATE\" ");
					if(session!=null && session.getAttribute("sno")!=null){
						sql.append("  from ns.so_header so_header  inner join ns.temp_ib ib on substring(ib.salesordernumber from 14 for 100) =so_header.so_number ");   
						sql.append("  inner join orderkey_information o  on so_header.entitlement_key=o.orderkey ");
					}else{
						sql.append(" from ns.so_header so_header inner join ns.temp_so_item so_item on so_header.so_number=so_item.so_number");
						sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
					}
					
					if(session!=null && session.getAttribute("sno")!=null)
						sql.append(" where ib.serialnumber='"+session.getAttribute("sno")+"' ");
					else if(session!=null && session.getAttribute("so")!=null)
						sql.append("where so_header.so_number='"+session.getAttribute("so")+"'");
					else if(session!=null && session.getAttribute("enduser")!=null)
						sql.append("where so_header.end_user ILIKE '%"+session.getAttribute("enduser")+"%'");
					else if(session!=null && session.getAttribute("ek")!=null)
						sql.append("where so_header.entitlement_key='"+session.getAttribute("ek")+"'");
					if(session!=null && session.getAttribute("reseller")!=null)
					sql.append(" and so_header.reseller ILIKE ? order by o.orderkey");
					if(session!=null && session.getAttribute("sno")!=null)
					sql.append(" LIMIT 1" );
					
					log.info("Field Search Extract Query :" +sql);
					pst = con.prepareStatement(sql.toString());
					if(session!=null && session.getAttribute("reseller")!=null)
					pst.setString(1,(String) session.getAttribute("reseller"));
					  
					ResultSet  rs= pst.executeQuery();	   	   
				    FileWriter sw= new FileWriter("FieldSearchResults.csv");  
				    CSVWriter writer = new CSVWriter(sw,'|');
					    	
				    writer.writeAll(rs, true);
				           
				    sw.close();  
				              
				    FileInputStream fis = new FileInputStream("FieldSearchResults.csv");  
				    byte b[];  
				    int x = fis.available();  
				    b = new byte[x];  
				    System.out.println(" b size"+b.length);  
				    fis.read(b);  
				              
				            // FIXME: this is ugly  
				    if (response != null)  {
				        response.setContentType("application/ms-excel"); 
						response.setHeader("Content-Disposition", "attachment; filename=FieldSearchResults.csv"); 
				     }
				                //response.setContentType(mimeType);  
				              
				     OutputStream os = response.getOutputStream();  
				     os.write(b);  
				     os.flush();   
				     writer.flush();
				     writer.close();
				     fis.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
				}
  }
		
  else if(session!=null && session.getAttribute("datesearch")!=null){

		 	Connection con = null;
		 	
		 	String url = "jdbc:postgresql://10.16.194.69:5432/ls";
		 	String user = "test";
		 	String password = "aerohive123!";
		   
		 			
	try {
			PreparedStatement pst = null;
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager.getConnection(url, user, password);
			
			//String  reseller  = "%"+request.getParameter("reseller")+"%";
			StringBuffer sql = new StringBuffer();

		  	sql.append("select so_item.item AS \"SKU\",so_item.entitlementkey AS \"ENTITLEMENT KEY\",so_item.quantity AS \"QUANTITY\",so_header.so_number AS \"SALES ORDER NUMBER\", so_header.po_check_number AS \"PO NUMBER\",so_header.reseller AS \"BILLING CUSTOMER\", ");
		  	sql.append("so_header.end_user AS \"END USER\",so_header.ship_date AS \"SHIPPED DATE\",o.hmid AS \"HMID\",");
			sql.append(" o.apnumber AS \"NUMBER OF HIVE OS DEVICES\", TO_CHAR(TO_TIMESTAMP(o.substartdate/1000), 'YYYY-MM-DD')  AS \"LICENSE START DATE \",TO_CHAR(TO_TIMESTAMP(o.subenddate/1000), 'YYYY-MM-DD') AS \"LICENSE END DATE\",  o.cvgnumber \"NUMBER OF CVG\" ,");
			sql.append("TO_CHAR(TO_TIMESTAMP(o.cvgsubstartdate/1000), 'YYYY-MM-DD') AS \"CVG SUBSCRIPTION START DATE\",TO_CHAR(TO_TIMESTAMP(o.cvgsubenddate/1000), 'YYYY-MM-DD') AS \"CVG SUBSCRIPTION  END DATE\",o.vhmnumber AS \"VHM NUMBER\",o.evaluedays AS \"NUMBER OF DAYS\",");
			sql.append("TO_CHAR(TO_TIMESTAMP(o.startdate/1000), 'YYYY-MM-DD')  AS \"SUPPORT START DATE\" , TO_CHAR(TO_TIMESTAMP(o.enddate/1000), 'YYYY-MM-DD') AS \"SUPPORT END DATE\" ");
			sql.append(" from ns.so_header so_header inner join ns.temp_so_item so_item on so_header.so_number=so_item.so_number");
			sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
			
			if(session.getAttribute("startdate")!=null && session.getAttribute("enddate")!=null){
					sql.append("where so_header.date::DATE>='"+session.getAttribute("startdate")+"'");
					sql.append("and so_header.date::DATE<='"+session.getAttribute("enddate")+"'");
			}
			if(session.getAttribute("expdays")!=null){
	
					sql.append("where o.subenddate>='"+getDate(null)+"'");
					sql.append("and o.subenddate<='"+getDate((String) session.getAttribute("expdays"))+"'");
					
			}
			if(session!=null && session.getAttribute("reseller")!=null)
			sql.append(" and so_header.reseller ILIKE ? order by o.orderkey");
			
			pst = con.prepareStatement(sql.toString());
			if(session!=null && session.getAttribute("reseller")!=null)
			pst.setString(1,(String) session.getAttribute("reseller"));
			 
			log.info("Search Date Extract Query :" +sql);
			ResultSet  rs= pst.executeQuery();	   	   
		    FileWriter sw= new FileWriter("DateSearchResults.csv");  
		    CSVWriter writer = new CSVWriter(sw,'|');
			    	
		    writer.writeAll(rs, true);
		           
		    sw.close();  
		              
		    FileInputStream fis = new FileInputStream("DateSearchResults.csv");  
		    byte b[];  
		    int x = fis.available();  
		    b = new byte[x];  
		    System.out.println(" b size"+b.length);  
		    fis.read(b);  
		              
		            // FIXME: this is ugly  
		    if (response != null)  {
		        response.setContentType("application/ms-excel"); 
				response.setHeader("Content-Disposition", "attachment; filename=DateSearchResults.csv"); 
		     }
		                //response.setContentType(mimeType);  
		              
		     OutputStream os = response.getOutputStream();  
		     os.write(b);  
		     os.flush();   
		     writer.flush();
		     writer.close();
		     fis.close();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	
		}else{
			   log.info("Search Field/Date Extract : Reseller Blank  "); 
			   String nextJSP = "/login.jsp";
	   	       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
	   	       dispatcher.forward(request,response); 
		}

		 
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
