#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>游戏逻辑服模板——后台管理系统</title>

    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="../ico/favicon.ico" type="image/x-icon"/>

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
    <script>
        //整体返回
        function parentonlick() {
            location.href = "login";
        }
        function skip(path) {
            // ${symbol_dollar}('${symbol_pound}right').load("../admin/loading");
            ${symbol_dollar}('${symbol_pound}right').load(path);
        }
    </script>
    <script src="../js/echarts.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="../js/china.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/dark.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/vintage.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!-- start: Header -->
<div class="navbar" role="navigation">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-actions navbar-left">
            <li class="visible-md visible-lg"><a id="main-menu-toggle"><i
                    class="fa fa-th-large"></i></a></li>
            <li class="visible-xs visible-sm"><a id="sidebar-menu"><i
                    class="fa fa-navicon"></i></a></li>
        </ul>
        <%-- <ul class="nav navbar-nav navbar-actions navbar-left">
             <li class="visible-md visible-lg"><span>开服时间：${symbol_dollar}{startServerTime }</span></li>
         </ul>
         <ul class="nav navbar-nav navbar-actions navbar-left">
             <li class="visible-md visible-lg"><span>&nbsp;&nbsp;最近重启时间：${symbol_dollar}{startTime }</span></li>
         </ul>
         <ul class="nav navbar-nav navbar-actions navbar-left">
             <li class="visible-md visible-lg"><span>&nbsp;&nbsp;${symbol_dollar}{remain}</span></li>
         </ul>--%>
        <!-- <form class="navbar-form navbar-left">
				<button type="submit" class="fa fa-search"></button>
				<input type="text" class="form-control" placeholder="Search..."></a>
			</form> -->
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown visible-md visible-lg"><a
                    class="dropdown-toggle" data-toggle="dropdown"> <c:choose>
                <c:when test="${symbol_dollar}{adminuser!=null }">
                    ${symbol_dollar}{admin.name }
                </c:when>
                <c:otherwise>
                    未登录
                </c:otherwise>
            </c:choose>
            </a>

                <ul class="dropdown-menu">
                    <!-- <li class="dropdown-menu-header"><strong>Account</strong></li>
						<li><a href="page-profile.html"><i class="fa fa-user"></i>
								Profile</a></li>
						<li><a href="page-login.html"><i class="fa fa-wrench"></i>
								Settings</a></li>
						<li><a href="page-invoice.html"><i class="fa fa-usd"></i>
								Payments <span class="label label-default">10</span></a></li>
						<li><a href="gallery.html"><i class="fa fa-file"></i>
								File <span class="label label-primary">27</span></a></li>
						<li class="divider"></li> -->
                    <li><a href="../admin/login"><i class="fa fa-sign-out"></i>
                        注销</a></li>
                </ul>
            </li>
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
                <div class="sidebar-header t-center">
						<span> <!-- <img class="text-logo" src="../img/logo1.png"> -->
                            <!-- <i
							class="fa fa-space-shuttle fa-3x blue"></i> -->
						</span>
                </div>
                <div class="sidebar-menu">
                    <ul class="nav nav-sidebar">
                        <li><a><i class="fa fa-laptop"></i><span class="text"
                                                                 onclick="history.go(0)"> 后台管理系统</span></a></li>
                        <c:if test="${symbol_dollar}{admin!=null&&admin.name=='kidbear' }">
                            <%--<li><a><i class="fa fa-envelope"></i><span class="text">--%>
                            <%--邮件管理</span> <span class="fa fa-angle-down pull-right"></span></a>--%>
                            <%--<ul class="nav sub">--%>
                            <%--<li><a onclick="skip('../activity/award')"><i--%>
                            <%--class="fa fa-mail-forward"></i><span class="text">--%>
                            <%--发送邮件</span></a></li>--%>
                            <%--<li><a onclick="skip('../activity/query')"><i--%>
                            <%--class="fa fa-search"></i><span class="text"> 邮件查询</span></a></li>--%>
                            <%--</ul>--%>
                            <%--</li>--%>
                        </c:if>
                        <li><a><i class="fa fa-list-alt"></i><span class="text">
										数据管理</span> <span class="fa fa-angle-down pull-right"></span></a>
                            <ul class="nav sub">
                                <c:if test="${symbol_dollar}{admin!=null&&admin.name=='kidbear' }">
                                    <li><a onclick="skip('../data/query')"><i
                                            class="fa fa-male"></i><span class="text"> 玩家数据</span></a></li>
                                </c:if>
                            </ul>
                        </li>
                        <c:if test="${symbol_dollar}{admin!=null&&admin.name=='kidbear' }">
                            <li><a><i class="fa fa-list-alt"></i><span class="text">
											游戏数据</span> <span class="fa fa-angle-down pull-right"></span></a>
                                <ul class="nav sub">
                                    <li><a onclick="skip('../csv/csvlist')"><i
                                            class="fa fa-cloud-download"></i><span class="text">
													CSV数值</span></a></li>
                                    <li><a onclick="skip('../datas/class')"><i
                                            class="fa fa-eye"></i><span class="text"> 相关类查看</span></a></li>
                                    <li><a onclick="skip('../datas/thread')"><i
                                            class="fa fa-eye"></i><span class="text"> 线程查看</span></a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-briefcase"></i><span
                                    class="text"> 配置管理</span> <span
                                    class="fa fa-angle-down pull-right"></span></a>
                                <ul class="nav sub">
                                    <li><a onclick="skip('../log/logConfig')"><i
                                            class="fa fa-align-left"></i><span class="text"> 信息配置</span></a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="sidebar-footer">
                <div class="sidebar-brand">游戏名称</div>
                <c:if test="${symbol_dollar}{admin!=null&&admin.name=='kidbear' }">
                    <ul class="sidebar-terms">
                        <li><a
                                href="${symbol_pound}"
                                target="_blank">登录服</a></li>
                        <li><a href="${symbol_pound}"
                               target="_blank">支付服</a></li>
                        <li><a href="${symbol_pound}"
                               target="_blank">文件服</a></li>
                    </ul>
                    <ul class="sidebar-terms">
                        <li><a href="${symbol_pound}"
                               target="_blank">Motan</a></li>
                        <li><a href="../druid/index.html" target="_blank">Druid</a></li>
                    </ul>
                </c:if>
            </div>
        </div>

    </div>
    <!-- end: Main Menu -->

    <!-- start: Content -->
    <div class="main" id="right">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header" onclick="">
                    <i class="fa fa-laptop"></i> 后台管理系统
                </h3>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>标题栏</strong>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <center>
                                    <h1>游戏逻辑服后台管理系统框架</h1>
                                </center>
                            </div>
                        </div>
                    </div>
                    <!-- /col -->
                </div>
                <!--/row-->
                <%--<div class="row">
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>次日留存分布图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/remain2log')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="remain2" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>七日留存分布图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/remain7log')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="remain7" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
                <div class="row">
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>每日新增分布图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/createlog')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="create" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>每日活跃分布图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/activelog')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="active" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
                <div class="row">
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>付费统计图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/paycostlog')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="paycost" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                    <div class="col-sm-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>每日充值分布图</strong>&nbsp;&nbsp;<a
                                        onclick="skip('../datas/dailypaylog')"><span
                                        class="text">查看详情</span></a>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="dailypay" style="height: 400px"></div>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-retweet red"></i><strong>全国玩家分布图</strong>
                                </h2>
                                <div class="panel-actions">
                                    <a href="${symbol_pound}" class="btn-refresh" onclick="history.go(0)"><i
                                            class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-minimize"><i
                                        class="fa fa-chevron-up"></i></a> <a href="${symbol_pound}" class="btn-close"><i
                                        class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="ipmap" style="height: 600px"></div>
                            </div>
                        </div>
                    </div>
                    <!-- /col -->
                </div>
                <!--/row-->--%>
            </div>
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
                                data-dismiss="modal">Close
                        </button>
                        <button type="button" class="btn btn-primary">Save
                            changes
                        </button>
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
        <script src="../plugins/datatables/js/dataTables.bootstrap.min.js"></script>

        <!--[if IE]>

        <script src="../js/jquery-1.11.1.min.js"></script>

        <![endif]-->

        <!--[if !IE]>-->

        <script type="text/javascript">
            window.jQuery
            || document
                    .write("<script src='../js/jquery-2.1.1.min.js'>"
                            + "<" + "/script>");
        </script>

        <!--<![endif]-->

        <!--[if IE]>

        <script type="text/javascript">
            window.jQuery || document.write("<script src='../js/jquery-1.11.1.min.js'>" + "<" + "/script>");
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
        <script type="text/javascript">
            // 初始化图表
            /*var remain2Chart = echarts.init(document
             .getElementById('remain2'));
             var remain7Chart = echarts.init(document
             .getElementById('remain7'));
             var createChart = echarts.init(document
             .getElementById('create'));
             var activeChart = echarts.init(document
             .getElementById('active'));
             var paycostChart = echarts.init(document
             .getElementById('paycost'));
             var dailypayChart = echarts.init(document
             .getElementById('dailypay'));
             var ipmapChart = echarts.init(document.getElementById('ipmap'));
             // loading
             remain2Chart.showLoading();
             remain7Chart.showLoading();
             createChart.showLoading();
             activeChart.showLoading();
             paycostChart.showLoading();
             dailypayChart.showLoading();
             ipmapChart.showLoading();
             // 路径配置
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/remain2logHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             remain2Chart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['次日留存']
             },
             xAxis: [{
             name: '日期',
             type: 'category',
             data: ret.dateCharts
             }],
             yAxis: [{
             name: '次日留存',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 20,
             end: 80
             },
             series: [{
             "name": "次日留存",
             "type": "bar",
             "data": ret.remain2Charts
             }]
             };
             // 为echarts对象加载数据
             remain2Chart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/remain7logHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             remain7Chart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['七日留存']
             },
             xAxis: [{
             name: '日期',
             type: 'category',
             data: ret.dateCharts
             }],
             yAxis: [{
             name: '七日留存',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 20,
             end: 80
             },
             series: [{
             "name": "七日留存",
             "type": "bar",
             "data": ret.remain7Charts
             }]
             };
             // 为echarts对象加载数据
             remain7Chart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/createlogHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             createChart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['每日新增']
             },
             xAxis: [{
             name: '日期',
             type: 'category',
             data: ret.dateCharts
             }],
             yAxis: [{
             name: '人数',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 20,
             end: 80
             },
             series: [{
             "name": "每日新增",
             "type": "bar",
             "data": ret.createCharts
             }]
             };
             // 为echarts对象加载数据
             createChart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/activelogHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             activeChart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['每日活跃分布']
             },
             xAxis: [{
             name: '日期',
             type: 'category',
             data: ret.dateCharts
             }],
             yAxis: [{
             name: '人数',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 20,
             end: 80
             },
             series: [{
             "name": "每日活跃分布",
             "type": "bar",
             "data": ret.countCharts
             }]
             };
             // 为echarts对象加载数据
             activeChart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/paycostlogHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             paycostChart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['付费统计']
             },
             xAxis: [{
             name: '付费类型',
             type: 'category',
             data: ret.payTypeCharts
             }],
             yAxis: [{
             name: '付费次数',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 0,
             end: 100
             },
             series: [{
             "name": "付费统计",
             "type": "bar",
             "data": ret.countCharts
             }]
             };
             // 为echarts对象加载数据
             paycostChart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/dailypaylogHandle",
             success: function (data) {
             var ret = JSON.parse(data);
             dailypayChart.hideLoading();
             var option = {
             tooltip: {
             trigger: 'item'
             },
             toolbox: {
             show: true,
             feature: {
             mark: {
             show: true
             },
             dataView: {
             show: true,
             readOnly: false
             },
             dataZoom: {
             show: true,
             },
             magicType: {
             show: true,
             type: ['line', 'bar']
             },
             restore: {
             show: true
             },
             saveAsImage: {
             show: true
             }
             }
             },
             calculable: true,
             grid: {
             top: '12%',
             left: '1%',
             right: '10%',
             containLabel: true
             },
             legend: {
             data: ['每日付费']
             },
             xAxis: [{
             name: '日期',
             type: 'category',
             data: ret.dateCharts
             }],
             yAxis: [{
             name: '付费总额',
             type: 'value'
             }],
             dataZoom: {
             type: 'inside',
             show: true,
             realtime: true,
             y: 36,
             height: 20,
             backgroundColor: 'rgba(221,160,221,0.5)',
             dataBackgroundColor: 'rgba(138,43,226,0.5)',
             fillerColor: 'rgba(38,143,26,0.6)',
             handleColor: 'rgba(128,43,16,0.8)',
             start: 20,
             end: 80
             },
             series: [{
             "name": "每日付费",
             "type": "bar",
             "data": ret.countCharts
             }]
             };
             // 为echarts对象加载数据
             dailypayChart.setOption(option);
             },
             });
             ${symbol_dollar}.ajax({
             type: "GET",
             dataType: "text",
             url: "../datas/ipmaplogHandle",
             success: function (retData) {
             var ret = JSON.parse(retData);
             ipmapChart.hideLoading();
             var data = ret;
             var geoCoordMap = {
             '海门': [121.15, 31.89],
             '鄂尔多斯': [109.781327, 39.608266],
             '招远': [120.38, 37.35],
             '舟山': [122.207216, 29.985295],
             '齐齐哈尔': [123.97, 47.33],
             '盐城': [120.13, 33.38],
             '赤峰': [118.87, 42.28],
             '青岛': [120.33, 36.07],
             '乳山': [121.52, 36.89],
             '金昌': [102.188043, 38.520089],
             '泉州': [118.58, 24.93],
             '莱西': [120.53, 36.86],
             '日照': [119.46, 35.42],
             '胶南': [119.97, 35.88],
             '南通': [121.05, 32.08],
             '拉萨': [91.11, 29.97],
             '云浮': [112.02, 22.93],
             '梅州': [116.1, 24.55],
             '文登': [122.05, 37.2],
             '上海': [121.48, 31.22],
             '攀枝花': [101.718637, 26.582347],
             '威海': [122.1, 37.5],
             '承德': [117.93, 40.97],
             '厦门': [118.1, 24.46],
             '汕尾': [115.375279, 22.786211],
             '潮州': [116.63, 23.68],
             '丹东': [124.37, 40.13],
             '太仓': [121.1, 31.45],
             '曲靖': [103.79, 25.51],
             '烟台': [121.39, 37.52],
             '福州': [119.3, 26.08],
             '瓦房店': [121.979603, 39.627114],
             '即墨': [120.45, 36.38],
             '抚顺': [123.97, 41.97],
             '玉溪': [102.52, 24.35],
             '张家口': [114.87, 40.82],
             '阳泉': [113.57, 37.85],
             '莱州': [119.942327, 37.177017],
             '湖州': [120.1, 30.86],
             '汕头': [116.69, 23.39],
             '昆山': [120.95, 31.39],
             '宁波': [121.56, 29.86],
             '湛江': [110.359377, 21.270708],
             '揭阳': [116.35, 23.55],
             '荣成': [122.41, 37.16],
             '连云港': [119.16, 34.59],
             '葫芦岛': [120.836932, 40.711052],
             '常熟': [120.74, 31.64],
             '东莞': [113.75, 23.04],
             '河源': [114.68, 23.73],
             '淮安': [119.15, 33.5],
             '泰州': [119.9, 32.49],
             '南宁': [108.33, 22.84],
             '营口': [122.18, 40.65],
             '惠州': [114.4, 23.09],
             '江阴': [120.26, 31.91],
             '蓬莱': [120.75, 37.8],
             '韶关': [113.62, 24.84],
             '嘉峪关': [98.289152, 39.77313],
             '广州': [113.23, 23.16],
             '延安': [109.47, 36.6],
             '太原': [112.53, 37.87],
             '清远': [113.01, 23.7],
             '中山': [113.38, 22.52],
             '昆明': [102.73, 25.04],
             '寿光': [118.73, 36.86],
             '盘锦': [122.070714, 41.119997],
             '长治': [113.08, 36.18],
             '深圳': [114.07, 22.62],
             '珠海': [113.52, 22.3],
             '宿迁': [118.3, 33.96],
             '咸阳': [108.72, 34.36],
             '铜川': [109.11, 35.09],
             '平度': [119.97, 36.77],
             '佛山': [113.11, 23.05],
             '海口': [110.35, 20.02],
             '江门': [113.06, 22.61],
             '章丘': [117.53, 36.72],
             '肇庆': [112.44, 23.05],
             '大连': [121.62, 38.92],
             '临汾': [111.5, 36.08],
             '吴江': [120.63, 31.16],
             '石嘴山': [106.39, 39.04],
             '沈阳': [123.38, 41.8],
             '苏州': [120.62, 31.32],
             '茂名': [110.88, 21.68],
             '嘉兴': [120.76, 30.77],
             '长春': [125.35, 43.88],
             '胶州': [120.03336, 36.264622],
             '银川': [106.27, 38.47],
             '张家港': [120.555821, 31.875428],
             '三门峡': [111.19, 34.76],
             '锦州': [121.15, 41.13],
             '南昌': [115.89, 28.68],
             '柳州': [109.4, 24.33],
             '三亚': [109.511909, 18.252847],
             '自贡': [104.778442, 29.33903],
             '吉林': [126.57, 43.87],
             '阳江': [111.95, 21.85],
             '泸州': [105.39, 28.91],
             '西宁': [101.74, 36.56],
             '宜宾': [104.56, 29.77],
             '呼和浩特': [111.65, 40.82],
             '成都': [104.06, 30.67],
             '大同': [113.3, 40.12],
             '镇江': [119.44, 32.2],
             '桂林': [110.28, 25.29],
             '张家界': [110.479191, 29.117096],
             '宜兴': [119.82, 31.36],
             '北海': [109.12, 21.49],
             '西安': [108.95, 34.27],
             '金坛': [119.56, 31.74],
             '东营': [118.49, 37.46],
             '牡丹江': [129.58, 44.6],
             '遵义': [106.9, 27.7],
             '绍兴': [120.58, 30.01],
             '扬州': [119.42, 32.39],
             '常州': [119.95, 31.79],
             '潍坊': [119.1, 36.62],
             '重庆': [106.54, 29.59],
             '台州': [121.420757, 28.656386],
             '南京': [118.78, 32.04],
             '滨州': [118.03, 37.36],
             '贵阳': [106.71, 26.57],
             '无锡': [120.29, 31.59],
             '本溪': [123.73, 41.3],
             '克拉玛依': [84.77, 45.59],
             '渭南': [109.5, 34.52],
             '马鞍山': [118.48, 31.56],
             '宝鸡': [107.15, 34.38],
             '焦作': [113.21, 35.24],
             '句容': [119.16, 31.95],
             '北京': [116.46, 39.92],
             '徐州': [117.2, 34.26],
             '衡水': [115.72, 37.72],
             '包头': [110, 40.58],
             '绵阳': [104.73, 31.48],
             '乌鲁木齐': [87.68, 43.77],
             '枣庄': [117.57, 34.86],
             '杭州': [120.19, 30.26],
             '淄博': [118.05, 36.78],
             '鞍山': [122.85, 41.12],
             '溧阳': [119.48, 31.43],
             '库尔勒': [86.06, 41.68],
             '安阳': [114.35, 36.1],
             '开封': [114.35, 34.79],
             '济南': [117, 36.65],
             '德阳': [104.37, 31.13],
             '温州': [120.65, 28.01],
             '九江': [115.97, 29.71],
             '邯郸': [114.47, 36.6],
             '临安': [119.72, 30.23],
             '兰州': [103.73, 36.03],
             '沧州': [116.83, 38.33],
             '临沂': [118.35, 35.05],
             '南充': [106.110698, 30.837793],
             '天津': [117.2, 39.13],
             '富阳': [119.95, 30.07],
             '泰安': [117.13, 36.18],
             '诸暨': [120.23, 29.71],
             '郑州': [113.65, 34.76],
             '哈尔滨': [126.63, 45.75],
             '聊城': [115.97, 36.45],
             '芜湖': [118.38, 31.33],
             '唐山': [118.02, 39.63],
             '平顶山': [113.29, 33.75],
             '邢台': [114.48, 37.05],
             '德州': [116.29, 37.45],
             '济宁': [116.59, 35.38],
             '荆州': [112.239741, 30.335165],
             '宜昌': [111.3, 30.7],
             '义乌': [120.06, 29.32],
             '丽水': [119.92, 28.45],
             '洛阳': [112.44, 34.7],
             '秦皇岛': [119.57, 39.95],
             '株洲': [113.16, 27.83],
             '石家庄': [114.48, 38.03],
             '莱芜': [117.67, 36.19],
             '常德': [111.69, 29.05],
             '保定': [115.48, 38.85],
             '湘潭': [112.91, 27.87],
             '金华': [119.64, 29.12],
             '岳阳': [113.09, 29.37],
             '长沙': [113, 28.21],
             '衢州': [118.88, 28.97],
             '廊坊': [116.7, 39.53],
             '菏泽': [115.480656, 35.23375],
             '合肥': [117.27, 31.86],
             '武汉': [114.31, 30.52],
             '大庆': [125.03, 46.58]
             };
             for (var i = 0; i < data.length; i++) {
             if (geoCoordMap[data[i].name] == undefined) {
             data.splice(i, 1);
             }
             }
             var convertData = function (data) {
             var res = [];
             for (var i = 0; i < data.length; i++) {
             var geoCoord = geoCoordMap[data[i].name];
             if (geoCoord) {
             res.push({
             name: data[i].name,
             value: geoCoord.concat(data[i].value)
             });
             }
             }
             return res;
             };
             option = {
             backgroundColor: '${symbol_pound}404a59',
             title: {
             text: '全国玩家分布',
             subtext: '',
             sublink: '',
             left: 'center',
             textStyle: {
             color: '${symbol_pound}fff'
             }
             },
             tooltip: {
             trigger: 'item'
             },
             legend: {
             orient: 'vertical',
             y: 'bottom',
             x: 'right',
             data: ['人数'],
             textStyle: {
             color: '${symbol_pound}fff'
             }
             },
             geo: {
             map: 'china',
             label: {
             emphasis: {
             show: false
             }
             },
             roam: true,
             itemStyle: {
             normal: {
             areaColor: '${symbol_pound}323c48',
             borderColor: '${symbol_pound}111'
             },
             emphasis: {
             areaColor: '${symbol_pound}2a333d'
             }
             }
             },
             series: [{
             name: '人数',
             type: 'scatter',
             coordinateSystem: 'geo',
             data: convertData(data),
             symbolSize: function (val) {
             return val[2] / 10;
             },
             /!* hoverAnimation : true, *!/
             label: {
             normal: {
             formatter: '{b}',
             position: 'right',
             show: false
             },
             emphasis: {
             show: true
             }
             },
             itemStyle: {
             normal: {
             color: '${symbol_pound}ddb926'
             }
             }
             }, {
             name: 'Top 5',
             type: 'effectScatter',
             coordinateSystem: 'geo',
             data: convertData(data.sort(function (a, b) {
             return b.value - a.value;
             }).slice(0, 5)),
             symbolSize: function (val) {
             return val[2] / 10;
             },
             showEffectOn: 'render',
             rippleEffect: {
             brushType: 'stroke'
             },
             hoverAnimation: true,
             label: {
             normal: {
             formatter: '{b}',
             position: 'right',
             show: true
             }
             },
             itemStyle: {
             normal: {
             color: '${symbol_pound}f4e925',
             shadowBlur: 10,
             shadowColor: '${symbol_pound}333'
             }
             },
             zlevel: 1
             }]
             };
             // 为echarts对象加载数据
             ipmapChart.setOption(option);
             }
             });*/
        </script>
    </div>
</body>
</html>