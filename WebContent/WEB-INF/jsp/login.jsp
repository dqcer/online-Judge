<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		valid_form();
	});
	function valid_form() {
		var validator = $('#form-signin')
				.bootstrapValidator(
						{
							message : 'This value is not valid',
							fields : {
								username : {
									message : 'The username is not valid',
									validators : {
										notEmpty : {
											message : 'The username is required and can\'t be empty'
										}
									}
								},
								password : {
									validators : {
										notEmpty : {
											message : 'The password is required and can\'t be empty'
										}
									}
								}
							}
						});
	}

</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="container">
			<form class="form-signin" id="form-signin"
				action="${ctx }/accounts/login" method="post">
				<h3 class="form-signin-heading">Login</h3>
				<hr>
				<div class="row form-group">
					<input autofocus="autofocus" id="username" maxlength="30"
						name="username" placeholder="Username" type="text"
						class="form-control">
				</div>
				<div class="row form-group">
					<input id="password" name="password" placeholder="Password"
						type="password" class="form-control">
				</div>
				<div class="row form-group">
					<button class="col-md-7 btn btn-primary" type="submit"
						onclick="valid_submit();">Login PlusOJ</button>
					<a style="text-align:right;" class="col-md-offset-1 col-md-4" href="${ctx }/accounts/tosignup">sign up?</a>
	
				</div>
				
				<c:if test="${loginInfo != null}">
					<div class="row form-group alert alert-warning">${loginInfo }</div>
				</c:if>
			</form>
			
		</div>
	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>