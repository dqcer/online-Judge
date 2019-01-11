<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
<script type="text/javascript">
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div id="brief_stats" class="col-md-12">
						<h2>
							Welcome, <a href="${ctx }/accounts/profile">${loginuser.username }</a> ! 
						</h2>
						<h3>
							You have solved <strong>${solvedNum} / ${problemsNum} </strong> problems.
						</h3>						
					</div>
				</div>
				<hr/>
				<div id="problemListRow" class="row col-md-9">
					<table id="problemList" class="table table-hover table-striped table-centered">
						<thead>
							<tr>
								<th class="header-id">
									<div>#</div>
								</th>
								<th class="header-status">
									<div>Status</div>
								</th>
								<th class="header-digest">
									<div>Digest</div>
								</th>
								<th class="header-language">
									<div>Language</div>
								</th>
								<th class="header-ac">
									<div>Acceptance[AC|WA|CE]</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${problemsList }" var="problem">
								<tr>
									<td>${problem.problemId }</td>
									<td>${problem.someUserResult }</td>
									<td><a
										href="${ctx }/problems/${problem.problemId }">
										${problem.problemDigest }
										</a></td>
									<c:set var="orilan" value="${problem.problemLanguage }"></c:set>
									<c:set var="translan" value="${fn:replace(orilan,'&',' ')}"></c:set>
									<td>${translan}</td>
									<td> 
										<c:if test="${problem.problemSolveTimes == '0' }">
											<div class="progress progress-striped">
												<div class="progress-bar progress-bar-success" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
											      	style="0%;">
											  	</div>
											</div>
										</c:if>
										<c:if test="${problem.problemSolveTimes != '0' }">
											<div class="progress progress-striped">
												<div class="progress-bar progress-bar-success" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
											      	style="width: ${problem.problemAcTimes/problem.problemSolveTimes*100}%;">
											  	</div>
											   	<div class="progress-bar progress-bar-danger" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 	
											      	style="width: ${problem.problemWaTimes/problem.problemSolveTimes*100}%;">
											   	</div>
											   	<div class="progress-bar progress-bar-warning" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 	
											      	style="width: ${problem.problemCeTimes/problem.problemSolveTimes*100}%;">
											   	</div>
											</div>
									   	</c:if>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-3 blog-sidebar">
				<div id="row brief_stats" class="col-md-12">
					<div class="panel panel-success">
					   <div class="panel-heading">Top Coders 15</div>
					   <table class="table">
					   <thead>
							<tr>
								<th class="header-id">
									<div>#</div>
								</th>
								<th class="header-name">
									<div>CoderName</div>
								</th>
								<th class="header-ac">
									<div>ACs</div>
								</th>
							</tr>
						</thead>
						<%int id = 1; %>
						<c:forEach items="${dataAnalyseBeans }" var="analyseBean">
						    <tr>
						    	<td><%=id++ %></td>
						    	<td class="orange">${analyseBean.userName}</td>
						    	<td>${analyseBean.acNumber}</td>
						    </tr>
					    </c:forEach>
					   </table>
					</div>
				</div>
			</div>
			</div>
			
		</div>

	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>