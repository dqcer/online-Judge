<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<h1>You have been ONE in PlusOJ</h1>
			<p>Welcome to PlusOnlineJudge !</p>
			<p>
				<a class="btn btn-primary btn-lg" role="button" href="${ctx }/accounts/tologin">Login to OJ NOW!</a>
			</p>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>