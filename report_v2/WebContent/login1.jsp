<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" type="text/css" href="css/top.css">
          <link rel="stylesheet" type="text/css" href="css/login.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form method="post" action="login">
         <table border="0" cellpadding="0" cellspacing="0" class="header">
		<tr>
 		<td colspan="2">
		</tr>
		</table>
            <center>
            <table class="login">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                    <tr>
                    <th colspan="2" style="margin-top:60px"> </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td size="30" align="center">Username :</td>
                        <td><input type="text" name="uname" value="" size="30" align="center"/></td>
                    </tr>
                    <tr>
                        <td size="30" align="center">Password :</td>
                        <td><input type="password" name="pass" value="" size="30" align="center"/></td>
                    </tr>
                    <tr>
                        <th colspan="2"><input type="submit" style="vertical-align:middle" value="Login" /></th>
                    </tr>
                    <tr>
                    </tr>
                 <!--    <tr>
                        <td colspan="2" size="20" align="center"><a href="reg.jsp" >Need an account?</a></td>
                    </tr> -->
                    <tr>
				 	<c:if test="${errormessage!= null}">
		 				<th colspan="2"><font color="red" align="center">${errormessage}</font></th>
					</c:if> 
				</tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>