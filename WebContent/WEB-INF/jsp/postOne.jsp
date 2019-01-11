<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//cdn.tinymce.com/4/tinymce.min.js"
	type="text/javascript" charset="utf-8"></script>
<%@	include file="include.jsp"%>
<style type="text/css">
.problemIO {
    resize: none;
}
</style>
<script type="text/javascript">
	tinymce.init({
		selector : '#problemContent',
		menubar : false,
		statusbar : false,
		init_instance_callback : function(editor) {
		    editor.setContent('${problem.problemContent}');
		}
	});
	function ioOpen2Close(){
		$('#ioVisable').attr('class','btn btn-info');
		$('#ioVisable').attr('ioVis',"0");
		$('#ioVisable').html('<span class="glyphicon glyphicon-eye-close"></span>');
	}
	function ioClose2Open(){
		$('#ioVisable').attr('class','btn btn-info active');
		$('#ioVisable').attr('ioVis',"1");
		$('#ioVisable').html('<span class="glyphicon glyphicon-eye-open"></span>');
	}
	function validate(problemLanguage,problemDigest,problemContent){
		if(problemLanguage == ''){
			$('#warningContent').html('<strong>Please choose Problem Language !</strong>');
			$('#warningModal').modal("show");
			return;
		}
		if(problemDigest == ''){
			$('#warningContent').html('<strong>Please give the Problem Digest out !</strong>');
			$('#warningModal').modal("show");
			return;
		}
		if(problemContent == ''){
			$('#warningContent').html('<strong>Please add the Problem Content !</strong>');
			$('#warningModal').modal("show");
			return;
		}
	}
	function toSave(problemLanguage,problemDigest,problemContent){
		var problemTests = new Array();
		for(k=1;k<=5;k++){
			var problemId = $('#problemId').val();
			var problemTestId = $('#testNum'+k).val();
			var problemTestInput = $('#problemI'+k).val();
			var problemTestOutput = $('#problemO'+k).val();
			var problemTestVisable = $('#testVis'+k).val();
			problemTests.push({problemId:problemId,problemTestId:problemTestId,problemTestInput:problemTestInput,problemTestOutput:problemTestOutput,problemTestVisable:problemTestVisable});
		}
		problemTests = JSON.stringify(problemTests);
		$.ajax({
			type : 'POST',
			url : '${ctx}/problems/tempSaveProblemContent',
			data : {
				problemId : $('#problemId').val(),
				problemDigest : problemDigest,
				problemContent : problemContent,
				problemLanguage : problemLanguage
			},
			success : function(result) {
				$('#problemId').val(result);
				$.ajax({
					type : 'POST',
					url : '${ctx}/problems/tempSaveProblemTest?newProblem='+result,
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					data : problemTests,
					success : function(result) {
						var testSum = result.length;
						for(k=1;k<=5;k++){
							$('#testNum'+k).val(result[k-1]);
						}
						$('#warningContent').html('<strong>Saved to <span class="orange">Plusoj</span> !</strong>');
					}
				});
			}
		});
	}
	function toPost(problemLanguage,problemDigest,problemContent){
		var problemTests = new Array();
		for(k=1;k<=5;k++){
			var problemId = $('#problemId').val();
			var problemTestId = $('#testNum'+k).val();
			var problemTestInput = $('#problemI'+k).val();
			var problemTestOutput = $('#problemO'+k).val();
			var problemTestVisable = $('#testVis'+k).val();
			problemTests.push({problemId:problemId,problemTestId:problemTestId,problemTestInput:problemTestInput,problemTestOutput:problemTestOutput,problemTestVisable:problemTestVisable});
		}
		problemTests = JSON.stringify(problemTests);
		$.ajax({
			type : 'POST',
			url : '${ctx}/problems/saveProblemContent',
			data : {
				problemId : $('#problemId').val(),
				problemDigest : problemDigest,
				problemContent : problemContent,
				problemLanguage : problemLanguage
			},
			success : function(result) {
				$('#problemId').val(result);
				$.ajax({
					type : 'POST',
					url : '${ctx}/problems/tempSaveProblemTest?newProblem='+result,
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					data : problemTests,
					success : function(result) {
						var testSum = result.length;
						for(k=1;k<=5;k++){
							$('#testNum'+k).val(result[k-1]);
						}
						window.location.href="${ctx}/myPosts";
					}
				});
			}
		});
	}
	$(function() {
		var problemLanguage = '${problem.problemLanguage}';
		var languages = problemLanguage.split('&');
		for (var i=0;i<languages.length;i++)
		{
			if(languages[i] != "")
				$("input[value='"+languages[i]+"']").parent().attr('class', 'label label-success');
		}
		$('.label').click(function() {
			if ($(this).attr('class') == 'label label-success')
				$(this).attr('class', 'label label-default');
			else if ($(this).attr('class') == 'label label-default')
				$(this).attr('class', 'label label-success');
		});
		$('#saveProblem').click(function() {
			var problemLanguage = '';
			var problemDigest = $('#problemDigest').val();
			var problemContent = tinymce.get('problemContent').getContent({format:'raw'});
			$('.label-success').each(function(i){
				problemLanguage += '&'+$(this).children().val();
			});
			validate(problemLanguage,problemDigest,problemContent);
			$('#warningContent').html('<strong>Saving to <span class="orange">Plusoj</span> !</strong>');
			$('#warningModal').modal("show");
			toSave(problemLanguage,problemDigest,problemContent);
		});
		$('#postProblem').click(function() {
			var problemLanguage = '';
			var problemDigest = $('#problemDigest').val();
			var problemContent = tinymce.get('problemContent').getContent({format:'raw'});
			$('.label-success').each(function(i){
				problemLanguage += '&'+$(this).children().val();
			});
			validate(problemLanguage,problemDigest,problemContent);
			validate();
			for(k=1;k<=5;k++){
				if($('#problemI'+k).val()==''||$('#problemO'+k).val()==''){
					$('#warningContent').html('<strong>You should complete the input and output in #'+k+'!</strong>');
					return;
				}
			}
			toPost(problemLanguage,problemDigest,problemContent);
		});
		$('#ioSaveButton').click(function(){
			var currentIONum = $('#currentIO').val();
			var problemInput = $('#problemInput').val();
			var problemOutput = $('#problemOutput').val();
			$('#problemI'+currentIONum).val(problemInput);
			$('#problemO'+currentIONum).val(problemOutput);
			$('#testVis'+currentIONum).val($('#ioVisable').attr('ioVis'));
			$('#problemIOModal').modal("hide");
		});
		
		$('#ioVisable').click(function(){
			if($('#ioVisable').attr('ioVis')=='1'){
				ioOpen2Close();
			}
			else{
				ioClose2Open();
			}
		});
		
		$('.io-control').click(function(){
			var testNum = $(this).attr("iono");
			$('#currentIO').val(testNum);
			$('#problemInput').val($('#problemI'+testNum).val());
			$('#problemOutput').val($('#problemO'+testNum).val());
			if($('#testVis'+testNum).val()=='1'){
				ioClose2Open();
			}
			else{
				ioOpen2Close();
			}
			$('#problemIOModal').modal("show");
		});
	});
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class='row col-md-12'>
			<div class="post-title" style="overflow: hidden;">
				<h3 style="display: inline-block; margin-top: 0px;">
					You can edit the problem here with the problem contents , inputs and
					outputs here and post them to <span class="orange">PlusOJ!</span>
				</h3>
				<h5>Tips : You can use the format tools to make your problem
					well typographical !</h5>
			</div>
		</div>
		<hr>
		<br>
		<div class="row">
			<div class="col-md-8">
				<div class="input-group row">
					<span class="input-group-addon">Digest</span> <input
						id="problemDigest" type="text" class="form-control"
						placeholder="Describe the problem" value="${problem.problemDigest}">
				</div>
				<br>
				<div class="row">
					<textarea id="problemContent" style="height: 350px;">
					</textarea>
				</div>
			</div>
			<div class="col-md-4">
				<button id="saveProblem" class="btn btn-default btn-pad">
					<span class="glyphicon glyphicon-save"></span>&nbsp;Save
				</button>
				<button id="postProblem" class="btn btn-success btn-pad">
					<span class="glyphicon glyphicon-send"></span>&nbsp;Post
				</button>
			</div>
			<div class="col-md-4">
				<hr>
			</div>
			<div class="col-md-4">
				<span class="label label-default"><input type="hidden"
					value="c" />C</span> 
				<span class="label label-default"><input
					type="hidden" value="cpp" />C++</span> 
				<span class="label label-default"><input
					type="hidden" value="java" />Java</span> 
				<span class="label label-default"><input
					type="hidden" value="ruby" />Ruby</span> 
				<span class="label label-default"><input
					type="hidden" value="python" />Python</span> 
				<span class="label label-default"><input type="hidden"
					value="haskell" />Haskell</span> 
				<span class="label label-default"><input
					type="hidden" value="go" />Go</span> 
				<span class="label label-default"><input
					type="hidden" value="swift" />Swift</span>
			</div>
			<div class="col-md-4">
				<hr>
			</div>
			<div class="col-md-4">
				<div id="ioPanel1" class="panel panel-primary io-control" iono="1">
					<div id="ioPanel1" class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span> &nbsp;Input and
							Output #1
						</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div id="ioPanel2" class="panel panel-primary io-control" iono="2">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span> &nbsp;Input and
							Output #2
						</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div id="ioPanel3" class="panel panel-primary io-control" iono="3">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span> &nbsp;Input and
							Output #3
						</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div id="ioPanel4" class="panel panel-primary io-control" iono="4">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span> &nbsp;Input and
							Output #4
						</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div id="ioPanel5" class="panel panel-primary io-control" iono="5">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-th-list"></span> &nbsp;Input and
							Output #5
						</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<hr>
			</div>

		</div>
	</div>
	<%@	include file="footer.jsp"%>
	<div class="modal fade" id="problemIOModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong>You should give this group of input and output</strong>
					</h3>
				</div>
				<div class="modal-body">
					<input type="hidden" id="currentIO"/>
					<p>Problem Input Here !</p>
					<textarea class="problemIO form-control" id="problemInput"
						style="height: 150px;">Write down the problem input here !</textarea>
					<hr>
					<p>Problem Output fit input above Here!</p>
					<textarea class="problemIO form-control" id="problemOutput"
						style="height: 150px;">Write down the problem output here ! </textarea>
				</div>
				<div class="modal-footer">
					<button id="ioVisable" type="button"></button>
					<button type="button" class="btn btn-default" data-dismiss="modal">cancel</button>
					<button id="ioSaveButton" type="button" class="btn btn-success">save</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="warningModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong>Operating Info</strong>
					</h3>
				</div>
				<div class="modal-body">
					<p id="warningContent"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
	<div class="hiddenPart" style="display: none">
		<input id="problemId" value="${problem.problemId }"/>
		<c:if test="${addNew != 1}">
			<c:forEach items="${problemTests }" var="test" varStatus="status">
				<input id="testNum${status.index+1}" value="${test.problemTestId }">
				<input id="testVis${status.index+1}" value="${test.problemTestVisable }">
				<textarea id="problemI${status.index+1}">${test.problemTestInput }</textarea>
				<textarea id="problemO${status.index+1}">${test.problemTestOutput }</textarea>
			</c:forEach>
		</c:if>
		<c:if test="${addNew == 1}">
			<c:forEach begin="1" end="5" varStatus="status">
				<input id="testNum${status.index}">
				<input id="testVis${status.index}">
				<textarea id="problemI${status.index}"></textarea>
				<textarea id="problemO${status.index}"></textarea>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>