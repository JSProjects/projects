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
		<link rel="stylesheet" href="css/datepicker.css" />

  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript"></script>

	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.js"></script> 
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
<!--      <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" /> -->


	   <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                if (typeof $().datepicker === 'function') {
                    $('.datepicker').datepicker();
                }
            });
        </script> 
	</head>
	
	
<body data-spy="scroll" data-target=".nav-menu" data-offset="50">

<!-- Google Tag Manager -->
<!-- <noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-NT2B6R"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-NT2B6R');</script>
End Google Tag Manager

Marketo Munchkin
<script src="https://munchkin.marketo.net/munchkin.js" type="text/javascript"></script> <script>mktoMunchkin("828-ALH-889");</script>
End Marketo Munchkin
 -->
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
                    <li ><a class="scroll" href="searchfield.jsp">Field Search</a></li>
					<li class="active"><a class="scroll" href="searchdate.jsp">Date Search</a></li>
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
        <h5>Date Search</h5>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" role="form" method="get" name="frm" action="searchdates">
             <div class='form-group'>
            <label class='control-label col-md-2 col-md-offset-2' for='startdate'>Sales Order Date</label>
            <div class='col-md-8'>
              <div class='col-md-3'>
                <div class='form-group internal input-group'>
                  <input class='form-control datepicker' name='startdate' id='startdate'>
                  <span class='add-on input-group-addon'>
                <!--     <i class='glyphicon glyphicon-calendar'></i> -->
                  </span>
                </div>
              </div>
              <label class='control-label col-md-2' for='enddate'>To</label>
              <div class='col-md-3'>
                 <div class='form-group internal input-group'>
                  <input class='form-control datepicker' name='enddate' id='enddate' >
                  <span class='add-on input-group-addon'>
                  <!--   <i class='glyphicon glyphicon-calendar fa fa-calendar'></i> -->
                  </span>  
                </div>
              </div>
            </div>
          </div>
            <div class='form-group'>
            <label class='control-label col-md-2 col-md-offset-2' for='lstartdate'>Subscription End Date</label>
            <div class='col-md-8'>
              <div class='col-md-3'>
                <div class='form-group internal input-group'>
                  <input class='form-control datepicker' name='lstartdate' id='lstartdate'>
                  <span class='add-on input-group-addon'>
                 <!--    <i class='glyphicon glyphicon-calendar'></i> -->
                  </span>
                </div>
              </div>
              <label class='control-label col-md-2' for='lenddate'>To</label>
              <div class='col-md-3'>
                 <div class='form-group internal input-group'>
                  <input class='form-control datepicker' name='lenddate' id='lenddate' >
                  <span class='add-on input-group-addon'>
                    <!-- <i class='glyphicon glyphicon-calendar fa fa-calendar'></i> -->
                  </span>  
                </div>
              </div>
            </div>
          </div>
            <div class='form-group'>
            <label class='control-label col-md-2 col-md-offset-2' for='expdays'>License Expiring(days)</label>
            <div class='col-md-2'>
              <select class='form-control' name='expdays' id='expdays'>
              <option> </option>
               <option value="30">30</option>
               <option value="60">60</option>
               <option value="90">90</option>
               <option value="120">120</option>
              </select>
            </div>
          </div>
           <div class="form-group">
            <div class="col-md-offset-4 col-md-3">
              <button class="btn-lg btn-primary" type="submit">Search</button>
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
   