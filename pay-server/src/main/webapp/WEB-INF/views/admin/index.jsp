<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理服务器后台</title>
<script>
	//整体返回
	function parentonlick() {
		location.href = "login";
	}
	function skip(path) {
		$('#right').load(path);
	}
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>游戏支付服模板——后台管理系统</title>

<!-- Import google fonts - Heading first/ text second -->
<link rel='stylesheet' type='text/css'
	href='http://fonts.useso.com/css?family=Open+Sans:400,700|Droid+Sans:400,700' />
<!--[if lt IE 9]>
<link href="http://fonts.useso.com/css?family=Open+Sans:400" rel="stylesheet" type="text/css" />
<link href="http://fonts.useso.com/css?family=Open+Sans:700" rel="stylesheet" type="text/css" />
<link href="http://fonts.useso.com/css?family=Droid+Sans:400" rel="stylesheet" type="text/css" />
<link href="http://fonts.useso.com/css?family=Droid+Sans:700" rel="stylesheet" type="text/css" />
<![endif]-->

<!-- Fav and touch icons -->
<link rel="shortcut icon" href="../ico/favicon.ico" type="image/x-icon" />

<!-- Css files -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/jquery.mmenu.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/climacons-font.css" rel="stylesheet">
<link href="../plugins/xcharts/css/xcharts.min.css" rel=" stylesheet">
<link href="../plugins/fullcalendar/css/fullcalendar.css"
	rel="stylesheet">
<link href="../plugins/morris/css/morris.css" rel="stylesheet">
<link href="../plugins/jquery-ui/css/jquery-ui-1.10.4.min.css"
	rel="stylesheet">
<link href="../plugins/jvectormap/css/jquery-jvectormap-1.2.2.css"
	rel="stylesheet">
<link href="../css/style.min.css" rel="stylesheet">
<link href="../css/add-ons.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
</head>
<body>
	<!-- start: Header -->
	<div class="navbar" role="navigation">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a href="<%=request.getContextPath()%>/admin/index.html#"
					id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
				<li class="visible-xs visible-sm"><a href="<%=request.getContextPath()%>/admin/index.html#"
					id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>
			</ul>
			<%--<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><span>服务器启动时间：${startTime }</span></li>
			</ul>--%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown visible-md visible-lg"><a
					class="dropdown-toggle" data-toggle="dropdown"><c:choose>
							<c:when test="${adminuser!=null }">
					${admin.name }
					</c:when>
							<c:otherwise>
					未登录
					</c:otherwise>
						</c:choose></a>
					<ul class="dropdown-menu">
						<li><a href="../admin/login"><i class="fa fa-sign-out"></i>
								注销</a></li>
					</ul></li>
				<li><a href="index.html"><i class="fa fa-power-off"></i></a></li>
			</ul>

		</div>

	</div>
	<!-- end: Header -->
	<div class="container-fluid content">
		<div class="row">
			<!-- start: Main Menu -->
			<div class="sidebar ">
				<div class="sidebar-collapse">
					<div class="sidebar-header t-center"></div>
					<div class="sidebar-menu">
						<ul class="nav nav-sidebar">
							<li><a onclick=""><i class="fa fa-laptop"></i><span
									class="text"> 支付管理系统</span></a></li>
							<li><a><i class="fa fa-sitemap"></i><span class="text">
										订单管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">
									<li><a onclick="javascript:skip('../paymgr/paylist')"><i
											class="fa fa-search"></i><span class="text"> 订单查询</span></a></li>
									<li><a onclick="javascript:skip('../paymgr/addPay')"><i
											class="fa fa-plus-square"></i><span class="text"> 添加订单</span></a></li>
								</ul></li>
						</ul>
					</div>
				</div>
				<div class="sidebar-footer">

					<div class="sidebar-brand">游戏支付服模板</div>

					<%--<ul class="sidebar-terms">
						<li><a
							href="http://127.0.0.1:81/36router/admin/index"
							target="_blank">登录服</a></li>
						<li><a
							href="http://127.0.0.1/file/file/hotfix"
							target="_blank">文件服</a></li>
					</ul>
					<ul class="sidebar-terms">
						<li><a
							href="http://127.0.0.1/motan"
							target="_blank">Motan</a></li>
						<li><a href="../druid/index.html" target="_blank">Druid</a></li>
					</ul>--%>
				</div>

			</div>
			<!-- end: Main Menu -->

			<!-- start: Content -->
			<div class="main" id="right">

				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa-laptop"></i> 后台管理系统
						</h3>
					</div>
				</div>
				<!-- end: Content -->
				<br> <br> <br>

			</div>
			<!--/container-->


			<div class="modal fade" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Warning Title</h4>
						</div>
						<div class="modal-body">
							<p>Here settings can be configured...</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

			<div class="clearfix"></div>


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
						|| document
								.write("<script src='../js/jquery-2.1.1.min.js'>"
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
			<script src="../plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
			<script src="../plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
			<script src="../plugins/moment/moment.min.js"></script>
			<script src="../plugins/fullcalendar/js/fullcalendar.min.js"></script>
			<!--[if lte IE 8]>
		<script language="javascript" type="text/javascript" src="../plugins/excanvas/excanvas.min.js"></script>
	<![endif]-->
			<script src="../plugins/flot/jquery.flot.min.js"></script>
			<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
			<script src="../plugins/flot/jquery.flot.stack.min.js"></script>
			<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
			<script src="../plugins/flot/jquery.flot.time.min.js"></script>
			<script src="../plugins/flot/jquery.flot.spline.min.js"></script>
			<script src="../plugins/xcharts/js/xcharts.min.js"></script>
			<script src="../plugins/autosize/jquery.autosize.min.js"></script>
			<script src="../plugins/placeholder/jquery.placeholder.min.js"></script>
			<script src="../plugins/datatables/js/jquery.dataTables.min.js"></script>
			<script src="../plugins/datatables/js/dataTables.bootstrap.min.js"></script>
			<script src="../plugins/raphael/raphael.min.js"></script>
			<script src="../plugins/morris/js/morris.min.js"></script>
			<script src="../plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js"></script>
			<script
				src="../plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"></script>
			<script src="../plugins/jvectormap/js/gdp-data.js"></script>
			<script src="../plugins/gauge/gauge.min.js"></script>


			<!-- theme scripts -->
			<script src="../js/SmoothScroll.js"></script>
			<script src="../js/jquery.mmenu.min.js"></script>
			<script src="../js/core.min.js"></script>
			<script src="../plugins/d3/d3.min.js"></script>

			<!-- inline scripts related to this page -->
			<script src="../js/pages/index.js"></script>

			<!-- end: JavaScript-->
</body>
</html>