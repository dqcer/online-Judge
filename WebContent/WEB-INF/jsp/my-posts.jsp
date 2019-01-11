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
	$(function(){
		$('.delete-control').click(function(){
			var problemId = $(this).parent().parent().children().html();
			$('#warningModal').data("problemId",problemId).modal("show");
		});
		$('#delete-ok').click(function(){
			var problemId = $('#warningModal').data("problemId");
			$.ajax({
				url : '${ctx}/problems/deleteProblem/'+problemId,
				success : function(result) {
					$('#warningModal').modal("hide");
					window.location.href="${ctx}/myPosts";
				}
			});
			
		});
		
	});
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="row">
					<div id="brief_stats" class="col-md-12">
						<h3>
							Welcome, <a href="${ctx }/accounts/profile">${loginuser.username }</a>&nbsp;!&nbsp;
							You have Posted <strong>${problemPostNum} </strong> problems.
						</h3>
					</div>
				</div>
				<hr/>
				<div>
					<a id="postOneButton" class="btn btn-primary" href="${ctx }/postProblem"><span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;Post One</a>
				</div>
				<hr/>
				<div id="problemPostListRow" class="row">
					<table id="problemList"
						class="table table-hover table-striped table-centered">
						<thead>
							<tr>
								<th class="header-id">
									<div>#</div>
								</th>
								<th class="header-digest">
									<div>Digest</div>
								</th>
								<th class="header-language">
									<div>Language</div>
								</th>
								<th class="header-status">
									<div>Online Status</div>
								</th>
								<th class="header-opt">
									<div>Operations</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${problemPostList }" var="problem">
								<tr>
									<td>${problem.problemId }</td>
									<td><a href="${ctx }/problems/${problem.problemId }">${problem.problemDigest }</a></td>
									<c:set var="orilan" value="${problem.problemLanguage }"></c:set>
									<c:set var="translan" value="${fn:replace(orilan,'&',' ')}"></c:set>
									<td>${translan}</td>
									<td>
										<c:if test="${problem.problemVisable == 1}"><button class="btn btn-sm btn-success" disabled="disabled">Passed</button></c:if>
										<c:if test="${problem.problemVisable != 1}"><button class="btn btn-sm btn-warning" disabled="disabled">Waited</button></c:if>
									</td>
									<td>
										<c:if test="${problem.problemVisable == 1}">
											<button class="btn btn-info" disabled="disabled">Edit</button>
											<button class="btn btn-danger delete-control">Delete</button>
										</c:if>
										<c:if test="${problem.problemVisable != 1}">
											<a class="btn btn-info" href="${ctx }/problems/editProblem/${problem.problemId }">Edit</a>
											<button class="btn btn-danger delete-control">Delete</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
	<div class="modal fade" id="warningModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong>Warning</strong>
					</h3>
				</div>
				<div class="modal-body">
					<h3>You're about to delete this Problem ?</h3>
					<p> if you delete this problem , no one will see it any more !</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button id="delete-ok" type="button" class="btn btn-danger" data-dismiss="modal">Delete</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>