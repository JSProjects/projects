<!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Login</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="https://www.aerohive.com/sites/all/themes/aerohivenetworks/favicon.ico" type="image/x-icon" />

<script src="uff/jquery.js"></script>
<link href="uff/animate.css" rel="stylesheet" type="text/css">
<script src="uff/jquery-ultimate-fancy-form.min.js"></script>
<link href="uff/jquery-ultimate-fancy-form.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/custom.css" rel="stylesheet" type="text/css">
<body>
<!--  <div style="margin-top:40px; margin-left:30%;margin-right:30%;">
<a class="scroll" href="https://www.aerohive.com"><img src="images/aeroport.JPG" alt="Aerohive Networks" align="middle"/></a>
</div> -->
<div class="container row">
<form class="form component-uff registration_form" method="post" action="login">
<div data-step>
<div class="header col-xs-12">
<h2 class="title" data-sb="fadeInUp">Login</h2>
<p class="description" style="text-align:center;"><font size="2">Welcome to the Aerohive Partner Order Reporting Tool.<br>If you have an account in this system, login below.<br><br>Otherwise, please contact your Aerohive Partner representative to request access</font></p>
</div>
<div class="content controls col-xs-12">
<div class="form-group col-xs-12 p0 pt12 m0">
<label data-sb="fadeInUp">Email address</label>
<input type="email" class="form-control" id="input-email" name="uname" placeholder="E-mail" data-sb="fadeInUp" data-validation-required="#error_user_information_email_required" data-validation-email="#error_user_information_email_invalid" />
<p id="error_user_information_email_required" class="alert-error">E-mail is required</p>
<p id="error_user_information_email_invalid" class="alert-error">This is not a valid email</p>
</div>
<div class="form-group col-xs-12 p0 pt12 m0">
<label data-sb="fadeInUp">Password</label>
<input type="password" class="form-control" id="input-password" name="pass" data-sb="fadeInUp" data-validation-required="#error_user_information_password_required" placeholder="Password">
<p id="error_user_information_password_required" class="alert-error">Password is required</p>
</div>
<!-- <div class="form-group col-xs-12 p0 tr" data-sb="fadeInUp">
<a href="#">Forgot password?</a>
</div> -->
<!-- <div class="checkbox" data-sb="fadeInUp">
<label class="fr p0"><input type="checkbox"> Keep me logged in</label>
</div> -->
</div>
<div class="footer col-xs-12 pt12">
<div class="group">
<!-- <a href="registration.html" class="btn btn-info col-xs-5 fl" data-sb="fadeInDown">Register</a> -->
<a class="btn btn-primary col-xs-5 fr" data-step-finish data-sb="fadeInDown"> Log in</a>
<c:if test="${errormessage!= null}">
<p><font color="red" align="center">Invalid Username/password</font></p>
</c:if>
</div>
</div>
<div class="clear"></div>
</div>
</form>
</div>
</body>
</html>