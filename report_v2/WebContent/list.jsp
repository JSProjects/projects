<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
 
   <%
   try{
     String s[]=null;
 
    /*  Class.forName("org.postgresql.Driver");
     //Connection con =DriverManager.getConnection("jdbc:postgresql://localhost:5432/ls","root","test");
     Statement st=con.createStatement();
     ResultSet rs = st.executeQuery("SELECT distinct trim(regexp_replace(reseller,'[0-9]*' ,'')) FROM ns.so_header");
  
     HttpSession session1 = request.getSession();*/
     List<String> li = (List<String>)session.getAttribute("resellerList");
 
      /*  while(rs.next())
       {
           li.add(rs.getString(1));
       }
  */
       String[] str = new String[li.size()];
       Iterator it = li.iterator();
 
       int i = 0;
       while(it.hasNext())
       {
           String p = (String)it.next();
           str[i] = p;
           i++;
       }
 
    //jQuery related start
       String query = (String)request.getParameter("q");
 	/*    System.out.println("Query String :" +query); */
       int cnt=1;
       for(int j=0;j<str.length;j++)
       {
    	  /*  System.out.println("Print j:" +str[j].toUpperCase() +" Print query :" + query.toUpperCase()); */
           if(str[j].toUpperCase().startsWith(query.toUpperCase()))
           {
              out.print(str[j]+"\n");
              if(cnt>=5)// 5=How many results have to show while we are typing(auto suggestions)
              break;
              cnt++;
            }
       }
    //jQuery related end
 
/* rs.close();
st.close();
con.close(); */
 
}
catch(Exception e){
e.printStackTrace();
}
 
//www.java4s.com
%>