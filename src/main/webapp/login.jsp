<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom-checkboxes.css">

<style type="text/css">
body.login {
	background: #0D3B5F;
	margin: 0;
	padding: 0;
	height: 100%;
}
</style>

</head>
<body id="login" class="login">

	<div class="container">
		<div id="loginbox" style="margin-top: 120px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				
			<div class="panel panel-success">
			
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
					<div style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>

					<form id="loginform" class="form-horizontal" role="form" action="/do/login" method="post">

						<div style="margin-bottom: 20px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input id="login-username" type="text" class="form-control" name="j_username" value="" placeholder="username or email">
						</div>
						

						<div style="margin-bottom: 20px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input id="login-password" type="password" class="form-control" name="j_password" placeholder="password">
						</div>
						
						<div style="margin-bottom: 20px; color: red;" class="input-group">
					<%
 						
 						if (request.getParameter("login_error") != null) {
 							//out.write("Bad credentials or account disabled, try again.\n");
 							Exception error = (Exception) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
 							if(error == null) {
 								error = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
 							}
 							out.write(error.getMessage());
 						}
 					%>
					</div> 


						<div style="margin-bottom: 15px" class="input-group">
							<label class="checkbox"> 
								<input type="checkbox" class="custom-checkbox">
							 		<span class="icons"> 
							 				<span class="icon-unchecked"></span> <span class="icon-checked"></span>
									</span> 
									<span>Remember Me</span>
							</label>
						</div>


						<div style="margin-bottom: 15px" class="input-group">
							<button type="submit" class="btn btn-success">Login</button>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="#"
										onClick="$('#loginbox').hide(); $('#signupbox').show()">
										Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>



				</div>
			</div>
		</div>

	</div>
	<script>

function wipeCookies() {
	var cookies = document.cookie.split(";");
	var path = window.location.pathname.split('/');

	for (var i = 0; i < cookies.length; i++) {
	    var cookie = cookies[i];
	    var eqPos = cookie.indexOf("=");
	    var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
	    if( name.indexOf("USERLOGIN") != -1 ) continue;
	    document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT"+";path=/";
		if (path[1]) {
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT"+";path=/"+path[1];
		}
		if (path[2]) {
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT"+";path=/"+path[1]+"/"+path[2];
		}
		if (path[3]) {
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT"+";path=/"+path[1]+"/"+path[2]+"/"+path[3];
		}
	}
}


</script>


</body>
</html>