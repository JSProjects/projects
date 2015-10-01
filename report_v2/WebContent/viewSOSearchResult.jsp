<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sales Order Details</title>
  <link rel="stylesheet" type="text/css" href="css/top.css">
  <link rel="stylesheet" type="text/css" href="css/so.css">
  <link rel="stylesheet" type="text/css" href="css/displaytag.css">
 <script language="JavaScript">
function CopyToClipboard(text) {
	alert(text);
    Copied = text.createTextRange();
    Copied.execCommand("Copy");
}
</script>
</head>
<body>
      <form method="post" name="frm" action="viewSOSearch">
    <table border="0" cellpadding="0" cellspacing="0" class="header">
    <tr>
 		 <td colspan="2">
    </tr>
    </table>
    <ul class="semiopaquemenu">
  	<li><a href="summary.jsp">Summary</a></li>
	<li><a href="searchfield.jsp">Field Search</a></li>
	<li><a href="searchdate.jsp">Date Search</a></li>
	<li style="float:right;font: bold 14px Arial;">${sessionScope.reseller}</li>
	<li style="float:right;font: 14px Arial;"><a href="logout.jsp">Log Off</a></li>
	</ul> 
	
	<ul id="crumbs">
	<li>Sales Order Search</li>
	<li>${sessionScope.sovalue}</li>
	</ul>

      <display:table class="data" export="true" uid="models" name="sessionScope.solist" sort="list" excludedParams="*"  pagesize="30" >
      			<display:column property="serialNumber" title="Serial Number"
                                sortable="true" sortName="serialNumber"  headerClass="sortable" />
                <display:column property="poNumber" title="PO Number"
                                sortable="true" sortName="poNumber" headerClass="sortable" />
 			    <display:column property="entitlementKey" title="Entitlement Key"
                                sortable="true" sortName="entitlementKey" headerClass="sortable" />
                <display:column property="soNumbber" title="Sales Order Number" 
                                sortable="true" sortName="soNumbber"  headerClass="sortable" />
                <display:column property="endUser" title="End User"
                                sortable="true" sortName="endUser" headerClass="sortable" />
                <display:column property="sku" title="SKU"
                                sortable="true" sortName="sku" headerClass="sortable" />
        <display:setProperty name="export.csv.filename" value="SalesOrderSearchResult.csv" />
           <display:setProperty name="export.excel.filename" value="SalesOrderSearchResult.xls" />        
     </display:table>
     </form>
</body>
</html> --%>

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

 <link rel="stylesheet" type="text/css" href="css/displaytag.css">
 <link rel="stylesheet" type="text/css" href="css/sum.css">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script src="js/jquery.autocomplete.js"></script>

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
				
					<li><a class="scroll" href="summary.jsp">Summary</a></li>
                    <li><a class="scroll" href="searchfield.jsp">Field Search</a></li>
					<li><a class="scroll" href="searchdate.jsp">Date Search</a></li>
					<li><a class="scroll" href="activesummary.jsp">Activation Summary</a></li>
				
				</ul>
				
			</div>
			
			
				<!-- Dropdown Menu For Mobile Devices-->
				<div class="dropdown mobile-drop">
				  <a data-toggle="dropdown" class="mobile-menu" href="#"><i class="fa fa-bars"></i></a>
				  <ul class="nav dropdown-menu fullwidth" role="menu" >
					<li><a class="scroll" href="summary.jsp">Summary</a></li>
                    <li><a class="scroll" href="searchfield.jsp">Field Search</a></li>
					<li><a class="scroll" href="searchdate.jsp">Date Search</a></li>
					<li><a class="scroll" href="activesummary.jsp">Activation Summary</a></li>
				  </ul>
				</div>
		
			<div class="clear"></div>
		</div>
	
	</section>
	<!-- End Navigation Section -->
	
	
		<!-- Rev Slider -->
		<section id="slider" class="contain">
		
		 <div class="tp-banner">
		
<form method="post" name="frm">
<ul id="crumbs">
	<li>Sales Order Search</li>
	<li>${sessionScope.sovalue}</li>
	</ul>

      <display:table class="table" export="true" uid="models" name="sessionScope.solist" sort="list" excludedParams="*"  pagesize="30" >
      			<display:column property="serialNumber" title="Serial Number"
                                sortable="true" sortName="serialNumber"  headerClass="sortable" />
                <display:column property="poNumber" title="PO Number"
                                sortable="true" sortName="poNumber" headerClass="sortable" />
 			    <display:column property="entitlementKey" title="Entitlement Key"
                                sortable="true" sortName="entitlementKey" headerClass="sortable" />
                <display:column property="soNumbber" title="Sales Order Number" 
                                sortable="true" sortName="soNumbber"  headerClass="sortable" />
                <display:column property="endUser" title="End User"
                                sortable="true" sortName="endUser" headerClass="sortable" />
                <display:column property="sku" title="SKU"
                                sortable="true" sortName="sku" headerClass="sortable" />
        <display:setProperty name="export.csv.filename" value="SalesOrderSearchResult.csv" />
           <display:setProperty name="export.excel.filename" value="SalesOrderSearchResult.xls" />        
     </display:table>
     
</form> 		
		</div>
	</section>
	</section>

	<!-- JS Files -->
	
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




</body>

</html>