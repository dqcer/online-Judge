<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://cdn.bootcss.com/ace/1.2.3/ace.js"
	type="text/javascript" charset="utf-8"></script>
<style type="text/css" media="screen">
#editor {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}
</style>
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<div class="question-title" style="overflow: hidden;">
					<h1 style="display: inline-block; margin-top: 0px;">
						#${chosenProblem.problemId}. ${chosenProblem.problemDigest}</h1>

					<h5>
						Tips : You used <span class="orange">${solution.solutionLanguage}</span>
						as your programming Language.
					</h5>
				</div>
				<hr>

				<div class="row question-content">
					<div class="col-md-12">${chosenProblem.problemContent }</div>
				</div>
				<%
					int count = 0;
				%>
				<div class="row result-content table-responsive">
					<table class="table table-hover table-striped  col-md-12">
						<caption>OJ Tests</caption>
						<thead>
							<tr>
								<th width="20%">#</th>
								<th width="80%">Content</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${compileResult == '500' }">
								<tr class="warning">
									<td>Result</td>
									<td>
										<p>${compileMessage.message}</p>
										<p>${compileMessage.details}</p>
									</td>
								</tr>
							</c:if>
							<c:if test="${compileResult != '500' }">
								<c:forEach items="${testResults }" var="result">
									<c:if test="${result.ojResult == 'AC'}">
										<tr class="success">
											<td>Judge</td>
											<td>${result.ojResult }</td>
										</tr>
									</c:if>
									<c:if test="${result.ojResult != 'AC'}">
										<tr class="danger">
											<td>Judge</td>
											<td>${result.ojResult }</td>
										</tr>
									</c:if>
									<tr>
										<td>Input</td>
										<td>${result.testInput }</td>
									</tr>
									<tr>
										<td>Expected Output</td>
										<td>${result.testOutput }</td>
									</tr>
									<tr>
										<td>Output</td>
										<td>${result.result }</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<hr/>
				<div class="row col-md-10">
					<span class="label label-primary col-md-1">
						${solution.solutionLanguage } </span>
					<c:if test="${solution.finalOJResult == 'AC'}">
						<span class="label label-success col-md-1"> ${solution.finalOJResult }
						</span>
					</c:if>
					<c:if test="${solution.finalOJResult != 'AC'}">
						<span class="label label-danger col-md-1"> ${solution.finalOJResult }
						</span>
					</c:if>
				</div>
				<br/>
				<pre id="embedded_ace_code" style="height: 400px;" class="col-md-12"
					style="margin-bottom: 12px;">
					<div id="editor"></div>
				</pre>
			</div>
		</div>
	</div>

	<script>
		var editor = ace.edit("editor");
		editor.setReadOnly(true);
		editor.setTheme("ace/theme/monokai");
		var solutionLang = "${solution.solutionLanguage}";
		if(solutionLang == 'java'){
			editor.getSession().setMode("ace/mode/java");
		}else if(solutionLang == 'c'){
			editor.getSession().setMode("ace/mode/c_cpp");
		}else if(solutionLang == 'cpp'){
			editor.getSession().setMode("ace/mode/c_cpp");
		}else if(solutionLang == 'rb'){
			editor.getSession().setMode("ace/mode/ruby");
		}else if(solutionLang == 'py'){
			editor.getSession().setMode("ace/mode/python");
		}else if(solutionLang == 'hs'){
			editor.getSession().setMode("ace/mode/haskell");
		}
		editor.setValue("${solution.codeSubmit}");
	</script>
	<%@	include file="footer.jsp"%>
</body>
</html>