<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style type="text/css">
body{
    width:100%;
    margin-left:0px;
    margin-top:0px;
    margin-right:0px;
    background-color: white; 
    background-image: url(/NFR/resources/images/family1.jpg); 
    background-repeat: no-repeat;
  	background-position: center;
  	background-position-x: 50%;
  	background-position-y: 110%;
} 
header{
 	width:100%;
	background-color:#404040;
 	padding:50px;
    background-position: 100%;
    padding-right:0px;
   	background-image: url(/NFR/resources/images/bg-logo.png);
   	background-position: left center;
   	background-repeat: no-repeat;
}
nav {
	width: 150px; 
	padding:5px;height:700px;
    float:left;
	background-color: #606060;
	line-height:30px;
		      
}
section {
    width:100%;
    text-align: center; 	 
}
footer {
    background-color:black;
    color:white;
    text-align:left;
    height:100px;	
    width:100%;
    margin:0; 
}
.navbar	{
	height: 700px;
    padding: 0;
    padding-bottom: 10px;
	margin: 0;
	/* position: fixed;  Ensures that the menu doesn’t affect other elements */
	border-right: 1px solid #606060; 
    z-index: 12;
	}
.navbar li 	{
            padding-bottom: 10px;
			height: auto;
			width: 150px;  /* Each menu item is 150px wide */
			/*float: left;   This lines up the menu items horizontally */
            object-position: top;
			text-align: center;  /* All text is placed in the center of the box */
			list-style: none;  /* Removes the default styling (bullets) for the list */
			font: normal bold 12px/1.2em Arial, Verdana, Helvetica;  
			padding: 0;
			margin: 0;
			background-color: #606060;
                        }
.navbar a	{							
		padding: 18px 0;  /* Adds a padding on the top and bottom so the text appears centered vertically */
		border-left: 1px solid #606060; /* Creates a border in a slightly lighter shade of blue than the background.  Combined with the right border, this creates a nice effect. */
		border-right: 1px solid #606060; /* Creates a border in a slightly darker shade of blue than the background.  Combined with the left border, this creates a nice effect. */
		text-decoration: none;  /* Removes the default hyperlink styling. */
		 color:white; /* Text color is black */
		display: block;
		}

.navbar li:hover, a:hover {
    background-color: black;
    	 color:white;
}

.navbar li ul 	{
		display: none; /* Hides the drop-down menu */									
		margin: 0; /* Aligns drop-down box underneath the menu item */
		padding: 0; /* Aligns drop-down box underneath the menu item */		
        margin-left:150px;
        float:left;
        margin-top: -45px;
        height: 0;
		}				

.navbar li:hover ul 	{
                        display: block; /* Displays the drop-down box when the menu item is hovered over */
                        z-index: 12;
                        padding-left: 1px;
                       
                        }

.navbar li ul li {
    background-color: #606060;
}

.navbar li ul li a 	{
		border-left: 1px solid #606060;
		border-right: 1px solid #606060;
		border-top: 1px solid #606060;
        z-index: 1001;
		}
		
ul.semiopaquemenu{ 
width:100%;
background: #E1E1E1;
margin-left:0px;
margin-top:0px;
margin-right:0px;
margin-bottom:0px;
padding:10px;
list-style:none;
text-align: right;
color:black;
}

 menu {
display: block;
list-style-type: disc;
-webkit-margin-before: 1em;
-webkit-margin-after: 1em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
-webkit-padding-start: 40px;
border: none;
}

h4 {
font-family: century gothic;
font-size: 16px;
color: black;
font-weight: normal;
}
h4 {
display: block;
-webkit-margin-before: 1.33em;
-webkit-margin-after: 1.33em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
font-weight: bold;
}

#banner{
    background:#606060;
    width:200px;
    height:700px;
    float:right; 
   	margin: 0;
   	padding: 0;
}
h3 {
margin-left:5px;
font-size: 1.30em;
}

#banner h3 {
margin: 0 0 9px -0px;
color: white;
font: normal bold 14px/1.2em Arial, Verdana, Helvetica; 
text-align:center; 
}

h5 {
margin-left:5px;
margin: 0 0 9px -0px;
color: #C94C00;
font: 18px/21px "CenturyGothic", Arial, Helvetica, sans-serif;
text-align:center;
padding:0;
}
</style>
</head>

<body>

<header>
</header>
<ul class="semiopaquemenu">
<%--  <li>${user}</li>  --%>
</ul> 
<nav>
 <ul class="navbar">
			 <li><a href="/NFR/index/?uname=${sessionScope.uname}">Home</a></li>
			 <li><a href="/NFR/key">Request Key</a>
				<!--<ul>
                    <li><a href="/ESS/HMOL">HMOL Key</a></li>
                     <li><a href="#">Evaluation Key</a></li> 
				</ul> -->        
			 </li>
			<!--  <li><a href="/rent/services.php">Inventory</a>
				<ul>
                    <li><a href="/rent/memb.php">Membership</a></li>
				   <li><a href="/rent/rboat.php" >Rentals</a></li>
                    <li><a href="/rent/rb.php">Banquet</a></li>
                    <li><a href="/rent/faq.php">FAQ</a></li>
	   
				</ul>         
			 </li>
			 <li><a href="/ages/ages.php">Ages</a>
				<ul>
				   <li><a href="/ages/adult.php">Adults</a></li>
                    <li><a href="/ages/children.php">Children</a></li>
                   
				</ul>         
			 </li> -->
			 <li><a href="/NFR/history">Manage Keys</a>
			 <li><a href="/report/logout.jsp">Log Off</a></li>
                <!--  <li><a href="contact_us.php">Contact Us</a> -->
</ul>
    
</nav>
<div id="banner">
</div>

<section>
<h2>Welcome to NFR Self Service Portal</h2>
<!-- <h5>(Phase 1: Entitlements only)</h5> -->
<h4>This Not-for-Resale (NFR) self-service portal allows partners to request and manage Entitlement Keys for their valid NFR kits. </h4>
<h4>Partners will have access to create and manage entitlement keys for your NFR kits.</h4>

<h4>For any questions, comments, or issues, contact us at <a href="mailto:entitlements@aerohive.com?Subject=NFR%20Self%20Service%20Portal">
entitlements@aerohive.com</a> </h4>
<!-- <h5>We plan to add additional functionality in the upcoming months so please visit our Jive page for updates to the tool.</h5> -->
<br/>
<br/>
<br/>

<!-- <div style="margin-left:170px;text-align: left">
<h3><u>FAQ</u></h3>
<p>
<img alt="" src="/NFR/resources/images/questionmark.png" style="width: 15px; height: 15px;">&nbsp;
<a href="/ESS/ng" style="text-decoration:none;">How do I request a HiveManager NG Account?<br>
</a>
</p>
</div> -->
</section>


</body>
</html>
