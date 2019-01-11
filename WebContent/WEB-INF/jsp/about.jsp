<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-12">
				<div class="row">
					<div id="brief_stats" class="col-md-12">
						<h1>
							Here is some information about <span class="orange">PlusOnlineJudge</span> !
						</h1>
						<h2>
							Thanks for reading :
						</h2>
					</div>
					<hr />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="question-content">
						<p class="orange">What is PlusOnlineJudge ?</p>
					</div>
					<div class="answer-content">
						<p>PlusOnlineJudge is an online judgement system for coders !</p>
						<p>As a coder , you can use the languages settled by the problems to
							program , and after your solve it you submit your solution to
							PlusOJ to judge if it satisfies the test results . You can see
							all of your submissions to track your activities on this platform
							!</p>
						<p>In a word , PlusOnlineJudge is for coders to practice coding and
							to tarck their progress !</p>
						<br />
					</div>
				</div>

				<div class="col-md-12">
					<div class="question-content">
						<p class="orange">PlusOnlineJudge Development ?</p>
					</div>
					<div class="answer-content">
					<p>Developing PlusOJ is my graduation project !</p>
						<p>PlusOJ is a Java Web project , Java and Spring framework
							construct the main MVC structure , Mysql offers the database
							support and iBatis offers the ORM support , jQuery Bootstrap gives
							the front-end display framework !</p>
						<p>PlusOJ is an open-source project and it is hosted on GitHub
							, and here is the project link :</p>
						<p>
							<a href="https://github.com/JinhaoPlus/PlusOnlineJudge">github.com/JinhaoPlus/PlusOnlineJudge</a>
						</p>
						<p>Welcome to fork PlusOJ to make it better ! And I will
							develop it continuously to make it be more useful !</p>
						<br />
					</div>
				</div>
				<div class="col-md-12">
					<div class="question-content">
						<p class="orange">Some Introduction about the
							compilers and the environments .</p>
					</div>
					<div class="answer-content">
						<p>This part is to give an introduction of background
							environment of PlusOJ , you can learn about the language platforms
							for your coding and you may host a PlusOJ on your own website too !</p>
						<table class="table table-hover table-striped">
							<tbody>
								<tr>
									<th>Environment Platform</th>
									<th>Version</th>
									<th>Notes</th>
								</tr>
								<tr>
									<td>Server OS</td>
									<td>Ubuntu 14.04.2 LTS</td>
									<td></td>
								</tr>
								<tr>
									<td>Spring Framework</td>
									<td>Spring Framework 3.1.1</td>
									<td></td>
								</tr>
								<tr>
									<td>Mysql</td>
									<td>Ver 14.14</td>
									<td></td>
								</tr>
								<tr>
									<th>Language Platform</th>
									<th>Version</th>
									<th>Notes</th>
								</tr>
								<tr>
									<td>C</td>
									<td>gcc version 4.8.2</td>
									<td></td>
								</tr>
								<tr>
									<td>C++</td>
									<td>gcc version 4.8.2</td>
									<td></td>
								</tr>
								<tr>
									<td>Java</td>
									<td>java version 1.7.0</td>
									<td>You should use the settled classname "Test"</td>
								</tr>
								<tr>
									<td>Python</td>
									<td>python 2.7.6</td>
									<td></td>
								</tr>
								<tr>
									<td>Ruby</td>
									<td>ruby 1.9.3</td>
									<td></td>
								</tr>
								<tr>
									<td>Haskell</td>
									<td>ghc 7.10.3</td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<br />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>