<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>My Submissions</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
<script type="text/javascript">
	function solutionMoreInfo(problemId,solutionId){
		$.ajax({
			type : "POST",
			url : "${ctx}/problems/getSolutionDetail",
			success : function(msg) {
				
			},
			data : {
				problemId : problemId,
				solutionId : solutionId
			}
		});
	}
</script>
</head>
<body>
		<%@	include file="topnav.jsp"%>
		<div class="container">
			<div class="row">
				<div id="brief_stats" class="col-md-8">
					<h2>
						Hello, <a href="${ctx }/profile">${loginuser.username }</a>! You have
						submitted <span class="orange">${submitTimes }</span> times!
					</h2>
					<p>And these are all of your submissions :</p>
				</div>
			</div>
			<div class="row col-md-4">
				<select id="filterchosen" class="form-control"
					onchange="selecthandler(this.value);">
					<option value="default" selected="selected">Choose one
						filter</option>
					<option value="solved">Solved Problems</option>
					<option value="unsolved">Unsolved Problems</option>
					<option value="attempt">Attempted Problems</option>
				</select>
			</div>
			<div id="problemListRow" class="row">
				<table id="problemList" class="table table-striped table-centered">
					<thead>
						<tr>
							<th>
								<div>#</div>
							</th>
							<th>
								<div>Problem#</div>
							</th>
							<th>
								<div>Language</div>
							</th>
							<th>
								<div>Submit Time</div>
							</th>
							<th>
								<div>OJResult</div>
							</th>
							<th>
								<div>More</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<%
							int count = 0;
						%>
						<c:forEach items="${solutions }" var="solution">
							<c:if test="${solution.finalOJResult == 'AC'}">
								<tr class='success'>
							</c:if>
							<c:if test="${solution.finalOJResult != 'AC'}">
								<tr class='danger'>
							</c:if>
							<td><%=++count%></td>
							<td><a class="orange">${solution.problemId }</a></td>
							<td>${solution.solutionLanguage }</td>
							<td>${solution.codeSubmitTime }</td>
							<td>${solution.finalOJResult }</td>
							<td>
								<a class="btn btn-info" href="${ctx}/problems/getSolutionDetail/${solution.problemId }/${solution.solutionId }");">
									more info
								</a>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%@	include file="footer.jsp"%>
</body>
</html>