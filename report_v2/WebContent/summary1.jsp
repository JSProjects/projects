<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>Summary Details</title>
 <link rel="stylesheet" type="text/css" href="css/displaytag.css">
 <link rel="stylesheet" type="text/css" href="css/sum.css">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script src="js/jquery.autocomplete.js"></script>
    <script>
        function openWin(idVal) 
        { 
                idVal= idVal.substring(0, idVal.length-1); 
                var url = 'sosearch'+ idVal; 
                window.open(url,'_blank','width=600,height=300'); 
        } 
        
        function openSO(idVal) 
        { 
                idVal= idVal.substring(0, idVal.length-1); 
                var url = 'search'+ idVal; 
                window.open(url,'_blank','width=600,height=300'); 
        } 
        function openEndUser(idVal){
        	idVal=idVal.substring(0,idVal.length-1);
        	idVal=idVal.substr(0,16);
        	var url='summary'+idVal;
        	window.open(url,'_blank','width=600,height=300')
        }
        function DoSubmit(sel)
        {
             this.form.submit();
              
              
        }
        function reset()
        {
        	alert(1);
        	document.getElementById('pagesize').selectedIndex = -1;
        }
        jQuery(function(){
        	$("#reseller").autocomplete("list.jsp");
        	});  
        
</script> 
</head>
<body>

    <table border="0" cellpadding="0" cellspacing="0" class="header">
    <tr>
 		 <td colspan="2">
    </tr>
    </table>
    <ul class="semiopaquemenu">
    <li><a href="summary.jsp" class="selected">Summary</a></li>
	<li><a href="searchfield.jsp">Field Search</a></li>
	<li><a href="searchdate.jsp">Date Search</a></li>
	<li style="float:right;font: bold 14px Arial;">${sessionScope.reseller}</li>
	<li style="float:right;"><a href="logout.jsp">Log Off</a></li>
	</ul> 
	<form method="get" id="form" action="summary">
		<div id="tfheader">
			<c:if test ="${sessionScope.reseller!='admin'}">
		        <input type="text" class="tftextinput" placeholder="Enter EndUser Name/ID" name="endUser" id="endUser" 
				  size="21" maxlength="120"><input type="submit" value="FilterBy EndUser" class="tfbutton">
				  
				<input type="submit" value="ClearFilter" class="tfbutton">
			</c:if>
			<c:if test ="${sessionScope.reseller=='admin'}">
			    <ul id="crumbs">
					<c:if test ="${not empty sessionScope.resellerName}">
							<li>${sessionScope.label}</li>
						</c:if>
						<c:if test ="${not empty sessionScope.endUserName}">
							<li>${sessionScope.label1}</li>
						</c:if>
				</ul>
				<input type="text" class="text" placeholder="Enter Reseller Name" name="reseller" id="reseller">
				<input type="text" class="text" placeholder="Enter End User" name="endUser" id="endUser">
				<input type="submit" value="View Summary" class="resellerbutton">
				<input type="submit" value="ClearFilter" class="resellerbutton" onclick="document.getElementById('pagesize').selectedIndex = -1">
			</c:if>
		</div>
<c:if test="${sessionScope.summarylist!=null}">		
<label>Items Per Page:</label>
	<select name="pagesize" id="pagesize" onchange="DoSubmit(this);">
  	 	<option value="30" <% if(request.getAttribute("pagesize")!=null && request.getAttribute("pagesize").equals("30")) {out.println("selected='selected'");}  %>>30</option>
   	 	<option value="60" <% if(request.getAttribute("pagesize")!=null && request.getAttribute("pagesize").equals("60")) {out.println("selected='selected'");}  %>>60</option>
   	 	<option value="90" <% if(request.getAttribute("pagesize")!=null && request.getAttribute("pagesize").equals("90")) {out.println("selected='selected'");}  %>>90</option>
   	 	<option value="120" <% if(request.getAttribute("pagesize")!=null && request.getAttribute("pagesize").equals("120")) {out.println("selected='selected'");}  %>>120</option> 
	</select>
</c:if>  
<%
String value =((String) (request.getAttribute("pagesize")!=null && !request.getAttribute("pagesize").equals("") ? request.getAttribute("pagesize") :"30"));
int intValue = Integer.parseInt(value);
%>
     
   
       <display:table class="data" id="test" export="true" uid="models" sort="list" name="sessionScope.summarylist" defaultsort="2" defaultorder="descending" excludedParams="*"  pagesize="<%=intValue%>">
            
 
                <display:column property="poNumber" title="PO Number"
                                sortable="true" sortName="poNumber" headerClass="sortable" />
                <display:column property="shipDate" title="Shipped Date"
                                sortable="true" sortName="shipDate" headerClass="sortable" />
               <display:column property="entitlementKey" title="Entitlement Key"
                                sortable="true" sortName="entitlementKey" headerClass="sortable" />
              <c:if test ="${sessionScope.reseller=='admin'}">
              <display:column property="keyStatus" title="Activation Status"
                                 sortable="true" sortName="keyStatus" headerClass="sortable" />
               </c:if>
               <c:if test ="${not empty sessionScope.endUserName && empty sessionScope.resellerName}">
              <display:column property="billingCustomer" title="Billing Customer (Partner)"
                                 sortable="true" sortName="billingCustomer" headerClass="sortable" />
               </c:if>
               <%--  <display:column property="soNumbber" title="Sales Order Number"
                                sortable="true" sortName="soNumbber"  headerClass="sortable" /> --%>
                <display:column property="soNumbber" title="Sales Order Number"  href="javascript:openSO('#')" paramId="so" paramProperty="soNumbber" 
                                sortable="true" sortName="soNumbber"  headerClass="sortable" />
                <display:column title="Sales Order Number" property="soNumbber" media="excel" />
                <display:column property="endUser" title="End User" href="javascript:openEndUser('#')" paramId="endUser"  paramProperty="endUser"  
								 style="text-align:left;" sortable="true" sortName="endUser" headerClass="sortable" />
                <display:column title="End User" property="endUser" media="excel" />
               
                <display:column property="hmId" title="HM ID"
                                sortable="true" sortName="hmId" headerClass="sortable" />
               <display:column property="systemType" title="System Type"
                                sortable="true" sortName="systemType" headerClass="sortable" />
                <display:column property="keyType" title="Key Type"
                                sortable="true" sortName="keyType" headerClass="sortable" />
                <display:column property="apNumber" title="Number of HiveOS Devices" href="javascript:openWin('#')" paramId="sonumber" paramProperty="soNumbber"
                                sortable="true" sortName="apNumber" headerClass="sortable" />
                <display:column property="supportQuantity" title="Support Quantity" 
                                sortable="true" sortName="supportQuantity" headerClass="sortable" />
                <display:column property="licenseStartDate" title="Subscription Start Date"
                                sortable="true" sortName="licenseStartDate" headerClass="sortable" />
                <display:column property="licenseEndDate" title="Subscription End Date"
                                sortable="true" sortName="licenseEndDate" headerClass="sortable" />
                <display:column property="supportStartDate" title="Support Start Date"
                                sortable="true" sortName="supportStartDate" headerClass="sortable" />
                <display:column property="supportEndDate" title="Support End Date"
                                sortable="true" sortName="supportEndDate" headerClass="sortable" />
                <display:setProperty name="export.csv.filename" value="summary.csv" />   
                <display:setProperty name="export.excel.filename" value="summary.xls" /> 
                <display:setProperty name="basic.msg.empty_list" value="Please enter Reseller/End User to View Summary as Admin" />
            </display:table>
</form>	
</body>
</html>