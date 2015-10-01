
<!DOCTYPE html>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

    <meta charset="utf-8" />
    <title>Aeroport</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="https://www.aerohive.com/sites/all/themes/aerohivenetworks/favicon.ico" type="image/x-icon" />
    		
	<!-- CSS Files -->
		
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/animate.min.css" />
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/style2.css" />
	<link rel="stylesheet" href="css/flexslider.css" />
	<link rel="stylesheet" href="css/font-awesome.css" />
	<link rel="stylesheet" href="css/owl.carousel.css" />
	<link rel="stylesheet" href="css/settings.css" />
	<link rel="stylesheet" href="css/prettyPhoto.css" />
	<link rel="stylesheet" href="css/responsive.css" />
	<link rel="stylesheet" href="css/YTPlayer.css" />

    
    <link rel="stylesheet" type="text/css" href="css/sum.css">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/displaytag.css">
    
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap1.js"></script>
	<script type="text/javascript" src="js/jquery.appear.js"></script>
	<script type="text/javascript" src="js/waypoints.min.js"></script>
	<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
	<script type="text/javascript" src="js/modernizr-latest.js"></script>
	<script type="text/javascript" src="js/SmoothScroll.js"></script>
	<script type="text/javascript" src="js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.superslides.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="js/jquery.sticky.js"></script>
	<script type="text/javascript" src="js/owl.carousel.js"></script>
	<script type="text/javascript" src="js/jquery.isotope.js"></script>
	<script type="text/javascript" src="js/rev-slider/jquery.themepunch.plugins.min.js"></script>
    <script type="text/javascript" src="js/rev-slider/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript" src="js/jquery.mb.YTPlayer.js"></script>
	<script type="text/javascript" src="js/jquery.fitvids.js"></script>
	<script type="text/javascript" src="js/plugins.js"></script> 
<!-- 	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> -->
	<script src="js/jquery.autocomplete.js"></script>
    <script>
        function openWin(idVal) 
        { 
                idVal= idVal.substring(0, idVal.length-1); 
                var url = 'sosearch'+ idVal; 
                window.open(url,'_blank'); 
        } 
        
        function openSO(idVal) 
        { 
                idVal= idVal.substring(0, idVal.length-1); 
                var url = 'search'+ idVal; 
                window.open(url,'_blank'); 
        } 
        function openEndUser(idVal){
        	idVal=idVal.substring(0,idVal.length-1);
        	idVal=idVal.substr(0,16);
        	var url='summary'+idVal;
        	window.open(url,'_blank');
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
	
	<!-- End CSS Files -->
 
</head>
	
	
<body data-spy="scroll" data-target=".nav-menu" data-offset="50">

<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-NT2B6R"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-NT2B6R');</script>
<!-- End Google Tag Manager -->

<!-- Marketo Munchkin -->
<script src="https://munchkin.marketo.net/munchkin.js" type="text/javascript"></script> <script>mktoMunchkin("828-ALH-889");</script>
<!-- End Marketo Munchkin -->

		<div id="pageloader">   
            <div class="loader-item">
              <img src="images/loading.gif" alt='loader' />
            </div>
        </div>

<!-- Home Section -->
	<section id="home" class="">
			
		
	<!-- Navigation Section -->
	<section id="navigation" class="shadow">
	
		<div class="inner navigation">
			
			<!-- Logo Img -->
			<div class="logo">
				<a class="scroll" href="https://www.aerohive.com"><img src="https://www.aerohive.com/sites/all/themes/aerohivenetworks/logo.png" alt="Aerohive Networks"/></a>
			</div>
			<div id="utility_block">
			<div class="user_utility">
			<span class="name">Welcome back,${sessionScope.reseller}</span><a class="account" href="logout.jsp">Log Out</a>
			</div>
			</div>
			<!-- Nav Menu -->
			<div class="nav-menu">
				
				<ul class="nav main-nav">
				
					<li class="active"><a class="scroll" href="#">Summary</a></li>
                    <li><a class="scroll" href="searchfield.jsp">Field Search</a></li>
					<li><a class="scroll" href="searchdate.jsp">Date Search</a></li>
					<c:if test ="${sessionScope.reseller=='admin'}">
					<li><a class="scroll" href="activesummary.jsp">Activation Summary</a></li>
				    </c:if>
				</ul>
				
			</div>
			
			
				<!-- Dropdown Menu For Mobile Devices-->
				<div class="dropdown mobile-drop">
				  <a data-toggle="dropdown" class="mobile-menu" href="#"><i class="fa fa-bars"></i></a>
				  <ul class="nav dropdown-menu fullwidth" role="menu" >
				<li><a class="scroll" href="summary.jsp">Summary</a></li>
                    <li><a class="scroll" href="searchfield.jsp">Field Search</a></li>
					<li><a class="scroll" href="searchdate.jsp">Date Search</a></li>
					<c:if test ="${sessionScope.reseller=='admin'}">
					<li><a class="scroll" href="activesummary.jsp">Activation Summary</a></li>
					</c:if>
				  </ul>
				</div>
		
			<div class="clear"></div>
		</div>
	
	</section>
	<!-- End Navigation Section -->
	
	
		<!-- Rev Slider -->
		<section id="slider" class="contain">
		
		 <div class="tp-banner">
		
		
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
				
<div class="container" style="width:600px;">
    <div class="panel panel-primary dialog-panel">
      <div class="panel-heading">
        <h5>View Summary As Admin</h5>
      </div>
      <div class="panel-body"> 
				<input type="text" class="text" placeholder="Enter Reseller Name" name="reseller" id="reseller">
				<input type="text" class="text" placeholder="Enter End User" name="endUser" id="endUser">
				<input type="submit" value="View" class="resellerbutton">
				<input type="submit" value="Clear" class="resellerbutton" onclick="document.getElementById('pagesize').selectedIndex = -1">
          </div>
            </div>
            </div>
          
            
				<!-- <fieldset><legend style="font-size:16px">View Summary-Admin</legend> 
				<input type="text" class="text" placeholder="Enter Reseller Name" name="reseller" id="reseller">
				<input type="text" class="text" placeholder="Enter End User" name="endUser" id="endUser">
				<input type="submit" value="View Summary" class="resellerbutton">
				<input type="submit" value="ClearFilter" class="resellerbutton" onclick="document.getElementById('pagesize').selectedIndex = -1">
				</fieldset> -->
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
     
   
       <display:table class="table blue" id="test" export="true" uid="models" sort="list" name="sessionScope.summarylist" defaultsort="2" defaultorder="descending" excludedParams="*"  pagesize="<%=intValue%>">
            
 
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
                <display:setProperty name="basic.msg.empty_list" value="" />
            </display:table>
</form>	
</div>
</section>
	
	</section>

	<!-- JS Files -->
	
	





</body>

</html>