<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link href="<c:url value="/resources/css/structure.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/banner.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/reset.css"/>" rel="stylesheet" type="text/css" />
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;}
	body{
    width:100%;
    margin-left:0px;
    margin-top:0px;
    margin-right:0px;
    background-color: #E1E1E1;
    background-image: url(/resources/images/bkg1.gif); 
    overflow: hidden;
} 
	</style>
</head>
<body>
<!-- <body onload='document.f.j_username.focus();'> -->

 
 <form name='f' action="<c:url value='/j_spring_security_check' />" method='POST'>
 <div class="header">
</div>
<div class="box login">
	<fieldset class="boxBody">
	  <label>Email Address</label>
	 <input type="text" name='j_username' tabindex="1" placeholder="Enter Email(example:mjohn@aerohive.com)" required/>
	 <label>Password</label>
	 <input type="password" name='j_password' tabindex="2" placeholder="Enter Aerohive Network Password" required/>
	</fieldset>
	<footer>
	<c:if test="${not empty error}">
	 <label tabindex="3"><font color="red">Invalid Username/Password</font></label>
	 </c:if>
	 <input type="submit" class="btnLogin" value="Login" tabindex="4">  
	</footer>


<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
 

</div>
</form>

</body>
</html>