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

public class SOSearchExtract extends HttpServlet{
	
	private final Log log = LogFactory.getLog(getClass());
	
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		 
		Connection con = null;
		HttpSession session=request.getSession(false);	 	
		String url = "jdbc:postgresql://10.16.194.69:5432/ls";
		String user = "test";
		String password = "aerohive123!";
		try {
			if(session!=null && session.getAttribute("reseller")!=null && session.getAttribute("sonumber")!=null){
				PreparedStatement pst = null;
				Class.forName("org.postgresql.Driver");
						
				con = DriverManager.getConnection(url, user, password);
						
						//String  reseller  = "%"+request.getParameter("reseller")+"%";
				StringBuffer sql = new StringBuffer();
			    sql.append("select o.orderkey,ib.salesordernumber,ib.item,so_header.reseller,so_header.end_user as enduser,ib.serialnumber,ib.hmid, ");
			    sql.append("so_header.po_check_number as ponumber ");
			  	sql.append(" from  ns.temp_so_header so_header  inner join ns.temp_ib ib on substring(ib.salesordernumber from 14 for 100) =so_header.so_number");
			  	sql.append(" inner join orderkey_information o on so_header.entitlement_key=o.orderkey ");
			  	sql.append("where (ib.producttype='AP' or ib.producttype='License - AP')  and so_header.so_number='"+session.getAttribute("sonumber")+"' ");
		  	  	sql.append(" and reseller ILIKE '%"+session.getAttribute("reseller")+"%'");
			  	log.info("Sales Order Search Extract Query :" +sql);
			  	
				pst = con.prepareStatement(sql.toString());
				//if(session!=null && session.getAttribute("reseller")!=null)
					//pst.setString(1,(String) session.getAttribute("reseller"));
						  
				ResultSet  rs= pst.executeQuery();	   	   
				FileWriter sw= new FileWriter("SOSearchResults.csv");  
				CSVWriter writer = new CSVWriter(sw,'|');
						    	
				writer.writeAll(rs, true);
				sw.close();  
					              
				FileInputStream fis = new FileInputStream("SOSearchResults.csv");  
				byte b[];  
				int x = fis.available();  
				b = new byte[x];  
				System.out.println(" b size"+b.length);  
				fis.read(b);  
					              
					            // FIXME: this is ugly  
				if (response != null)  {
					response.setContentType("application/ms-excel"); 
					response.setHeader("Content-Disposition", "attachment; filename=SOSearchResults.csv"); 
				}
					                //response.setContentType(mimeType);  
					              
				OutputStream os = response.getOutputStream();  
				os.write(b);  
				os.flush();   
				writer.flush();
				writer.close();
				fis.close();
			}else{
				log.info("Sales Order Search Extract :Reseller Blank");
			    String nextJSP = "/login.jsp";
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			    dispatcher.forward(request,response);
			}
		 
					 
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	 }
  }