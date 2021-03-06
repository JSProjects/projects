<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HMOL Key</title>
<style type="text/css">
body{
    width:100%;
    margin-left:0px;
    margin-top:0px;
    margin-right:0px;
    background-color: white; 
    background-image: url(/ESS/resources/images/bkg1.gif); 
    overflow: hidden;
} 
header{
 	width:100%;
	background-color:#404040;
 	padding:50px;
    background-position: 100%;
    padding-right:0px;
   	background-image: url(/ESS/resources/images/bg-logo.png);
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
    padding:10px;	
    text-align: center; 	 
}
footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;	 	 
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
h3{
font:18px Arial, Helvetica, sans-serif;
font-weight: bold;
text-align:center;
}
h4 {
font:15px Arial, Helvetica, sans-serif;
}
h4 {
display: block;
-webkit-margin-before: 1.33em;
-webkit-margin-after: 1.33em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
font-weight: bold;
}

h5 {
font:14px Arial, Helvetica, sans-serif;
}
h5 {
display: block;
-webkit-margin-before: 1.33em;
-webkit-margin-after: 1.33em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
font-weight: bold;
color:red;
}
table.imagetable caption{ 
background: #b5cfd2;
padding:10px;
color:black;
text-align:center;
font-weight: bold;
font-size:15px;
}

table.imagetable {
	font-family: verdana,arial,sans-serif;
	font-size:13px;
	color:#333333;
	margin: 0 auto;
/* 	border-collapse: collapse;
	border-spacing: 0;
	border-radius: 10px;
	white-space:nowrap;
    border-bottom:    1px solid  #F8F8F8;
    padding-top:30px;
     column-gap: 40px; */
	
}
table.imagetable th {
	background:#606060;
	border-collapse: separate;
	border-spacing: 0;
	padding: 10px 20px 10px 40px;
	border-top:    1px solid  #000000;
	border-bottom:    1px solid  #000000;
	text-align:center;
	color:white;
}
table.imagetable tr {
	background:#606060;
	padding: 10px 15px 10px 30px;
	border-collapse: separate;
	border-spacing: 0;
	white-space:nowrap;
	text-align:center;
}

table.imagetable tr:nth-child(odd){ 
	background: #FFFFFF;
}

table.imagetable tr:nth-child(even){
		background: #F8F8F8;
} 

table.dark{
    box-shadow: inset 0px 1px 1px rgba(255,255,255, .1), 0px 1px 3px rgba(0,0,0, .1);
    text-shadow: 0px 1px 0px rgba(0,0,0, .5);
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 30px;
    margin-left:350px;
}

table.dark tr{
    box-shadow: inset 0px 1px 1px rgba(255,255,255, .1), 0px 1px 3px rgba(0,0,0, .1);
    text-shadow: 0px 1px 0px rgba(0,0,0, .5);
}

table.dark td {
  padding-right: 10px;
}
table.imagetable tr.column{
font: bold 14px Georgia ;
z-index:15;
background: #0095cd;
padding: 10px; 
margin: 0;
border-top-right-radius:2em;
border-top-left-radius:2em;
}

.button_example{
 display:inline-block;
 margin-bottom:10px;
   border-top: 1px solid #606060;
   background: #606060;
   background: -webkit-gradient(linear, left top, left bottom, from(#606060), to(#606060));
   background: -webkit-linear-gradient(top, #3e779d, #606060);
   background: -moz-linear-gradient(top, #3e779d, #606060);
   background: -ms-linear-gradient(top, #3e779d, #606060);
   background: -o-linear-gradient(top, #3e779d, #606060);
   padding: 5px 5px;
   padding-bottom:5px;
   -webkit-border-radius: 2px;
   -moz-border-radius: 2px;
   border-radius: 2px;
   -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
   -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
   box-shadow: rgba(0,0,0,1) 0 1px 0;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: white;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button_example:hover {
   border-top-color: #606060;
   background: #606060;
   color: #ccc;
   }
.button_example:active {
   border-top-color: #606060;
   background: #606060;
   }
/*
Modern table design - template
Download more PSD stuff from www.psdgraphics.com
*/

#test {
    border: 5px gray;
    margin-top:20px;
   	margin-left:250px;
	padding: 10px;
	width: 678px;
	font: 14px Arial, Helvetica, sans-serif;
	color:#747474;
	background-color:#E1E1E1;
}

#test1 {
    border: 5px gray;
   	margin-left:250px;
	padding: 10px;
	width: 678px;
	font: 14px Arial, Helvetica, sans-serif;
	color:#747474;
	background-color:#E1E1E1;
	text-align:center;
}


#psdgraphics-com-table {
	margin-left:170px;
	padding: 4px;
	width: 678px;
	font: 7px Arial, Helvetica, sans-serif;
	color:#747474;
	background-color:#0c2a62;
}


#psdg-header {
	margin:0;
	padding: 14px 0 0 24px;
	width: 654px;
	height: 20px;
	color:#FFF;
	font-size:6px;
	background: #0c2c65 url(/ESS/resources/images/head-bcg.jpg) no-repeat right top;	
	text-align:center;
}

#psdg-header1 {
	margin-bottom:50px;
	padding: 10px 0px 0 24px;
	width: 654px;
	height: 40px;
	color:black;
	font-size:7px;
	background: #eff4ff no-repeat right top;	
}

.psdg-bold {
	font: bold 16px Arial, Helvetica, sans-serif;
	
}
.psdg-bold1 {
    text-align: center;
	font: bold 16px Arial, Helvetica, sans-serif;
	
}
#psdg-top {
	margin:0;
	padding: 0;
	width: 678px;
	height: 46px;
	border-top: 2px solid #FFF;
	background: #eff4ff url(/ESS/resources/images/top-light-blue.png) repeat-x left top;	
}

.psdg-top-cell {
	float:left;
	padding: 15px 0 0 0;
	text-align:center;
	width:105px;
	height: 31px;
	border-right: 1px solid #ced9ec;
	color:#1f3d71;
	font: 13px Arial, Helvetica, sans-serif;
}

#psdg-middle {
	margin:0;
	padding: 0;
	width: 578px;
	background: #f6f6f6 url(/ESS/resources/images/center-bcg.png) repeat-y right top;	
}

.psdg-left {
	float:left;
	margin:0;
	padding: 10px 0 0 24px;
	width: 129px;
	text-align: left;
	height: 25px;
	border-right: 1px solid #ced9ec;
	border-bottom: 1px solid #b3c1db;
	color:#1f3d71;
	font: 13px Arial, Helvetica, sans-serif;
	background: #e4ebf8 url(/ESS/resources/images/center-blue.png) repeat-y left top;
}



.psdg-right {
	float:left;
	margin:0;
	padding: 11px 0 0 0;
	width: 105px;
	text-align:center;
	height: 24px;
	border-right: 1px solid #ced9ec;
	border-bottom: 1px solid #b3c1db;
}

#psdg-bottom {
	clear:both;
	margin:0;
	padding: 0;
	width: 578px;
	height: 48px;
	border-top: 2px solid #FFF;
	background: #e4e3e3 url(/ESS/resources/images/bottom-line.png) repeat-x left top;	
}


.psdg-bottom-cell {
	float:left;
	padding: 15px 0 0 0;
	text-align:center;
	width:105px;
	height: 33px;
	border-right: 1px solid #ced9ec;
	color:#070707;
	font: 13px Arial, Helvetica, sans-serif;
}



#psdg-footer {
	font-size: 10px;
	color:#8a8a8a;
	margin:0;
	padding: 8px 0 8px 12px;
	width: 566px;
	background: #f6f6f6 url(/ESS/resources/images/center-bcg.png) repeat-y right top;	
}

h2 {
margin: 0 0 9px -0px;
color: black;
font: 16px/21px, Arial, Helvetica, sans-serif;
text-align:center;
padding:0;
}
p {
display: block;
text-align:left;
margin-left:250px;
margin-right:250px;
color: black;
font: 16px Arial, Helvetica, sans-serif;
}
hr { 
  height: 12px; border: 0; box-shadow: inset 0 12px 12px -12px rgba(0,0,0,0.5);
} 
#banner{
    background:#606060;
    width:150px;
    height:700px;
    float:right; 
   	margin: 0;
   	padding: 0;
}
#contactform {width:900px; height:500px;background: #E1E1E1;margin-left:250px;}
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
			 <li><a href="/ESS/index">Home</a></li>
			 <li><a href="#">Request Key</a>
				<ul>
                    <li><a href="/ESS/HMOL">HMOL Key</a></li>
                   <!--  <li><a href="#">Evaluation Key</a></li> -->
				</ul>         
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
			 <li><a href="/ESS/j_spring_security_logout">Log Off</a></li>
                <!--  <li><a href="contact_us.php">Contact Us</a> -->
</ul>
    
</nav>
<div id="banner">
</div>
<form name='f' action="/ESS/generate" method='GET'>
<section>

<!-- 
<div id="test"> -->
<h2>HMOL Key Request Page</h2>
 <p>As an Aerohive employee, you can request (one) personal-use entitlement key (EK). Once the key has been
requested you will receive and email within 24 hours that will include your EK. The key is good for up 
to 10 devices for 1 year. We will renew your license on an annual basis as long as you are actively employed at Aerohive.</p>
<span class="psdg-bold"> Generate HMOL Key:</span><input type="submit"  value="Generate" class="button_example">
<br/>
<br/>
<br/>
<!-- </div> -->
<c:if test="${not empty userlist}">
<!-- <div id="test1"> -->
<hr>
<br/>
<br/>
<br/>
<span class="psdg-bold1">User Request History</span>
<br/>
<br/>
<br/>
 <table class=imagetable>
<%-- <caption>User Request History</caption> --%>
        <tr>
            <th>UserName</th>
            <th>Submitted Date</th>
            <th>Entitlement Key</th>
        </tr>

        <c:forEach var="user" items="${userlist}">
            <tr>
                <td>${user.email}</td>
                <td>${user.submittedDate}</td>
  				<td>${user.entitlementKey}</td>
            </tr>
        </c:forEach>
 
    </table> 
    	<c:if test="${not empty error}">
    	<br/>
	 <p style="color:red;">Note: You may request only one key at this time. However, you will be able to request a 1-year renewal EK 30 days prior to the expiration of the previous key. If you need more than one EK please send an e-mail to 
	  <a href="mailto:entitlements@aerohive.com?Subject=Employee%20Self%20Service%20Portal">entitlements@aerohive.com</a> with your Manager&#8217;s approval detailing your special request.</p>
	 </c:if>
<!-- </div> -->
</c:if>
</section>
</form>

</body>
</html>