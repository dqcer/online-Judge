<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://cdn.bootcss.com/ace/1.2.3/ace.js" type="text/javascript"
	charset="utf-8"></script>
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
	<form action="${ctx }/problems/submitCode" method="post">
	<div class="container">
		<div class="row col-md-offset-1 col-md-10">
			<div class="question-title" style="overflow: hidden;">
				<h1 style="display: inline-block; margin-top: 0px;">
					#${chosenProblem.problemId}. ${chosenProblem.problemDigest}
				</h1>

				<h5>Tips : You can use <span class="orange">${chosenProblem.problemLanguage}</span> as your programming Languages.</h5>
			</div>
			<hr>
			
			<div class="row question-content">
				<div class="col-md-12">${chosenProblem.problemContent }</div>
			</div>
			<div class="row result-content table-responsive">
				<table class="table table-hover table-striped col-md-2">
					<caption>Optional OJ Tests</caption>
					<thead>
						<tr>
							<th>#</th>
							<th>Content</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${displayTests }" var="test">
							<tr class="warning">
								<td>Input</td>
								<td>${test.problemTestInput }</td>
							</tr>
							<tr class="info">
								<td>Output</td>
								<td>${test.problemTestOutput }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br />
		<div class="row col-md-offset-1 col-md-10" style="margin-bottom: 12px;">
			<div class="col-md-2">
				<select class="form-control" id="lang" onchange="changeLanguage();">
					<c:forEach items="${problemLanguages }" var="language">
						<c:if test="${language eq 'c' }">
							<option value="c">C</option>
						</c:if>
						<c:if test="${language eq 'cpp' }">
							<option value="cpp">C++</option>
						</c:if>
						<c:if test="${language eq 'java' }">
							<option value="java">Java</option>
						</c:if>
						<c:if test="${language eq 'python' }">
							<option value="py">Python</option>
						</c:if>
						<c:if test="${language eq 'ruby' }">
							<option value="rb">Ruby</option>
						</c:if>
						<c:if test="${language eq 'haskell' }">
							<option value="hs">Haskell</option>
						</c:if>
						
					</c:forEach>
					
				</select>
			</div>
			<div class="col-md-2">
				<select class="form-control" id="theme" onchange="changeTheme();">
					<option value="monokai" selected="selected">Monokai</option>
					<option value="xcode">Xcode</option>
					<option value="eclipse">Eclipse</option>
					<option value="solarized">Solarized</option>
				</select>
			</div>
			<div class="col-md-6">
				<button class="btn btn-default" type="button" onclick="resetCode();">
					<span class="glyphicon glyphicon-refresh"></span>
				</button>
				<button id="submit" class="btn btn-primary btn-pad" type="submit"
					data-original-title="Shortcut: Command + enter">Submit Solution</button>
				<button id="discuss" class="btn btn btn-success" type="button">
					Go to Discuss !</button>
			</div>
		</div>
		<pre id="embedded_ace_code" style="height: 400px;" class="col-md-offset-1 col-md-10" style="margin-bottom: 12px;">
			<div id="editor"></div>
		</pre>
		<div>
			<input id="problemId" name="problemId" value="${chosenProblem.problemId}" type="hidden"/>
			<textarea id="codeToSubmit" name="codeSubmit" style="display:none"></textarea>
			<input id="solutionLanguage" name="solutionLanguage" type="hidden"/>
		</div>
	</div>
	</form>
	
	<script>
		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/monokai");
		var selLang = $('#lang option:selected').val();
		editor.getSession().on("change", function(e){
			$("#codeToSubmit").val(editor.getValue());
		});
		var javaPre = "/**\r\n * please import your package here\r\n */\r\n\r\npublic class Test\r\n{\r\n \r\n    /**\r\n     * please keep the class name here to avoid compiling error\r\n     */\r\n    public static void main(String[] args) \r\n    {\r\n        \r\n    }\r\n}";
		var cPre = "/**\r\n * please edit your code here\r\n */\r\n\r\n #include <stdio.h>\r\n\r\n int main(int argc, char const *argv[])\r\n {\r\n 	/* code */\r\n 	return 0;\r\n }\r\n";
		var cppPre = "/**\r\n * please edit your code here\r\n */\r\n\r\n#include <iostream>\r\nusing namespace std;\r\nint main(int argc, char const *argv[])\r\n{\r\n	/* code */\r\n	return 0;\r\n}\r\n";
		var pyPre = "#please edit your code here\r\n";
		var rbPre = "#please edit your code here\r\n";
		var hsPre = "--please edit your code here\r\n";
		if(selLang == 'java'){
			editor.getSession().setMode("ace/mode/java");
			editor.setValue(javaPre);
		}else if(selLang == 'c'){
			editor.getSession().setMode("ace/mode/c_cpp");
			editor.setValue(cPre);
		}else if(selLang == 'cpp'){
			editor.getSession().setMode("ace/mode/c_cpp");
			editor.setValue(cppPre);
		}else if(selLang == 'rb'){
			editor.getSession().setMode("ace/mode/ruby");
			editor.setValue(rbPre);
		}else if(selLang == 'py'){
			editor.getSession().setMode("ace/mode/python");
			editor.setValue(pyPre);
		}else if(selLang == 'hs'){
			editor.getSession().setMode("ace/mode/haskell");
			editor.setValue(hsPre);
		}
		$("#solutionLanguage").val($('#lang option:selected').val());
		function resetCode() {
			editor.setValue("");
		}
		function changeLanguage() {
			if ($('#lang option:selected').val() == 'java'){
				editor.getSession().setMode("ace/mode/java");
				$("#solutionLanguage").val("java");
				editor.setValue(javaPre);
			}
			else if ($('#lang option:selected').val() == 'c'){
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage").val("c");
				editor.setValue(cPre);
			}
			else if ($('#lang option:selected').val() == 'cpp'){
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage").val("cpp");
				editor.setValue(cppPre);
			}
			else if ($('#lang option:selected').val() == 'rb'){
				editor.getSession().setMode("ace/mode/ruby");
				$("#solutionLanguage").val("rb");
				editor.setValue(rbPre);
			}
			else if ($('#lang option:selected').val() == 'py'){
				editor.getSession().setMode("ace/mode/python");
				$("#solutionLanguage").val("py");
				editor.setValue(pyPre);
			}
			else if ($('#lang option:selected').val() == 'hs'){
				editor.getSession().setMode("ace/mode/haskell");
				$("#solutionLanguage").val("hs");
				editor.setValue(hsPre);
			}
		}
		function changeTheme() {
			if ($('#theme option:selected').val() == 'monokai')
				editor.setTheme("ace/theme/monokai");
			else if ($('#theme option:selected').val() == 'xcode')
				editor.setTheme("ace/theme/xcode");
			else if ($('#theme option:selected').val() == 'eclipse')
				editor.setTheme("ace/theme/eclipse");
			else if ($('#theme option:selected').val() == 'solarized')
				editor.setTheme("ace/theme/solarized_dark");
		}
	</script>
	<%@	include file="footer.jsp"%>
</body>
</html>