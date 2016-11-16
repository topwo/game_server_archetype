<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <style type="text/css">
.csv {
	text-align: center;
	padding-top: 50px;
}

.csv ul li {
	list-style-type: none;
	margin: 10px;
	text-align: center;
}

.btn {
	width: 60%;
	height: 50px;
	line-height: 50px;
	background-color: lightblue;
}

.btn:hover {
	opacity: 0.8;
}

.csvdiv {
	display: inline-block;
	width: 80%;
	text-align: center;
}

.csvfile {
	margin: 5px;
	width: 29%;
	line-height: 50px;
	display: inline-block;
	float: left;
	height: 50px;
	padding: 5px;
	background-color: lightblue;
}

.csvfile a {
	text-decoration: none;
	font-family: "微软雅黑";
	color: black;
}

.csvfile:hover {
	opacity: 0.8;
}
</style> -->
<script type="text/javascript">
	function doUpdate() {
		$.ajax({
			url : '../csv/csvupdate',
			type : 'POST',
			dataType : 'text',
			success : function(data) {
				if (data == 'success') {
					alert('更新CSV文件成功');
				} else {
					alert('更新CSV文件失败');
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	}

	function doUpload() {
		var formData = new FormData($("#uploadForm")[0]);
		$('#uploadBtn').attr('disabled', 'disabled');
		$('#uploadBtn').val('正在上传...');
		$.ajax({
			url : '../csv/csvupload',
			type : 'POST',
			data : formData,
			dataType : 'text',
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				if (data == 'success') {
					alert('上传CSV文件成功');
					skip('../csv/csvlist');
				} else {
					alert('上传CSV文件失败');
					skip('../csv/csvlist');
				}
			},
			error : function(data) {
				alert('服务器错误');
				skip('../csv/csvlist');
			}
		});
	}

	function doDownload(name) {
		var url = "../csv/csvdownload?name=" + name + ".csv";
		window.open(url);
	}
</script>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-plus-square-o red"></i><strong>上传CSV文件</strong>
					</h2>
				</div>
				<div class="panel-body">
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						点击下方列表下载要修改的CSV文件，修改保存并上传（<span style="color: red">按照列表中的文件名上传</span>），然后点击更新CSV表到服务器生效
					</div>

					<div class="form-group">
						<div class="controls">
							<div id="dropzone">
								<form id="uploadForm" class="dropzone"
									enctype="multipart/form-data">
									指定CSV文件名： <input type="text" name="filename" value="" /> <input
										type='text' name='textfield' id='textfield' class='txt'
										disabled="disabled" placeHolder="选择的文件名" /> <input
										type='button' class='btn btn-success' value='浏览'
										onclick="fileField.click()" /> <input type="file" name="file"
										class="file" id="fileField" style="display: none" size="28"
										onchange="document.getElementById('textfield').value=this.value" />
									<input class="btn btn-success" type="button" id="uploadBtn"
										value="上传文件" onclick="doUpload()" /> <input
										class="btn btn-primary" type="button" onclick="doUpdate()"
										value="更新CSV表到服务器" />
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!--/col-->

	</div>
	<!--/row-->
	<c:set var="count" value="0" />
	<c:forEach items="${csvNames }" var="name">
		<c:set var="count" value="${count+1 }" />
		<c:if test="${(count+3)%4==0 }">
			<div class="row">
		</c:if>
		<div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
			<div class="smallstat green-bg">
				<i class="fa fa-cloud-download white-bg"
					onclick="doDownload('${name}')"></i><span class="title">${name }</span><span>${name }.csv</span>
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

	<%-- <div class="csv">
		<ul>
			<li>
				<form id="uploadForm">
					指定CSV文件名： <input type="text" name="filename" value="" />&nbsp;&nbsp;&nbsp;&nbsp;
					上传CSV文件：<input type="file" name="file" /> <input type="button"
						value="上传文件" onclick="doUpload()" />
				</form>
			</li>
			<li>
				<button class="btn" onclick="doUpdate()">更新CSV表到服务器</button>
			</li>
			<li>
				<p>
					点击下方列表下载要修改的CSV文件，修改保存并上传（<span style="color: red">按照列表中的文件名上传</span>），然后点击更新CSV表到服务器生效
				</p>
			</li>
			<li><div class="csvdiv">
					<c:forEach items="${csvNames }" var="name">
						<center>
							<div class="csvfile" onclick="doDownload('${name}')">
								<span>${name }.csv</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>${name }</span>
							</div>
						</center>
					</c:forEach>
				</div></li>
		</ul>
	</div> --%>
	<script type="text/javascript">
		$('span').each(function() {
			fileName = $(this).html();
			if (fileName == "Cangku") {
				$(this).html("仓库数据表");
			} else if (fileName == "Bingying") {
				$(this).html("兵营数据表");
			} else if (fileName == "KejiOpen") {
				$(this).html("科技开放表");
			} else if (fileName == "VipExp") {
				$(this).html("VIP经验表");
			} else if (fileName == "Zhaoshangju") {
				$(this).html("招商局数据表");
			} else if (fileName == "JunjichuUpgrade") {
				$(this).html("军机处升级表");
			} else if (fileName == "EquipExpReverse") {
				$(this).html("装备经验表（反）");
			} else if (fileName == "EquipExp") {
				$(this).html("装备经验表");
			} else if (fileName == "CardExp") {
				$(this).html("卡牌经验表");
			} else if (fileName == "JunZhuExp") {
				$(this).html("君主经验表");
			} else if (fileName == "Huangcheng") {
				$(this).html("皇城数据表");
			} else if (fileName == "JunjichuNonZx") {
				$(this).html("军机处非阵型效果表");
			} else if (fileName == "JunjichuZx") {
				$(this).html("军机处阵型效果表");
			} else if (fileName == "Equip") {
				$(this).html("装备表");
			} else if (fileName == "Prop") {
				$(this).html("物品表");
			} else if (fileName == "Pve") {
				$(this).html("普通关卡表");
			} else if (fileName == "Minju") {
				$(this).html("民居数据表");
			} else if (fileName == "Jiaochang") {
				$(this).html("校场数据表");
			} else if (fileName == "Card") {
				$(this).html("卡牌表");
			} else if (fileName == "Nongtian") {
				$(this).html("农田数据表");
			} else if (fileName == "KejiUpgrade") {
				$(this).html("科技升级表");
			} else if (fileName == "DailyTask") {
				$(this).html("每日任务表");
			} else if (fileName == "MainTaskBuilding") {
				$(this).html("主线建筑升级表");
			} else if (fileName == "MainTaskCard") {
				$(this).html("主线卡牌任务表");
			} else if (fileName == "MainTaskLevel") {
				$(this).html("主线升级任务表");
			} else if (fileName == "MainTaskPve") {
				$(this).html("主线PVE任务表");
			} else if (fileName == "GreenCard") {
				$(this).html("绿卡卡库表");
			} else if (fileName == "BlueCard") {
				$(this).html("蓝卡卡库表");
			} else if (fileName == "LowPurpleCard") {
				$(this).html("低级紫卡表");
			} else if (fileName == "HighPurpleCard") {
				$(this).html("高级紫卡表");
			} else if (fileName == "LowYellowCard") {
				$(this).html("低级橙卡表");
			} else if (fileName == "MiddleYellowCard") {
				$(this).html("中级橙卡表");
			} else if (fileName == "HighYellowCard") {
				$(this).html("高级橙卡表");
			} else if (fileName == "SuperYellowCard") {
				$(this).html("超级橙卡表");
			} else if (fileName == "LowProp") {
				$(this).html("低级道具包表");
			} else if (fileName == "MiddleProp") {
				$(this).html("中级道具包表");
			} else if (fileName == "HighProp") {
				$(this).html("高级道具包表");
			} else if (fileName == "PickCardProperty") {
				$(this).html("抽卡概率表");
			} else if (fileName == "CardLevel") {
				$(this).html("卡牌等级与攻击表");
			} else if (fileName == "PkAward") {
				$(this).html("竞技场奖励表");
			} else if (fileName == "PveExcellent") {
				$(this).html("精英关卡表");
			} else if (fileName == "RobotName") {
				$(this).html("竞技场机器人表");
			} else if (fileName == "Tiejiangpu") {
				$(this).html("铁匠铺表");
			} else if (fileName == "PkDailyAward") {
				$(this).html("竞技场日常奖励表");
			} else if (fileName == "EquipUpLevelBase") {
				$(this).html("装备强化效果基础表");
			} else if (fileName == "EquipUpLevelXishu") {
				$(this).html("装备强化系数表");
			} else if (fileName == "ChengChiName") {
				$(this).html("城池名字表");
			} else if (fileName == "ChengChi") {
				$(this).html("城池链接表");
			} else if (fileName == "CountryPve") {
				$(this).html("国战PVE");
			} else if (fileName == "CardStarCostCoin") {
				$(this).html("卡牌升星银两消耗");
			} else if (fileName == "CardStarCostYuanbao") {
				$(this).html("卡牌升星元宝消耗");
			} else if (fileName == "CardDecompose") {
				$(this).html("卡牌分解将魂");
			} else if (fileName == "PkPickCard") {
				$(this).html("竞技场抽卡卡包");
			} else if (fileName == "PkPickItem") {
				$(this).html("竞技场抽卡道具包");
			} else if (fileName == "VipAward") {
				$(this).html("Vip礼包");
			} else if (fileName == "CardDecomposeCoin") {
				$(this).html("卡牌分解银两");
			} else if (fileName == "PveSecondCoin") {
				$(this).html("日常副本银两表");
			} else if (fileName == "EquipStarEffect") {
				$(this).html("装备升星效果");
			} else if (fileName == "BattlePickCard") {
				$(this).html("竞技场抽卡卡库");
			} else if (fileName == "PkBattleAward") {
				$(this).html("竞技场奖励表");
			} else if (fileName == "CountryNpc") {
				$(this).html("国战机器人");
			} else if (fileName == "PveSecondExp") {
				$(this).html("日常副本经验表");
			} else if (fileName == "PveSecondJungong") {
				$(this).html("日常副本军功表");
			} else if (fileName == "PveExcellentBox") {
				$(this).html("精英关卡宝箱");
			} else if (fileName == "BattleShowCard") {
				$(this).html("竞技场展示卡库");
			} else if (fileName == "EquipUpLevelBaseCost") {
				$(this).html("装备强化消耗基础表");
			} else if (fileName == "CardStar") {
				$(this).html("卡牌星级基础表");
			} else if (fileName == "PveBox") {
				$(this).html("普通关卡宝箱");
			} else if (fileName == "PveSecondFood") {
				$(this).html("日常副本粮食表");
			}
		});
	</script>
</body>
</html>