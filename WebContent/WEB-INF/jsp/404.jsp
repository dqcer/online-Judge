<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>404!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="col-md-offset-2 col-md-8">
			<div class="jumbotron">
				<h2>You have been to a Unknown Space !</h2>
				<p>
					<a class="btn btn-primary btn-lg" role="button" href="${ctx }/index">Go PlusOJ NOW!</a>
				</p>
			</div>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>