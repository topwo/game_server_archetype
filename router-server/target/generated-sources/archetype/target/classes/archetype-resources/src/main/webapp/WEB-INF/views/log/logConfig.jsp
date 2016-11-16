#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志管理</title>
<!-- <style type="text/css">
.logConfig {
	text-align: center;
}

.logConfigLayer {
	
}

.logConfigLayer ul {
	list-style-type: none;
	display: inline-block;
	width: 20%;
}

.logConfigLayer ul li {
	margin: 20px;
	height: 40px;
	line-height: 40px;
}

.logConfigLayer .btn {
	width: 100%;
	height: 50px;
	line-height: 50px;
	background-color: lightblue;
}

.logConfigLayer .btn:hover {
	opacity: 0.8;
}
</style> -->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	function update() {
		var datas = {
			sdk : ${symbol_dollar}("input[name='sdk']:checked").val() == 'on' ? true : false,
			msg : ${symbol_dollar}("input[name='msg']:checked").val() == 'on' ? true : false,
			client : ${symbol_dollar}("input[name='client']:checked").val() == 'on' ? true
					: false
		}
/* 		alert('修改配置成功'); */
		${symbol_dollar}.ajax({
			url : "../log/logConfigHandle",
			type : "post",
			data : datas,
			success : function(data) {
				if (data == "success") {
					alert("修改配置成功");
					${symbol_dollar}("${symbol_pound}right").load("../log/logConfig");
				}
			}
		});
	}
</script>
</head>
<body>

	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>参数配置</strong>
					</h2>
				</div>
				<div class="panel-body">
					<form action="" method="post" class="form-horizontal ">
						<div class="form-group">
							<label class="col-lg-2 col-md-2 col-sm-6 control-label">SDK日志</label>
							<div class="controls">
								<div class="col-md-1 col-sm-2 col-xs-3">
									<label class="switch switch-success"> <input name="sdk"
										type="checkbox" class="switch-input"
										<c:if test="${symbol_dollar}{sdk==true }">checked="checked"</c:if>> <span
										class="switch-label" data-on="On" data-off="Off"></span> <span
										class="switch-handle"></span>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 col-md-2 col-sm-6 control-label">消息日志</label>
							<div class="controls">
								<div class="col-md-1 col-sm-2 col-xs-3">
									<label class="switch switch-success"> <input name="msg"
										type="checkbox" class="switch-input"
										<c:if test="${symbol_dollar}{msg==true }">checked="checked"</c:if>> <span
										class="switch-label" data-on="On" data-off="Off"></span> <span
										class="switch-handle"></span>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 col-md-2 col-sm-6 control-label">客户端日志</label>
							<div class="controls">
								<div class="col-md-1 col-sm-2 col-xs-3">
									<label class="switch switch-success"> <input
										name="client" type="checkbox" class="switch-input"
										<c:if test="${symbol_dollar}{client==true }">checked="checked"</c:if>> <span
										class="switch-label" data-on="On" data-off="Off"></span> <span
										class="switch-handle"></span>
									</label>
								</div>
							</div>
						</div>

					</form>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-success btn-lg btn-block"
						onclick="javascript:update()">
						<i class="fa fa-dot-circle-o"></i> 修改
					</button>
				</div>
			</div>
		</div>
		<!--/col-->

	</div>
	<!--/row-->

	<%-- <div class="logConfig">
		<div class="logConfigLayer">
			<ul>
				<li><h2>管理系统</h2></li>
				<li>SDK 日志&nbsp;&nbsp;&nbsp;&nbsp;<input class="textbox"
					type="radio" name="sdk" value="true"
					<c:if test="${symbol_dollar}{sdk==true }">checked="checked"</c:if> />&nbsp;开&nbsp;&nbsp;<input
					class="textbox" type="radio" name="sdk" value="false"
					<c:if test="${symbol_dollar}{sdk==false }">checked="checked"</c:if> />&nbsp;关&nbsp;&nbsp;
				</li>
				<li>消息日志&nbsp;&nbsp;&nbsp;&nbsp;<input class="textbox"
					type="radio" name="msg" value="true"
					<c:if test="${symbol_dollar}{msg==true }">checked="checked"</c:if> />&nbsp;开&nbsp;&nbsp;<input
					class="textbox" type="radio" name="msg" value="false"
					<c:if test="${symbol_dollar}{msg==false }">checked="checked"</c:if> />&nbsp;关&nbsp;&nbsp;
				</li>
				<li>客户端日志&nbsp;&nbsp;&nbsp;<input class="textbox" type="radio"
					name="client" value="true"
					<c:if test="${symbol_dollar}{client==true }">checked="checked"</c:if> />&nbsp;开&nbsp;&nbsp;<input
					class="textbox" type="radio" name="client" value="false"
					<c:if test="${symbol_dollar}{client==false }">checked="checked"</c:if> />&nbsp;关&nbsp;&nbsp;
				</li>
				<li><div class="btn" onclick="update()">修改</div></li>
			</ul>
		</div>
	</div> --%>
</body>
</html>