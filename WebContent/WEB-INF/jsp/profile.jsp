<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>My Profile!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
<script type="text/javascript">
	$(function() {
		valid_form();
	});
	function show_change_password(){
		$('.tempHide').show();
		$('.chpwdButton').hide();
		$('.confirmChpwd').show();
	}
	function valid_form(){
		var validator = $('#form-profile').bootstrapValidator(
		{
			message : 'This value is not valid',
			fields : {
				new_password : {
					validators : {
						notEmpty : {
							message : 'The password is required and can\'t be empty'
						},
						stringLength : {
							min : 6,
							max : 30,
							message : 'The password must be more than 6 and less than 30 characters long'
						},
						identical : {
							field : 'confirm_password',
							message : 'The password and its confirm are not the same'
						},
						different : {
							field : 'username',
							message : 'The password can\'t be the same as username'
						}
					}
				},
				confirm_password : {
					validators : {
						identical : {
							field : 'new_password',
							message : 'The password and its confirm are not the same'
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
			<form class="form-signin" id="form-profile" action="${ctx }/accounts/changePassword" method="post">
				<h3 class="form-signin-heading"><span class="orange">PlusOJ </span>Profile</h3>
				<hr>
				<div class="form-group">
					<input autofocus="autofocus" id="username" maxlength="30" readonly="readonly"
						name="username" placeholder="Username" type="text" value="${loginuser.username }"
						class="form-control">
				</div>
				<div class="form-group">
					<input id="bitcode" maxlength="12"
						name="bitcode" placeholder="BIT Code" type="text" readonly="readonly" value="${loginuser.bitcode }"
						class="form-control">
				</div>
				<div class="form-group">
					<input id="email" name="email" placeholder="E-mail address" readonly="readonly" value="${loginuser.email }"
						type="email" class="form-control">
				</div>				<div class="tempHide form-group" <c:if test="${show_toggle != 'yes' }">style="display:none"</c:if>>
					<input id="old_password" name="old_password" placeholder="Old Password"
						type="password" class="form-control">
				</div>

				<div class="tempHide form-group" <c:if test="${show_toggle != 'yes' }">style="display:none"</c:if>>
					<input id="new_password" name="new_password" placeholder="New Password"  value="${new_password }"
						type="password" class="form-control">
				</div>
				<div class="tempHide form-group" <c:if test="${show_toggle != 'yes' }">style="display:none"</c:if>>
					<input id="confirm_password" name="confirm_password" placeholder="Confirm Password"  value="${new_password }"
						type="password" class="form-control">
				</div>
				
				<div class="form-group">
					<button class="chpwdButton btn btn-primary" type="button"
						onclick="show_change_password();">Change Password?</button>
					<button class="confirmChpwd btn btn-success" type="submit" style="display:none"
						onclick="change_password();">Confirmed to Change!</button>
				</div>
				<div class="tempHide form-group alert alert-warning">${chgpsdInfo }</div>
			</form>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>