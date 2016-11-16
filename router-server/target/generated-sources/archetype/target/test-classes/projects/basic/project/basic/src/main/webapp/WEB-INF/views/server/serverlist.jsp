<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/server/server.js"></script>
<title>服务器列表</title>
<script type="text/javascript">
function startServer(ip){
	var host = ip;
	var flag = confirm("确定要开服吗？");
	if(flag){
		var datas = {
			"host" : host
		};
		$.ajax({
			data : datas,
			dataType : "text",
			type : "GET",
			url : "../server/operateStart",
			success : function(result) {
				if (result == "success") {
					alert("开服成功");
					$('#right').load("../server/serverlist");
				} else if(result == "started"){
					alert("服务器已开启");
				} else {
					alert("开服失败");
				}
			},
			error : function(result) {
				alert("网络连接失败");
			}
		});
	}
}

function shutServer(ip){
	var host = ip;
	var flag = confirm("确定要关服吗？");
	if(flag){
		var datas = {
			"host" : host
		};
		$.ajax({
			data : datas,
			dataType : "text",
			type : "GET",
			url : "../server/operateShut",
			success : function(result) {
				if (result == "success") {
					alert("关服成功");
					$('#right').load("../server/serverlist");
				} else if(result == "shutted"){
					alert("服务器已关闭");
				} else {
					alert("关服失败");
				}
			},
			error : function(result) {
				alert("网络连接失败");
			}
		});
	}
}

function delServer(serverid) {
	var id = serverid;
	var flag = confirm("确定要删除服务器配置信息吗？");
	if(flag){
		var datas = {
			"id" : id
		};
		$.ajax({
			data : datas,
			type : "POST",
			url : "../server/deleteserver",
			success : function(result) {
				if (result == "SUCCESS") {
					alert("删除服务器成功");
					$('#right').load("../server/serverlist");
				} else {
					alert("删除服务器失败");
				}
			},
			error : function(result) {
				alert("删除服务器失败");
			}
		});
	}
}
</script>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-table red"></i><span class="break"></span><strong>
							服务器列表 </strong>
					</h2>
					<!-- <div class="panel-actions">
						<a href="table.html#" class="btn-setting"><i
							class="fa fa-rotate-right"></i></a> <a href="table.html#"
							class="btn-minimize"><i class="fa fa-chevron-up"></i></a> <a
							href="table.html#" class="btn-close"><i class="fa fa-times"></i></a>
					</div> -->
				</div>
				<div class="panel-body">
					<table
						class="table table-bordered table-striped table-condensed table-hover">
						<thead>
							<tr>
								<th>服务器id</th>
								<th>服务器名称</th>
								<th>ip</th>
								<th>端口</th>
								<th>状态</th>
								<th>白名单模式</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${serverList.size==0 }">
									<tr>
										<td colspan="7">暂无服务器</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="server" items="${list }">
										<tr>
											<td>${server.id }</td>
											<td>${server.name }</td>
											<td>${server.ip }</td>
											<td>${server.port }</td>
											<td><c:choose>
													<c:when test="${server.state==0 }">新服</c:when>
													<c:when test="${server.state==1 }">流畅</c:when>
													<c:when test="${server.state==2 }">爆满</c:when>
													<c:when test="${server.state==3 }">维护</c:when>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${server.whiteMode==1 }">
													是
												</c:when>
													<c:otherwise>
													否
												</c:otherwise>
												</c:choose></td>
											<td>${server.remark }</td>
											<td><a href="#" class="btn btn-primary"
												onclick="javascript:skip('../server/addserver?id=${server.id}')">修改</a>
												<a
												href="http://${server.ip}:${server.tomcatPort }/logical/admin/index"
												class="btn btn-success" target="_blank">管理</a> <a href="#"
												onclick="delServer(${server.id})" class="btn btn-danger">删除</a>
												<c:choose>
													<c:when test="${server.state==3 }">
														<a href="#" onclick="startServer('${server.ip}')"
															class="btn btn-danger">开服</a>
													</c:when>
													<c:otherwise>
														<a href="#" onclick="shutServer('${server.ip}')"
															class="btn btn-danger">关服</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--/col-->
	</div>
	<!--/row-->
</body>
</html>