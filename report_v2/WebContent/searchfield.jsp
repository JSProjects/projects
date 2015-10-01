<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

     <link rel="stylesheet" type="text/css" href="css/top.css">
      <link rel="stylesheet" type="text/css" href="css/login.css">
      
      <link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script src="js/jquery.autocomplete.js"></script>
<script>

function validateForm() {
    var so_number = document.forms["frm"]["so"].value;
    var enduser = document.forms["frm"]["enduser"].value;
    var key = document.forms["frm"]["ek"].value;
    var sno = document.forms["frm"]["sno"].value;
    var po = document.forms["frm"]["po"].value;
    var hm = document.forms["frm"]["hm"].value;
    if ((so_number == null || so_number == "") && (enduser == null || enduser == "") && (key == null || key == "") && (sno == null || sno == "") && (po ==null || po =="") && (hm ==null || hm ==""))  {
    	document.getElementById("note").style.visibility='visible';
    	document.getElementById("note").style.color="red";
        return false;
    }
}

function check()
{
	var sno = document.forms["frm"]["sno"].value;
    var message = document.getElementById('confirmMessage');
	var badColor = "#ff6666";
	var goodColor = "#008000";
	var pattern = /^\d{14}$/;
    if(sno!=null && sno!=""){
    	var pass = document.getElementById('sno');
    	var numbers = /^[0-9]+$/;
    	if(!sno.match(numbers)){
    	      pass.style.backgroundColor = badColor;
    	    message.innerHTML = "Please Input Numeric Value";
    	}else if(!pattern.test(sno)) {
    		pass.style.backgroundColor = badColor; 
    		message.innerHTML = "Expecting 14 digits";
    	}else{
    		pass.style.backgroundColor = goodColor;
    		message.innerHTML = "";
    	}
    }
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
<li><a href="summary.jsp" >Summary</a></li>
<li><a href="searchfield.jsp" class="selected">Field Search</a></li>
<li><a href="searchdate.jsp">Date Search</a></li>
<li style="float:right;font: bold 14px Arial;">${sessionScope.reseller}</li>
<li style="float:right;"><a href="logout.jsp">Log Off</a></li>
<!--<c:if test ="${sessionScope.reseller=='admin'}">
<div style="float:right;margin-right:20px;margin-top:0px;"> 
<form method="get" name="frm" action="summary">
<input type="text" class="text" placeholder="Enter Reseller Name" name="reseller" id="reseller">
<input type="submit" value="View Summary" class="resellerbutton">
</form>
</div>
 <li id="search">
        <form action="summary" method="get">
            <input type="text" name="reseller" id="search_text" placeholder="Search"/>
            <input type="button" name="View Summary" id="search_button"></a>
        </form>
    </li> 
</c:if>-->

</ul>
<form method="get" name="frm" onsubmit="return validateForm()" action="search">
 <div id="search">
<div id="fieldsearch">
</div>
<div id="contactform">


    <div id="first">
        <div id="name">
            <div id="description">Sales Order:</div> 
            <input type="text" name="so" id ="id" placeholder="Enter Sales Order Number">
        </div> 
        <div id="surname">
            <div id="description">End User:</div> 
            <input type="text" name="enduser" id="enduser" placeholder="Enter End User Name/ID"> 
        </div>
    </div>
    <div id="second">
        <div id="contact">
        	<div id="description">Entitlement Key:</div>
			<input type="text" name="ek" id="ek" placeholder="Enter Entitlement Key">
		</div>
		 <div id="secondsurname">
            <div id="description">Serial Number:</div> 
            <input type="text" name="sno" id="sno" onkeyup="check(); return false;" placeholder="Enter Serial Number"> 
            <span id="confirmMessage" class="confirmMessage"></span>
        </div>
    </div>
     <div id="third">
        <div id="contact">
        	<div id="description">HiveManager ID:</div>
			<input type="text" name="hm" id="hm" placeholder="Enter HiveManager ID">
		</div>
		 <div id="secondsurname">
            <div id="description">Purchase Order:</div> 
            <input type="text" name="po" id="po" placeholder="Enter Purchase Order Number"> 
        </div>
    </div>
<!--     <div id="po">
     	<div id="nam">
            <div id="description">Purchase Order:</div> 
            <input type="text" name="po" id="po" placeholder="Enter Purchase Order Number"> 
      </div>
      </div> -->
    
        <div>
    <input type="submit" value="search" onClick="submitAction();" class="tfbutton">
    <div id="note">*Please search for one field at a time</div> 
    </div>
    
</div>
</div>
</form>
</body> --%>

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
	
<!-- 	  <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css"></link>
  <link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css" rel="stylesheet" type="text/css"></link>
  <link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/1.8/css/bootstrap-switch.css" rel="stylesheet" type="text/css"></link> -->
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>
<!--   <script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/1.8/js/bootstrap-switch.min.js" type="text/javascript"></script> -->
  
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
	<script>
	function validateForm() {
	    var so_number = document.forms["frm"]["so"].value;
	    var enduser = document.forms["frm"]["enduser"].value;
	    var key = document.forms["frm"]["ek"].value;
	    var sno = document.forms["frm"]["sno"].value;
	    var po = document.forms["frm"]["po"].value;
	    var hm = document.forms["frm"]["hm"].value;
	    if ((so_number == null || so_number == "") && (enduser == null || enduser == "") && (key == null || key == "") && (sno == null || sno == "") && (po ==null || po =="") && (hm ==null || hm ==""))  {
	    	document.getElementById("note").style.visibility='visible';
	    	document.getElementById("note").style.color="red";
	        return false;
	    }
	}

	function check()
	{
		var sno = document.forms["frm"]["sno"].value;
	    var message = document.getElementById('confirmMessage');
		var badColor = "#ff6666";
		var goodColor = "#008000";
		var pattern = /^\d{14}$/;
	    if(sno!=null && sno!=""){
	    	var pass = document.getElementById('sno');
	    	var numbers = /^[0-9]+$/;
	    	if(!sno.match(numbers)){
	    	      pass.style.backgroundColor = badColor;
	    	    message.innerHTML = "Please Input Numeric Value";
	    	}else if(!pattern.test(sno)) {
	    		pass.style.backgroundColor = badColor; 
	    		message.innerHTML = "Expecting 14 digits";
	    	}else{
	    		pass.style.backgroundColor = goodColor;
	    		message.innerHTML = "";
	    	}
	    }
	}  
	
	</script>
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
                    <li class="active"><a class="scroll" href="searchfield.jsp">Field Search</a></li>
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
 	<!-- Rev Slider -->
		<section id="slider" class="contain">
		
		 <div class="tp-banner">
     <div class="container">
    <div class="panel panel-primary dialog-panel">
      <div class="panel-heading">
        <h5>Field Search</h5>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" role="form" name="frm" method="get" onsubmit="return validateForm()" action="search">    
          <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="so">Sales Order</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" name="so" id ="so" placeholder="Enter Sales Order Number" type="text"></input>
                </div>
              </div>
            </div>
          </div>
            <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="enduser">End User</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" name="enduser" id="enduser" placeholder="Enter End User" type="text"></input>
                </div>
              </div>
            </div>
          </div>
          
            <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="ek">Entitlement Key</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" name="ek" id="ek" placeholder="Enter Entitlement Key" type="text"></input>
                </div>
              </div>
            </div>
          </div>
            <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="sno">Serial Number</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" name="sno" id="sno" placeholder="Enter Serial Number" type="text"></input>
                  <span id="confirmMessage" class="confirmMessage"></span>
                </div>
              </div>
            </div>
          </div>
            <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="hm">HiveManager ID</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control"  name="hm" id="hm" placeholder="Enter HiveManager ID" type="text"></input>
                </div>
              </div>
            </div>
          </div>
            <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="po">Purchase Order</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" name="po" id="po" placeholder="Enter Purchase Order" type="text"></input>
                </div>
              </div>
            </div>
          </div>
           <div class="form-group">
            <div class="col-md-offset-4 col-md-3">
              <button class="btn-lg btn-primary" type="submit">Search</button>
              <div id="note" style="visibility:hidden;">*Please search for one field at a time</div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
     </div>
     </section>
     </section> 
   </body>   
</html>
   