<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>游戏文件服模板——后台管理系统</title>

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
    <script type="text/javascript">
        function doUpdate(channel, base, field, zipLength) {
            $.ajax({
                type: "post",
                dataType: "text",
                data: {
                    'channel': channel,
                    'base': base,
                    'version': $(field).parent().parent().find("[name='version']")
                            .val(),
                    'zipLength': zipLength
                },
                url: "../file/doUpdate",
                success: function (data) {
                    if (data == "success") {
                        alert('更改版本成功');
                        location.href = "../file/hotfix";
                    } else {
                        alert('更改版本失败');
                        return;
                    }
                }
            });
        }

        function doAdd() {
            var channel = $('#channel').val();
            var base = $('#base').val();
            $.ajax({
                type: "post",
                dataType: "text",
                data: {
                    'channel': channel,
                    'base': base
                },
                url: "../file/doAdd",
                success: function (data) {
                    if (data == "success") {
                        alert('添加版本成功');
                        location.href = "../file/hotfix";
                    } else {
                        alert('添加版本失败');
                        return;
                    }
                }
            });
        }

        function doDelete(channel, base, field) {
            $.ajax({
                type: "post",
                dataType: "text",
                data: {
                    'channel': channel,
                    'base': base,
                    'version': $(field).parent().parent().find("[name='version']")
                            .val()
                },
                url: "../file/doDelete",
                success: function (data) {
                    if (data == "success") {
                        alert('删除版本成功');
                        location.href = "../file/hotfix";
                    } else {
                        alert('删除版本失败');
                        return;
                    }
                }
            });
        }

        function doUpload() {
            var formData = new FormData($("#uploadForm")[0]);
            $('#uploadBtn').attr('disabled', 'disabled');
            $('#uploadBtn').val('正在上传...');
            $.ajax({
                url: '../file/fileupload',
                type: 'POST',
                data: formData,
                dataType: 'text',
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data == 'success') {
                        alert('上传更新包成功');
                        location.reload();
                    } else if (data == 'pwderr') {
                        alert('上传密码错误');
                        location.reload();
                    } else {
                        alert('文件服务器错误');
                        location.reload();
                    }
                },
                error: function (data) {
                    console.log(data);
                    location.reload();
                }
            });
        }

        function download(url, file) {
            var pwd = window.prompt('请输入下载文件密码：', '');
            if (pwd == null || pwd == '') {
                alert('密码不能为空');
                return;
            }
            window.open("../file/filedownload?name=" + file + "&pwd=" + pwd);
        }
    </script>
</head>
<body>
<!-- start: Header -->
<div class="navbar" role="navigation">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-actions navbar-left">
            <li class="visible-md visible-lg"><span>服务器启动时间：${startTime }</span></li>
        </ul>
    </div>
</div>
<!-- end: Header -->
<input id="test#123" value="testsetset"/>
<div class="container-fluid content">
    <div class="row">

        <!-- start: Main Menu -->
        <div class="sidebar ">
            <div class="sidebar-collapse">
                <div class="sidebar-header t-center"></div>
                <div class="sidebar-menu">
                    <ul class="nav nav-sidebar">
                        <li><a href="#" onclick=""><i class="fa fa-laptop"></i><span
                                class="text"> 文件管理系统</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="sidebar-footer">

                <%--<div class="sidebar-brand">游戏文件服模板</div>

                <ul class="sidebar-terms">
                    <li><a
                        href="http://127.0.0.1/router/admin/index"
                        target="_blank">登录服</a></li>
                    <li><a
                        href="http://127.0.0.1/pay/admin/index"
                        target="_blank">支付服</a></li>
                </ul>
                <ul class="sidebar-terms">
                    <li><a href="http://127.0.0.1/motan"
                        target="_blank">Motan</a></li>
                </ul>--%>
            </div>

        </div>
        <!-- end: Main Menu -->
        <!-- start: Content -->
        <div class="main" id="right">
            <!-- Form 版本信息 Start -->
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2>
                                <i class="fa fa-indent red"></i><strong>更新版本信息</strong>
                            </h2>
                        </div>
                        <form id="uploadForm" class="form-horizontal ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">更新包名</label>
                                    <div class="col-md-9">
                                        <input type="text" name="filename" value=""
                                               placeholder="包名（包括后缀，不填使用默认文件名）" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">基础版本号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="base" value=""
                                               placeHolder="请填写基础版本号" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">更新版本号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="version" value=""
                                               placeHolder="请填写更新版本号" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">渠道号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="channel" value=""
                                               placeHolder="请填写渠道号" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">上传密码</label>
                                    <div class="col-md-9">
                                        <input type="password" name="pwd" value=""
                                               placeHolder="请填写上传密码" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <input type='text' name='textfield' id='textfield' class='txt'
                                       disabled="disabled" placeHolder="选择的文件名"/> <input
                                    type='button' class='btn btn-success' value='浏览'
                                    onclick="fileField.click()"/> <input type="file" name="file"
                                                                         class="file" id="fileField"
                                                                         style="display: none" size="28"
                                                                         onchange="document.getElementById('textfield').value=this.value"/>
                                <input class="btn btn-success" type="button" id="uploadBtn"
                                       value="上传文件" onclick="doUpload()"/>
                            </div>
                        </form>
                    </div>
                </div>
                <!--/.col-->
                <!-- Form 版本信息 End -->

                <!-- Table 版本列表 Start -->
                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2>
                                    <i class="fa fa-table red"></i><span class="break"></span><strong>当前版本列表</strong>
                                </h2>
                            </div>
                            <div class="panel-body">
                                <table
                                        class="table table-bordered table-striped table-condensed table-hover">
                                    <thead>
                                    <tr>
                                        <th>渠道号</th>
                                        <th>基础版本号</th>
                                        <th>更新版本号</th>
                                        <th>更新包大小(B)</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${verMap }" var="tmpVer">
                                        <tr>
                                            <c:set value="${ fn:split(tmpVer.value, '#') }" var="vers"/>
                                            <c:set value="${ fn:split(tmpVer.key, '#') }" var="keys"/>
                                            <td><input type="hidden" value="${keys[0] }"/>${keys[0] }</td>
                                            <td><input type="hidden" value="${keys[1] }"/>${keys[1] }</td>
                                            <td><input name="version" class="form-control"
                                                       value="${vers[0] }"></td>
                                            <td><input type="hidden" value="${vers[1] }">${vers[1] }</td>
                                            <td>
                                                <button class="btn btn-info"
                                                        onclick="doUpdate('${keys[0] }','${keys[1] }',this,'${vers[1] }')">
                                                    修改
                                                </button>
                                                <button class="btn btn-danger"
                                                        onclick="doDelete('${keys[0] }','${keys[1] }',this)">删除
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td><input id="channel" class="form-control"
                                                   type="text" placeHolder="请填写渠道号"/></td>
                                        <td><input id="base" class="form-control" type="text"
                                                   placeHolder="请填写基础版本号"/></td>
                                        <td><input id="version" class="form-control"
                                                   placeHolder="1.0.0" disabled="disabled"></td>
                                        <td><input id="file" class="form-control"
                                                   placeHolder="0" disabled="disabled"></td>
                                        <td>
                                            <button class="btn btn-info" onclick="doAdd()">添加</button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
                <!-- Table 版本列表 End -->

                <!-- Div 文件列表 Start -->
                <c:set var="count" value="0"/>
                <c:forEach items="${files }" var="file">
                    <c:set var="count" value="${count+1 }"/>
                    <c:if test="${(count+3)%4==0 }">
                        <div class="row">
                    </c:if>
                    <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
                        <div class="smallstat green-bg">
                            <i class="fa fa-cloud-download white-bg"
                               onclick="download('${url}','${file }')"></i><span class="title">${file }</span>
                        </div>
                        <!--/.smallstat-->
                    </div>
                    <!--/.col-->
                    <%-- <div onclick="doDownload('${name}')">
            <span>${name }.csv</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>${name }</span>
        </div> --%>
                    <c:if test="${count%4==0 }">
                        </div>
                    </c:if>
                </c:forEach>
                <!-- Div 文件列表 End -->

            </div>
            <!--/container-->
            <div class="clearfix"></div>
        </div>
    </div>
</body>
</html>