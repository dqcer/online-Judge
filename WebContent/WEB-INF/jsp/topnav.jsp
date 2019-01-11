<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<nav class="navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-brand">
					<div class="orange">PlusOnlineJudge!</div>
				</div>
			</div>
			<div class="navbar-collapse collapse" style="height: 1px;">
			<ul class="nav navbar-nav">
				<li id="problems"><a href="${ctx}/index"><span
						class="glyphicon glyphicon-th"> Problems</span></a></li>
				<li id="try"><a href="${ctx}/try"><span
						class="glyphicon glyphicon-edit"> Try!</span></a></li>
				<li id="discuss"><a href="${ctx }/cloud-runner"><span
						class="glyphicon glyphicon-cloud"> CloudRunner</span></a></li>
			</ul>
			<ul id="navBar-right" class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					style="margin-bottom: -5px" data-toggle="dropdown"><span
						class="glyphicon glyphicon-user"> ${sessionScope.loginuser.username}</span> <span
						class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/accounts/profile"><span class="glyphicon glyphicon-eye-open"> Profile</span></a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/mySubmissions"><span
								class="glyphicon glyphicon-list">&nbsp;Submissions</span></a></li>
						<li><a href="${ctx}/myPosts"><span
								class="glyphicon glyphicon-cloud-upload">&nbsp;Posts</span></a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-paperclip">&nbsp;Snippets</span></a></li>
						<li><a href="${ctx }/accounts/logout/"><span
								class="glyphicon glyphicon-log-out">&nbsp;Log out</span></a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>