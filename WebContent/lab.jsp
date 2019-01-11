<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>Welcome!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container">
		<c:set var="text" value="15652594325jinhao"></c:set>
		<c:set var="trans" value="${fn:replace(text,'jinhao','___')}"></c:set>
		<input value="${trans }">
	</div>
</body>
</html>