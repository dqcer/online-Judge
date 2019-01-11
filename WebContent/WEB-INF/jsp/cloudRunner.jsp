<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Cloud Runner</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://cdn.bootcss.com/ace/1.2.3/ace.js"
	type="text/javascript" charset="utf-8"></script>
<style type="text/css" media="screen">
.editor {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}
</style>
<%@	include file="include.jsp"%>
<script type="text/javascript">
	$(function() {
		var editor1 = ace.edit("editor1");
		editor1.setTheme("ace/theme/monokai");
		editor1.getSession().setMode("ace/mode/java");
		var javaPre = "/**\r\n * please import your package here\r\n */\r\n\r\npublic class Test\r\n{\r\n \r\n    /**\r\n     * please keep the class name here to avoid compiling error\r\n     */\r\n    public static void main(String[] args) \r\n    {\r\n        \r\n    }\r\n}\r\n";
		var cPre = "/**\r\n * please edit your code here\r\n */\r\n\r\n #include <stdio.h>\r\n\r\n int main(int argc, char const *argv[])\r\n {\r\n 	/* code */\r\n 	return 0;\r\n }\r\n";
		var cppPre = "/**\r\n * please edit your code here\r\n */\r\n\r\n#include <iostream>\r\nusing namespace std;\r\nint main(int argc, char const *argv[])\r\n{\r\n	/* code */\r\n	return 0;\r\n}\r\n";
		var pyPre = "#please edit your code here\r\n";
		var rbPre = "#please edit your code here\r\n";
		var hsPre = "--please edit your code here\r\n";
		var phpPre = "<?php\r\n/*\r\nplease edit your code here\r\n*/\r\n\r\n?>";
		var goPre = "/**\r\n * please edit your code here\r\n */\r\n\r\npackage main\r\n \r\nimport \"fmt\"\r\n \r\nfunc main() {\r\n    /* code */\r\n}\r\n";
		var jsPre = "//please edit your code here\r\//npowered by node.js\r\n";
		var swiftPre = "//please edit your code here\r\n";
		editor1.setValue(javaPre);
		$('#addTab').click(function() {
			var nextTab = $('#tabs li').size();
			// create the tab
			$('<li><a href="#tab'+nextTab+'" data-toggle="tab">File'+ nextTab+ '<span class="glyphicon glyphicon-remove close-control"></span></a></li>').appendTo('#tabs');
			// create the tab content
			var tab_pane = "<br/><input type='hidden' value='"+nextTab+"'/><div id='settings_row' class='row col-md-12' style='margin-bottom: 12px;'>";
			tab_pane += $('#settings_row').html();
			tab_pane += "</div><input id='solutionLanguage"+nextTab+"' name='solutionLanguage"+nextTab+"' type='hidden' value='java'/>";
			tab_pane += "<div class='col-md-8'><div id='embedded_ace_code' style='height: 450px;' class='col-md-12' style='margin-bottom: 12px;'><div class='editor' id='editor"+nextTab+"'></div></div></div>";
			tab_pane += "<div class='col-md-4'><div class='input-group col-md-12'><span class='input-group-addon'><span class='glyphicon glyphicon-console'></span></span><input id='cloudRunnerSyncCode"+nextTab+"' type='hidden'/><input id='consoleId' type='hidden' value='"+nextTab+"' /><input id='std"+nextTab+"' type='text' readonly='readonly' class='form-control std-control' placeholder='stdin : hit key ENTER to input'></div>";
			tab_pane += "<br/><div id='console"+nextTab+"' class='panel panel-primary' style='height: 400px;'> <div class='panel-heading'><h3 id='consoleTitle"+nextTab+"' class='panel-title'>Output Console #"+nextTab+"</h3></div><div id='cloud-result"+nextTab+"' class='panel-body' data-spy='scroll'";
			tab_pane += "style='height: 350px; overflow: auto; position: relative;'></div></div></div></div>";

			$('<div class="row tab-pane fade in active" id="tab'+nextTab+'">'+ tab_pane + '</div>').appendTo('.tab-content');
			var editorx = ace.edit('editor' + nextTab);
			editorx.setTheme("ace/theme/monokai");
			editorx.getSession().setMode("ace/mode/java");
			editorx.setValue(javaPre);
			$('#tabs a:last').tab('show');
		});
		$(document).on('change', '.lang-control', function() {
			var tabId = $(this).parent().parent().prev().val();
			var editor = ace.edit("editor" + tabId);
			var selectedLang = $(this).val();
			if (selectedLang == 'java') {
				editor.getSession().setMode("ace/mode/java");
				$("#solutionLanguage" + tabId).val("java");
				editor.setValue(javaPre);
			} else if (selectedLang == 'c') {
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage" + tabId).val("c");
				editor.setValue(cPre);
			} else if (selectedLang == 'cpp') {
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage" + tabId).val("cpp");
				editor.setValue(cppPre);
			} else if (selectedLang == 'rb') {
				editor.getSession().setMode("ace/mode/ruby");
				$("#solutionLanguage" + tabId).val("rb");
				editor.setValue(rbPre);
			} else if (selectedLang == 'py') {
				editor.getSession().setMode("ace/mode/python");
				$("#solutionLanguage" + tabId).val("py");
				editor.setValue(pyPre);
			} else if (selectedLang == 'hs') {
				editor.getSession().setMode("ace/mode/haskell");
				$("#solutionLanguage" + tabId).val("hs");
				editor.setValue(hsPre);
			} else if (selectedLang == 'php') {
				editor.getSession().setMode("ace/mode/php");
				$("#solutionLanguage" + tabId).val("php");
				editor.setValue(phpPre);
			} else if (selectedLang == 'go') {
				editor.getSession().setMode("ace/mode/golang");
				$("#solutionLanguage" + tabId).val("go");
				editor.setValue(goPre);
			} else if (selectedLang == 'js') {
				editor.getSession().setMode("ace/mode/js");
				$("#solutionLanguage" + tabId).val("js");
				editor.setValue(jsPre);
			} else if (selectedLang == 'swift') {
				editor.getSession().setMode("ace/mode/swift");
				$("#solutionLanguage" + tabId).val("swift");
				editor.setValue(swiftPre);
			}
		});

		$(document).on('click', '.close-control', function() {
			$('#closeModal').data("tabToClose", $(this)).modal("show");
		});

		$(document).on('click', '#closeButton', function() {
			var tabToClose = $('#closeModal').data("tabToClose");
			tabToClose.parent().parent().remove();
			var closing_tab_pane = tabToClose.parent().attr('href');
			var tabId = $(closing_tab_pane).children().next().val();
			var editor = ace.edit("editor" + tabId);
			editor.destroy();
			$(closing_tab_pane).remove();
			$('#tabs a:last').tab('show');
			$('#closeModal').modal("hide");
		});
		$(document).on('click','.save-control',function() {
			$('#saveModal').data("tabIdToSave", $(this).parent().parent().prev().val()).modal("show");
		});
		$(document).on('click', '#saveButton', function() {
			$(this).val('saving');
			var tabIdToSave = $('#saveModal').data("tabIdToSave");
			var editor = ace.edit("editor" + tabIdToSave);
			$.ajax({
				type : 'POST',
				url : '${ctx}/save-snippet',
				data : {
					codeSnippit : editor.getValue(),
					snippetDescription : $('#snippetDiscription').val()
				},
				success : function(result) {
					
				}
			});
			$(this).val('saved');
			$('#saveModal').modal("hide");
		});
		$(document).on('click','.run-control',function() {
			$(this).html("<span class='glyphicon glyphicon-wrench'></span> running");
			var runButton = $(this);
			var tabId = $(this).parent().parent().prev().val();
			var editor = ace.edit("editor" + tabId);
			var cloudRunnerSyncCode;
			$('#std'+tabId).removeAttr("readonly");
			$('#cloud-result' + tabId).html("");
			$('#console'+tabId).attr("class","panel panel-info");
			$('#consoleTitle'+tabId).html("Output Console #"+tabId+" [:running]");
			$.ajax({
				type : 'POST',
				url : '${ctx}/problems/cloudRunSync',
				success : function(result) {
					$('#cloudRunnerSyncCode'+tabId).val(result);
					$.ajax({
						type : 'POST',
						url : '${ctx}/problems/cloudRun',
						data : {
							cloudRunnerSyncCode : result,
							codeSubmit : editor.getValue(),
							solutionLanguage : $(
									'#solutionLanguage' + tabId)
									.val()
						},
						success : function(result) {
							var runCode = result.message.code;
							var code = result.message.code;
							if (code == '201') {
								$('#console'+tabId).attr("class","panel panel-success");
								$('#cloud-result' + tabId).append(result.result);
							}else if(code == '500') {
								$('#console'+tabId).attr("class","panel panel-warning");
								$('#cloud-result' + tabId).html("<p><strong>"+result.message.message+"</strong></p><p>"+result.message.details+"</p>");
							}else if(code == '501') {
								$('#console'+tabId).attr("class","panel panel-danger");
								$('#cloud-result' + tabId).html("<p><strong>"+result.message.message+"</strong></p><p>"+result.message.details+"</p>");
							}
							$('#consoleTitle'+tabId).html("Output Console #"+tabId+" [:terminated]");
							runButton.html("<span class='glyphicon glyphicon-flash'></span> run");
							$('#std'+tabId).attr("readonly","readonly");
						}
					});
				}
			});
			
		});
		$(document).on('keyup', '.std-control', function(e) {
			if(e.keyCode == 13){
				var consoleId = $(this).prev().val();
				var typedInput = $(this).val();
				$(this).val('');
				$('#cloud-result'+consoleId).append('<strong>'+'>_&nbsp'+typedInput+'↵'+'</strong>'+'<br/>');
				$.ajax({
					type : 'POST',
					url : '${ctx}/problems/cloudRunEnterInput',
					data : {
						typedInput : typedInput,
						cloudRunnerSyncCode : $('#cloudRunnerSyncCode'+consoleId).val()
					},
					success : function(result) {
						
					}
				});
			}
		});
		$(document).on('change', '.theme-control', function() {
			var tabId = $(this).parent().parent().prev().val();
			var editor = ace.edit("editor" + tabId);
			var selectedTheme = $(this).val();
			if (selectedTheme == 'monokai')
				editor.setTheme("ace/theme/monokai");
			else if (selectedTheme == 'xcode')
				editor.setTheme("ace/theme/xcode");
			else if (selectedTheme == 'eclipse')
				editor.setTheme("ace/theme/eclipse");
			else if (selectedTheme == 'solarized')
				editor.setTheme("ace/theme/solarized_dark");
		});
		$(document).on('click', '.reset-control', function() {
			var tabId = $(this).parent().parent().prev().val();
			var editor = ace.edit("editor" + tabId);
			editor.setValue("");
		});
	});
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul id="tabs" class="nav nav-tabs">
					<li><a id="addTab"> <span class="glyphicon glyphicon-plus"></span></a></li>
					<li class="active"><a href="#tab1" data-toggle="tab">
							File1 </a></li>
				</ul>
				<div id="tabContents" class="tab-content">
					<div class="row tab-pane fade in active" id="tab1">
						<br /> 
						<input id="tabId" type="hidden" value="1" />
						<div id="settings_row" class="row col-md-12" style="margin-bottom: 12px;">
							<div class="col-lg-2">
								<select class="form-control lang-control" id="lang">
									<option value="c">C</option>
									<option value="cpp">C++</option>
									<option value="java" selected="selected">Java</option>
									<option value="py">Python</option>
									<option value="rb">Ruby</option>
									<option value="hs">Haskell</option>
									<option value="go">Go</option>
									<option value="php">Php</option>
									<option value="js">Node.js</option>
									<option value="swift">Swift</option> 
								</select>
							</div>
							<div class="col-lg-2">
								<select id="theme" class="form-control theme-control">
									<option value="monokai" selected="selected">Monokai</option>
									<option value="xcode">Xcode</option>
									<option value="eclipse">Eclipse</option>
									<option value="solarized">Solarized</option>
								</select>
							</div>
							<div class="col-lg-6">
								<button class="btn btn-default reset-control" type="button">
									<span class="glyphicon glyphicon-refresh"></span>
								</button>
								<button id="run" class="btn btn-success btn-pad run-control"
									data-original-title="Shortcut: Command + enter">
									<span class="glyphicon glyphicon-flash"></span>run
								</button>
								<button id="save" class="btn btn-primary btn-pad save-control">
									<span class="glyphicon glyphicon-save"></span>save
								</button>
							</div>
						</div>
						<input id="solutionLanguage1" name="solutionLanguage1"
							type="hidden" value="java" />
						<div class="col-md-8">
							<div id="embedded_ace_code" style="height: 450px;"
								class="col-md-12">
								<div id="editor1" class="editor"></div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="input-group col-md-12">
					        	<span class="input-group-addon"><span class="glyphicon glyphicon-console"></span></span>
					        	<input id="cloudRunnerSyncCode1" type="hidden"/>
					        	<input id="consoleId" type="hidden" value="1" />
					        	<input id="std1" type="text" readonly="readonly" class="form-control std-control" placeholder="stdin : hit key ENTER to input">
					    	</div>
					    	<br/>
							<div id="console1" class="panel panel-primary" style="height: 400px;">
								<div class="panel-heading">
									<h3 id="consoleTitle1" class="panel-title">Output Console #1</h3>
								</div>
								<div id="cloud-result1" class="panel-body" data-spy="scroll"
									style="height: 350px; overflow: auto; position: relative;">
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
	<div class="modal fade" id="closeModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong>Sure to Close ?</strong>
					</h3>
				</div>
				<div class="modal-body">
					<p>You will lose the file process in this editor!</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">cancel</button>
					<button id="closeButton" type="button" class="btn btn-danger">close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong>Save Code to PlusOJ Snippets</strong>
					</h3>
				</div>
				<div class="modal-body">
					<p>You will save your code to PlusOJ Snippets and you can find them easily then !</p>
					<div class="input-group">
				    	<span class="input-group-addon"><span class="glyphicon glyphicon-exclamation-sign"></span></span>
				    	<input id="snippetDiscription" type="text" class="form-control" placeholder="Code Desciption Here">
				   	</div>
				   	<br/>
				</div>
				<div class="modal-footer">
				<div class="row">
					<button type="button" class="col-md-2 btn btn-default" data-dismiss="modal">cancel</button>
					<select class=" col-md-2">
						<option value="public"><span class="glyphicon glyphicon-eye-open">public</span></option>
						<option value="private"><span class="glyphicon glyphicon-eye-close">private</span></option>
					</select>
					<button id="saveButton" type="button" class="col-md-2 btn btn-primary"><span class="glyphicon glyphicon-save"></span>save</button>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>