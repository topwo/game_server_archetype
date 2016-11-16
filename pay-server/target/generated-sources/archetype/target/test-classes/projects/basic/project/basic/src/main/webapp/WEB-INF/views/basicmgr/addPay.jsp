<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加支付记录</title>
<script type="text/javascript">
	function addPay() {
		var account = $("#account").val();
		var amount = $("#amount").val();
		var channel = $("#channel").val();
		var gamename = $("#gamename").val();
		var goodType = $("#goodType").val();
		var basicDate = $("#basicDate").val();
		var isFinished = $("#isFinished").val();
		var orderid = $("#orderid").val();
		var userid = $("#userid").val();
		if (account == '') {
			alert('请输入账号id');
			return;
		}
		if (amount == '') {
			alert('请输入金额');
			return;
		}
		if (channel == '') {
			alert('请输入渠道');
			return;
		}
		if (gamename == '') {
			alert('请输入游戏名');
			return;
		}
		if (basicDate == '') {
			alert('请输入支付日期');
			return;
		}
		if (isFinished == '') {
			alert('请输入订单状态');
			return;
		}
		if (orderid == '') {
			alert('请输入联运订单号');
			return;
		}
		if (userid == '') {
			alert('请输入玩家ID');
			return;
		}
		var datas = {
			"account" : account,
			"amount" : amount,
			"channel" : channel,
			"gamename" : gamename,
			"goodType" : goodType,
			"basicDate" : basicDate,
			"isFinished" : isFinished,
			"orderid" : orderid,
			"userid" : userid
		}
		$.ajax({
			url : "../basicmgr/addPayHandle",
			dataType : "text",
			type : "post",
			data : datas,
			success : function(data) {
				if (data == "success") {
					alert("添加支付记录成功");
					$("#right").load("../basicmgr/basiclist");
				} else {
					alert("添加支付记录失败");
				}
			}
		});
	}
</script>
</head>
<body>

	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>订单信息</strong>
					</h2>
				</div>
				<div class="panel-body">
					<form action="" method="post" class="form-horizontal ">
						<div class="form-group">
							<label class="col-md-3 control-label">联运订单号</label>
							<div class="col-md-9">
								<input type="text" name="orderid" id="orderid"
									class="form-control" placeHolder="请输入联运订单号" value="add_order" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">玩家ID</label>
							<div class="col-md-9">
								<input type="number" name="userid" id="userid"
									class="form-control" placeHolder="请输入玩家ID" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">玩家账号</label>
							<div class="col-md-9">
								<input type="text" name="account" id="account"
									class="form-control" placeHolder="请输入玩家账号" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">充值金额</label>
							<div class="col-md-9">
								<input type="number" id="amount" class="form-control"
									placeHolder="请输入充值金额">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">渠道</label>
							<div class="col-md-9">
								<select name="channel" class="form-control" id="channel">
									<option value="0">无渠道</option>
									<option value="ios-xy">xy</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">游戏名</label>
							<div class="col-md-9">
								<input type="text" id="gamename" class="form-control"
									placeHolder="请输入游戏名" value="游戏名称">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品类型</label>
							<div class="col-md-9">
								<select name="goodType" class="form-control" id="goodType">
									<option value="1">首冲</option>
									<option value="2">普通会员</option>
									<option value="3">超级会员</option>
									<option value="4">豪华签到</option>
									<option value="5">购买14888元宝</option>
									<option value="6">购买6888元宝</option>
									<option value="7">购买3888元宝</option>
									<option value="8">购买1888元宝</option>
									<option value="9">购买1088元宝</option>
									<option value="10">购买3888元宝</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">订单状态</label>
							<div class="col-md-9">
								<select name="isFinished" class="form-control" id="isFinished">
									<option value="0">未验证</option>
									<option value="1">验证失败</option>
									<option value="2">未发货</option>
									<option value="3">发货失败</option>
									<option value="4" selected="selected">订单完成</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">支付日期</label>
							<div class="col-md-9">
								<input type="text" id="basicDate" class="form-control"
									placeHolder="请输入支付日期" value="2015-01-01">
							</div>
						</div>
						<br>
					</form>
				</div>
				<div class="panel-footer">
					<button id="searchBtn" type="button"
						class="btn btn-success btn-lg btn-block" onclick="addPay()">
						<i class="fa fa-dot-circle-o"></i> 添加
					</button>
					<!-- <button type="reset" class="btn btn-sm btn-danger">
						<i class="fa fa-ban"></i> Reset
					</button> -->
				</div>
			</div>
		</div>
		<!--/.col-->
	</div>
	<!-- row -->


	<%-- <div class="addPay">
		<center>
			<table>
				<tr>
					<td>玩家userID</td>
					<td><input type="number" id="accId" placeHolder="请输入账号userID"></td>
				</tr>
				<tr>
					<td>平台账号id</td>
					<td><input type="text" id="userId" placeHolder="请输入第三方账号id"
						value="test_uid"></td>
				</tr>
				<tr>
					<td>厂商订单号</td>
					<td><input type="text" id="orderId" placeHolder="请输入厂商订单号"
						value="test_order_id"></td>
				</tr>
				<tr>
					<td>充值金额（元）</td>
					<td><input type="number" id="amount" placeHolder="请输入充值金额"></td>
				</tr>
				<tr>
					<td>渠道</td>
					<td><select name="channel" id="channel">
							<option value="0"
								<c:if test="${channel==0 }">selected="selected"</c:if>>无渠道</option>
							<option value="android-xiaomi"
								<c:if test="${channel=='android-xiaomi' }">selected="selected"</c:if>>小米</option>
							<option value="android-360"
								<c:if test="${channel=='android-360' }">selected="selected"</c:if>>360</option>
							<option value="android-4399"
								<c:if test="${channel=='android-4399' }">selected="selected"</c:if>>4399</option>
							<option value="android-le8"
								<c:if test="${channel=='android-le8' }">selected="selected"</c:if>>乐8</option>
							<option value="android-paojiao"
								<c:if test="${channel=='android-paojiao' }">selected="selected"</c:if>>泡椒</option>
							<option value="android-baidu"
								<c:if test="${channel=='android-baidu' }">selected="selected"</c:if>>百度</option>
							<option value="android-tecent"
								<c:if test="${channel=='android-tecent' }">selected="selected"</c:if>>腾讯</option>
							<option value="ios-xy"
								<c:if test="${channel=='ios-xy' }">selected="selected"</c:if>>XY助手</option>
							<option value="ios-aisi"
								<c:if test="${channel=='ios-aisi' }">selected="selected"</c:if>>爱思</option>
							<option value="ios-haima"
								<c:if test="${channel=='ios-haima' }">selected="selected"</c:if>>海马安卓</option>
							<option value="android-haima"
								<c:if test="${channel=='android-haima' }">selected="selected"</c:if>>海马iOS</option>
							<option value="ios-kuaiyong"
								<c:if test="${channel=='ios-kuaiyong' }">selected="selected"</c:if>>快用</option>
							<option value="ios"
								<c:if test="${channel=='ios' }">selected="selected"</c:if>>iOS正版</option>
							<option value="android-sandayunying"
								<c:if test="${channel=='android-sandayunying' }">selected="selected"</c:if>>三大运营商</option>
							<option value="android-uc"
								<c:if test="${channel=='android-uc' }">selected="selected"</c:if>>UC</option>
							<option value="android-jinli"
								<c:if test="${channel=='android-jinli' }">selected="selected"</c:if>>金立</option>
							<option value="android-lenovo"
								<c:if test="${channel=='android-lenovo' }">selected="selected"</c:if>>联想</option>
							<option value="android-vivo"
								<c:if test="${channel=='android-vivo' }">selected="selected"</c:if>>vivo</option>
							<option value="android-huawei"
								<c:if test="${channel=='android-huawei' }">selected="selected"</c:if>>华为</option>
							<option value="android-oppo"
								<c:if test="${channel=='android-oppo' }">selected="selected"</c:if>>oppo</option>
							<option value="android-dangle"
								<c:if test="${channel=='android-dangle' }">selected="selected"</c:if>>当乐</option>
							<option value="android-leshi"
								<c:if test="${channel=='android-leshi' }">selected="selected"</c:if>>乐视</option>
							<option value="android-muzhiwan"
								<c:if test="${channel=='android-muzhiwan' }">selected="selected"</c:if>>拇指玩</option>
							<option value="android-wandoujia"
								<c:if test="${channel=='android-wandoujia' }">selected="selected"</c:if>>豌豆荚</option>
							<option value="android-yingyonghui"
								<c:if test="${channel=='android-yingyonghui' }">selected="selected"</c:if>>应用汇</option>
							<option value="android-shouyoutianxia"
								<c:if test="${channel=='android-shouyoutianxia' }">selected="selected"</c:if>>手游天下</option>
							<option value="android-mumayi"
								<c:if test="${channel=='android-mumayi' }">selected="selected"</c:if>>木蚂蚁</option>
							<option value="android-youyoucun"
								<c:if test="${channel=='android-youyoucun' }">selected="selected"</c:if>>悠悠村</option>
							<option value="android-jidi"
								<c:if test="${channel=='android-jidi' }">selected="selected"</c:if>>基地</option>
							<option value="android-liantong"
								<c:if test="${channel=='android-jidi' }">selected="selected"</c:if>>联通</option>
					</select></td>
				</tr>
				<tr>
					<td>游戏名</td>
					<td><input type="text" id="gamename" placeHolder="请输入游戏名"
						value="lasthero"></td>
				</tr>
				<tr>
					<td>商品名</td>
					<td><input type="text" id="goodname" placeHolder="请输入商品名"
						value="模拟商品数据"></td>
				</tr>
				<tr>
					<td>支付日期</td>
					<td><input type="text" id="basicDate" placeHolder="2015-01-01"></td>
				</tr>
			</table>
			<div class="btn" onclick="addPay()">添加</div>
		</center>
	</div> --%>
</body>
</html>