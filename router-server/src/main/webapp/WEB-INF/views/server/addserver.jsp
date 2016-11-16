<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script type="text/javascript" src="../js/server/server.js"></script>-->
<script type="text/javascript">
	function saveServer() {
		var id = $('#id').val();
		var name = $('#name').val();
		var ip = $('#ip').val();
		var port = $('#port').val();
		var tomcatPort = $('#tomcatPort').val();
		var state = $('#state').val();
		var max = $('#max').val();
		var remark = $('#remark').val();
		var whiteUsers = $('#whiteUsers').val();
		var openChannels = $('#openChannels').val();
		var whiteMode = $("input[name='whiteMode']:checked").val() == 'on' ? 1
				: 0;
		if (id == "") {
			alert("请填写服务器id");
			return false;
		}
		if (name == "") {
			alert("请填写服务器名字");
			return false;
		}
		if (ip == "") {
			alert("请填写服务器ip");
			return false;
		}
		if (openChannels == "") {
			alert("请填写开放渠道");
			return false;
		}
		if (port == "") {
			alert("请填写服务器端口");
			return false;
		}
		if (tomcatPort == "") {
			alert("请填写容器端口");
			return false;
		}
		var datas = {
			"id" : id,
			"name" : name,
			"ip" : ip,
			"port" : port,
			"openChannels" : openChannels,
			"tomcatPort" : tomcatPort,
			"state" : state,
			"remark" : remark,
			"whiteUsers" : whiteUsers,
			"whiteMode" : whiteMode
		};
		$.ajax({
			data : datas,
			type : "POST",
			url : "../server/saveserver",
			success : function(result) {
				if (result == "SUCCESS") {
					alert("保存服务器成功");
					$('#right').load("../server/serverlist");
				} else {
					alert("保存服务器失败");
				}
			},
			error : function(result) {
				alert("保存服务器失败");
			}
		});
	}
</script>
<title>保存服务器配置信息</title>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>添加服务器</strong>
					</h2>
				</div>
				<div class="panel-body">
					<form action="" method="post" class="form-horizontal ">
						<div class="form-group">
							<label class="col-md-3 control-label">服务器id</label>
							<div class="col-md-9">
								<input id="id" placeholder="服务器id" type="number"
									value="${server.id }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">服务器名称</label>
							<div class="col-md-9">
								<input id="name" placeholder="服务器名称" type="text"
									value="${server.name }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">ip</label>
							<div class="col-md-9">
								<input id="ip" placeholder="服务器ip" type="text"
									value="${server.ip }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">端口</label>
							<div class="col-md-9">
								<input id="port" placeholder="服务器端口" type="number"
									value="${server.port }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">容器端口</label>
							<div class="col-md-9">
								<input id="tomcatPort" placeholder="容器端口" type="number"
									value="${server.tomcatPort }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">状态</label>
							<div class="col-md-9">
								<select id="state" name="select" class="form-control" size="1">
									<option value="0"
										<c:if test="${server.state==0 }">selected="selected"</c:if>>新服</option>
									<option value="1"
										<c:if test="${server.state==1 }">selected="selected"</c:if>>流畅</option>
									<option value="2"
										<c:if test="${server.state==2 }">selected="selected"</c:if>>爆满</option>
									<option value="3"
										<c:if test="${server.state==3 }">selected="selected"</c:if>>维护</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">白名单模式</label>
							<div class="controls">
								<div class="col-md-1 col-sm-2 col-xs-3">
									<label class="switch switch-success"> <input
										name="whiteMode" type="checkbox" class="switch-input"
										<c:if test="${server.whiteMode==1 }">checked="checked"</c:if>>
										<span class="switch-label" data-on="On" data-off="Off"></span>
										<span class="switch-handle"></span>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="whiteUsers">白名单账号</label>
							<div class="col-md-9">
								<textarea id="whiteUsers" name="whiteUsers" class="form-control"
									placeholder="请输入白名单账号（或id），格式为'name1,name2,name3,id1,id2,id3'">${server.whiteUsers }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="openChannels">开放渠道<br>
							<a
								href="http://123.59.110.201:8000/showdoc/index.php?s=/5&page_id=50"
								target="_blank">渠道号查看</a></label>
							<div class="col-md-9">
								<textarea id="openChannels" name="openChannels"
									class="form-control" placeholder="请输入开放渠道，格式为'渠道1,渠道2'">${server.openChannels }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注</label>
							<div class="col-md-9">
								<input id="remark" placeholder="服务器备注" type="text"
									value="${server.remark }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<br>
					</form>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-success btn-lg btn-block"
						onclick="saveServer()">
						<i class="fa fa-dot-circle-o"></i> 保存
					</button>
					<!-- <button type="reset" class="btn btn-sm btn-danger">
						<i class="fa fa-ban"></i> Reset
					</button> -->
				</div>
			</div>
		</div>
		<!--/.col-->
</body>
</html>