#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
<!-- <style type="text/css">
.queryLayer {
	width: 100%;
	text-align: center;
	padding-top: 200px;
}

${symbol_pound}loginBtn, ${symbol_pound}clearBtn {
	width: 10%;
	height: 50px;
	line-height: 50px;
	background-color: lightblue;
	opacity: 1.0;
	display: inline-block;
}

${symbol_pound}loginBtn:hover, ${symbol_pound}clearBtn:hover {
	opacity: 0.8;
}

.textbox {
	border: 0px;
}
</style> -->
<script type="text/javascript">
	function queryPlayer() {
		var userID = ${symbol_dollar}('${symbol_pound}userID').val();
		var name = ${symbol_dollar}('${symbol_pound}name').val();
		if (userID == '' && name == '') {
			alert('请输入查询条件');
			return;
		}
		${symbol_dollar}.ajax({
			type : "get",
			dataType : "text",
			data : {
				"userID" : userID,
				"name" : name
			},
			url : "../datas/queryHandle",
			success : function(data) {
				if (data == "success") {
					skip("../datas/player?userID=" + userID + "&name=" + name);
				} else {
					alert("玩家不存在");
					return;
				}
			}
		});
	}

	function clearPlayer() {
		var userID = ${symbol_dollar}('${symbol_pound}userID').val();
		var name = ${symbol_dollar}('${symbol_pound}name').val();
		if (userID == '' && name == '') {
			alert('请输入查询条件');
			return;
		}
		var flag = confirm('确定要清空账号信息吗？该过程不可逆！');
		if (!flag) {
			return;
		}
		${symbol_dollar}.ajax({
			type : "get",
			dataType : "text",
			data : {
				"userID" : userID,
				"name" : name
			},
			url : "../datas/clearHandle",
			success : function(data) {
				if (data == "success") {
					alert("已清空玩家信息");
				} else {
					alert("玩家不存在");
					return;
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-12 full">

			<div class="row">

				<div
					class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-xs-6 col-xs-offset-3 login-box-locked">
					玩家UID： <input id="userID" class="form-control" size="16"
						type="text" placeholder="玩家UID"> <br>角色名 ：<br>
					<input id="name" class="form-control" size="16" type="text"
						placeholder="角色名"> <span class="help-block"> <small>只需要输入其中一个</small>
					</span>
					<button id="loginBtn" type="button" class="btn btn-primary"
						onclick="queryPlayer()">查询</button>
					<button id="clearBtn" type="button" class="btn btn-danger" disabled="disabled"
						onclick="clearPlayer()">一键清空账号</button>
				</div>
				<!--/col-->

			</div>
			<!--/row-->

		</div>
		<!--/content-->

	</div>
	<!--/row-->

	<!-- <div class="queryLayer">
		<center>
			玩家UID:&nbsp;&nbsp;&nbsp;&nbsp;<input id="userID" placeHolder="玩家UID" />&nbsp;&nbsp;&nbsp;&nbsp;
			或&nbsp;&nbsp;角色名:&nbsp;&nbsp;&nbsp;&nbsp;<input id="name"
				placeHolder="角色名" />&nbsp;&nbsp;&nbsp;&nbsp;
			<div id="loginBtn" onclick="queryPlayer()">查询</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div id="clearBtn" onclick="clearPlayer()">一键清空账号</div>
		</center>
	</div> -->
</body>
</html>