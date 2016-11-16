<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理系统</title>

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="../ico/favicon.ico" type="image/x-icon" />

<!-- Css files -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/jquery.mmenu.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../plugins/jquery-ui/css/jquery-ui-1.10.4.min.css"
	rel="stylesheet">
<link href="../css/style.min.css" rel="stylesheet">
<link href="../css/add-ons.min.css" rel="stylesheet">
<style>
footer {
	display: none;
}
</style>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="../js/admin/login.js"></script> -->
<script type="text/javascript">
	function login() {
		if ($("#loginname").val() == null || $("#loginname").val() == "") {
			alert('用户名不能为空');
			return;
		}
		if ($("#password").val() == null || $("#password").val() == "") {
			alert('密码不能为空');
			return;
		}
		var datas = {
			name : $("#loginname").val(),
			pwd : $("#password").val()
		};;;;;;
		$.ajax({
			data : datas,
			type : "POST",
			dataType : "text",
			url : "../admin/loginHandle",
			success : function(data) {
				if (data == "success") {
					location.href = "../admin/index";
				} else {
					alert("登录失败");
				}
			},
			error : function(result) {
				alert("登录失败");
			}
		});
	}

	//enter键触发登录功能
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) { // enter 键
			login();
		}
	};
</script>
</head>
<body>
	<div class="container-fluid content">
		<div class="row">
			<div id="content" class="col-sm-12 full">
				<div class="row">
					<div class="login-box">
						<div class="header">游戏名称</div>
						<div class="text-center">
							<!-- <li><div href="" class="fa fa-facebook facebook-bg"></div>国战</li>
							<li><div href="" class="fa fa-twitter twitter-bg"></div>三国</li>
							<li><div href="" class="fa fa-linkedin linkedin-bg"></div>志</li> -->
						</div>
						<div class="text-with-hr">
							<span>管理后台</span>
						</div>
						<fieldset class="col-sm-12">
							<div class="form-group">
								<div class="controls row">
									<div class="input-group col-sm-12">
										<input type="text" class="form-control" id="loginname"
											placeholder="请输入登录名" /> <span class="input-group-addon"><i
											class="fa fa-user"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="controls row">
									<div class="input-group col-sm-12">
										<input type="password" class="form-control" id="password"
											placeholder="请输入登录密码" /> <span class="input-group-addon"><i
											class="fa fa-key"></i></span>
									</div>
								</div>
							</div>
							<!-- <div class="confirm">
									<input type="checkbox" name="remember" /> <label
										for="remember">Remember me</label>
								</div> -->
							<div class="row">
								<button type="submit" id="loginBtn" onclick="login()"
									class="btn btn-lg btn-primary col-xs-12">登录</button>

							</div>
						</fieldset>
						<!-- <a class="pull-left" href="page-login.html#">Forgot Password?</a>
						<a class="pull-right" href="page-register.html">Sign Up!</a> -->
						<div class="clearfix"></div>
					</div>
				</div>
				<!--/row-->
			</div>
		</div>
		<!--/row-->
	</div>
	<!--/container-->
	<!-- start: JavaScript-->
	<!--[if !IE]>-->
	<script src="../js/jquery-2.1.1.min.js"></script>
	<!--<![endif]-->
	<!--[if IE]>
		<script src="../js/jquery-1.11.1.min.js"></script>
	<![endif]-->
	<!--[if !IE]>-->
	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='../js/jquery-2.1.1.min.js'>"
						+ "<"+"/script>");
	</script>
	<!--<![endif]-->
	<!--[if IE]>
		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='../js/jquery-1.11.1.min.js'>"+"<"+"/script>");
		</script>
	<![endif]-->
	<script src="../js/jquery-migrate-1.2.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<!-- page scripts -->
	<!-- theme scripts -->
	<script src="../js/SmoothScroll.js"></script>
	<script src="../js/jquery.mmenu.min.js"></script>
	<script src="../js/core.min.js"></script>
	<!-- inline scripts related to this page -->
	<!-- end: JavaScript-->
</body>
</html>
