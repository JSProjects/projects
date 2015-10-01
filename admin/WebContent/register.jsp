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
	function checkPass()
    {
    	
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('new');
        var pass2 = document.getElementById('confirm');
        //Store the Confimation Message Object ...
        var message = document.getElementById('confirmMessage');
        //Set the colors we will be using ...
        var goodColor = "#008000";
        var badColor = "#ff6666";
        //Compare the values in the password field
        //and the confirmation field
        if(pass1.value==pass2.value){
            //The passwords match.
            //Set the color to the good color and inform
            //the user that they have entered the correct password
            pass2.style.backgroundColor = goodColor;
            message.style.color = goodColor;
            message.innerHTML = "Passwords Match!"
        }else{
            //The passwords do not match.
            //Set the color to the bad color and
            //notify the user.
            pass2.style.backgroundColor = badColor;
            message.style.color = badColor;
            message.innerHTML = "Passwords Do Not Match!"
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
			<span class="name">Welcome back,${sessionScope.uname}</span><a class="account" href="logout.jsp">Log Out</a>
			</div>
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
        <h5>Create New User</h5>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" role="form" method="post" action="register">    
          <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="name">Email Address</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" placeholder="Enter Email Address" type="text" name="name"></input>
                </div>
              </div>
            </div>
          </div>
         
              <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="reseller">Reseller Name</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" placeholder="Enter Reseller Name" type="text" name="reseller"></input>
                </div>
              </div>
            </div>
          </div>
             <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="reportaccess">Report Access</label>
            <div class="col-md-1">
                  <input class="form-control"  type="checkbox" name="reportaccess" value="report"></input>
            </div>
          </div>
              <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="nfraccess">NFR Access</label>
            <div class="col-md-1">
              <input class="form-control"  type="checkbox" name="nfraccess" value="nfr"></input>
            </div>
          </div>
           <div class="form-group">
            <div class="col-md-offset-4 col-md-3">
              <button class="btn-lg btn-primary" type="submit">Create User</button>
            </div>
        	 <c:if test="${not empty message}">
	 				<label><font color="black">${message}</font></label> 
			</c:if>
          </div>
        </form>
        
        
      </div>
    </div>
  </div>
     </div>
     </section>
     
     <section id="slider" class="contain">
		
		 <div class="tp-banner">
      <div class="container">
    <div class="panel panel-primary dialog-panel">
      <div class="panel-heading">
        <h5>Reset Password</h5>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" role="form" method="get" action="register">    
          <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="name">Email Address</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" placeholder="Enter Email Address" type="text" name="name"></input>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-2 col-md-offset-2" for="reseller">Reseller Name</label>
            <div class="col-md-6">
              <div class="form-group">
                <div class="col-md-11">
                  <input class="form-control" placeholder="Enter Reseller Name" type="text" name="reseller"></input>
                </div>
              </div>
            </div>
          </div>
           <div class="form-group">
            <div class="col-md-offset-4 col-md-3">
              <button class="btn-lg btn-primary" type="submit">Reset Password</button>
            </div>
             <c:if test="${not empty message1}">
	 				<label><font color="black">${message1}</font></label> 
			</c:if>
          </div>
        </form>
        
        
      </div>
    </div>
  </div>
     </div>
     </section>
     
      <section id="slider" class="contain">
		
		 <div class="tp-banner">
      <div class="container">
    <div class="panel panel-primary dialog-panel">
      <div class="panel-heading">
        <h5>Users</h5>
      </div>
      <div class="panel-body">
       <display:table class="table blue" export="true" uid="users" name="sessionScope.users" sort="list" excludedParams="*"  pagesize="30">
          
                <display:column property="uname" title="Email Address"
                                sortable="true" sortName="uname" headerClass="sortable" />
                <display:column property="resellerName" title="Reseller Name"
                                sortable="true" sortName="resellerName" headerClass="sortable" />
                <display:column property="reportaccess" title="Report Access"
                                sortable="true" sortName="reportaccess" headerClass="sortable" />
                <display:column property="nfraccess" title="NFR Access"
                                sortable="true" sortName="nfraccess" headerClass="sortable" />
                <display:setProperty name="export.csv.filename" value="users.csv" />
                <display:setProperty name="export.excel.filename" value="users.xls" />
    			<display:setProperty name="basic.msg.empty_list" value="" />
     </display:table>
        
      </div>
    </div>
  </div>
     </div>
     </section>
     </section> 
   </body>   
</html>