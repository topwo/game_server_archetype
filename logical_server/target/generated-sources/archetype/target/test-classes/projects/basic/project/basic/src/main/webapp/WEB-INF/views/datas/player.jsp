<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>玩家数据</title>
<script type="text/javascript">
	function junZhuUpdate() {
		var userid = $('#userid').val();
		var name = $('#name').val();
		var headImg = $('#headImg').val();
		var role = $('#role').val();
		var country = $('#country').val();
		var exp = $('#exp').val();
		var vipExp = $('#vipExp').val();
		var paySum = $('#paySum').val();
		var conduction = $('#conduction').val();
		var skillPoint = $('#skillPoint').val();
		var junling = $('#junling').val();
		var coin = $('#coin').val();
		var yuanbao = $('#yuanbao').val();
		var food = $('#food').val();
		var iron = $('#iron').val();
		var wood = $('#wood').val();
		var soldierNum = $('#soldierNum').val();
		var jungongNum = $('#jungongNum').val();
		var jianghunNum = $('#jianghunNum').val();
		var shengwang = $('#shengwang').val();
		var zxId = $('#zxId').val();
		var junjichu = $('#junjichu').val();
		var huangcheng = $('#huangcheng').val();
		var zhaoshangju = $('#zhaoshangju').val();
		var jiaochang = $('#jiaochang').val();
		var cangku = $('#cangku').val();
		var tiejiangpu = $('#tiejiangpu').val();
		var bingying = $('#bingying').val();
		var firstRecharge = $('#firstRecharge').val();
		var commonVip = $('#commonVip').val();
		var superVip = $('#superVip').val();
		var minju1 = $('#minju1').val();
		var minju2 = $('#minju2').val();
		var minju3 = $('#minju3').val();
		var minju4 = $('#minju4').val();
		var minju5 = $('#minju5').val();
		var minju6 = $('#minju6').val();
		var nongtian1 = $('#nongtian1').val();
		var nongtian2 = $('#nongtian2').val();
		var nongtian3 = $('#nongtian3').val();
		var nongtian4 = $('#nongtian4').val();
		var nongtian5 = $('#nongtian5').val();
		var nongtian6 = $('#nongtian6').val();
		if (name == '') {
			alert('请输入名字');
			return;
		}
		if (headImg == '') {
			alert('请输入头像');
			return;
		}
		if (role == '') {
			alert('请输入角色id');
			return;
		}
		if (country == '') {
			alert('请输入国家');
			return;
		}
		if (exp == '') {
			exp = 0;
		}
		if (vipExp == '') {
			vipExp = 0;
		}
		if (conduction == '') {
			alert('请输入引导步骤');
			return;
		}
		if (paySum == '') {
			alert('请输入累计充值');
			return;
		}
		if (skillPoint == '') {
			alert('请输入技能点数');
			return;
		}
		if (junling == '') {
			alert('请输入军令数');
			return;
		}
		if (coin == '') {
			alert('请输入银两');
			return;
		}
		if (yuanbao == '') {
			alert('请输入元宝');
			return;
		}
		if (food == '') {
			alert('请输入粮食');
			return;
		}
		if (iron == '') {
			alert('请输入精铁');
			return;
		}
		if (wood == '') {
			alert('请输入木材');
			return;
		}
		if (soldierNum == '') {
			alert('请输入兵力');
			return;
		}
		if (jungongNum == '') {
			alert('请输入军功数');
			return;
		}
		if (jianghunNum == '') {
			alert('请输入将魂数');
			return;
		}
		if (shengwang == '') {
			alert('请输入声望');
			return;
		}
		if (zxId == '') {
			alert('请输入阵型');
			return;
		}
		if (junjichu == '') {
			alert('请输入军机处等级');
			return;
		}
		if (junjichu == '') {
			alert('请输入皇城等级');
			return;
		}
		if (zhaoshangju == '') {
			alert('请输入招商局等级');
			return;
		}
		if (jiaochang == '') {
			alert('请输入校场等级');
			return;
		}
		if (cangku == '') {
			alert('请输入仓库等级');
			return;
		}
		if (tiejiangpu == '') {
			alert('请输入铁匠铺等级');
			return;
		}
		if (bingying == '') {
			alert('请输入兵营等级');
			return;
		}
		if (minju1 == '') {
			alert('请输入民居1等级');
			return;
		}
		if (minju2 == '') {
			alert('请输入民居2等级');
			return;
		}
		if (minju3 == '') {
			alert('请输入民居3等级');
			return;
		}
		if (minju4 == '') {
			alert('请输入民居4等级');
			return;
		}
		if (minju5 == '') {
			alert('请输入民居5等级');
			return;
		}
		if (minju6 == '') {
			alert('请输入民居6等级');
			return;
		}
		if (nongtian1 == '') {
			alert('请输入农田1等级');
			return;
		}
		if (nongtian2 == '') {
			alert('请输入农田2等级');
			return;
		}
		if (nongtian3 == '') {
			alert('请输入农田3等级');
			return;
		}
		if (nongtian4 == '') {
			alert('请输入农田4等级');
			return;
		}
		if (nongtian5 == '') {
			alert('请输入农田5等级');
			return;
		}
		if (nongtian6 == '') {
			alert('请输入农田6等级');
			return;
		}
		var datas = {
			"userid" : userid,
			"name" : name,
			"headImg" : headImg,
			"role" : role,
			"country" : country,
			"exp" : exp,
			"vipExp" : vipExp,
			"conduction" : conduction,
			"paySum" : paySum,
			"skillPoint" : skillPoint,
			"junling" : junling,
			"coin" : coin,
			"yuanbao" : yuanbao,
			"food" : food,
			"iron" : iron,
			"wood" : wood,
			"soldierNum" : soldierNum,
			"jungongNum" : jungongNum,
			"jianghunNum" : jianghunNum,
			"shengwang" : shengwang,
			"firstRecharge" : firstRecharge,
			"commonVip" : commonVip,
			"superVip" : superVip,
			"zxId" : zxId,
			"junjichu" : junjichu,
			"zhaoshangju" : zhaoshangju,
			"jiaochang" : jiaochang,
			"cangku" : cangku,
			"bingying" : bingying,
			"huangcheng" : huangcheng,
			"tiejiangpu" : tiejiangpu,
			"minju1" : minju1,
			"minju2" : minju2,
			"minju3" : minju3,
			"minju4" : minju4,
			"minju5" : minju5,
			"minju6" : minju6,
			"nongtian1" : nongtian1,
			"nongtian2" : nongtian2,
			"nongtian3" : nongtian3,
			"nongtian4" : nongtian4,
			"nongtian5" : nongtian5,
			"nongtian6" : nongtian6
		}
		$.ajax({
			type : "get",
			dataType : "text",
			data : datas,
			url : "../datas/updateJunZhu",
			success : function(data) {
				if (data == "success") {
					skip("../datas/player?userID=" + userid);
					alert("保存成功");
				} else {
					alert("保存失败");
					return;
				}
			}
		});
	}
</script>
</head>
<body class="bodystyle">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>其他数据</strong>
					</h2>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/cardlist?userID=${userID}')">卡牌信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/equiplist?userID=${userID}')">装备信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/baglist?userID=${userID}')">背包信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/pvelist?userID=${userID}')">普通关卡信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/pveexcellentlist?userID=${userID}')">精英关卡信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/workerlist?userID=${userID}')">工人队列</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/trainlist?userID=${userID}')">训练队列</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/teclist?userID=${userID}')">科技信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/tasklist?userID=${userID}')">任务信息</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/yuanbaolist?userID=${userID}')">元宝记录</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="skip('../datas/operatelist?userID=${userID}')">操作记录</button>
					<c:if test="${pkOpen==true }">
						<button type="button" class="btn btn-sm btn-primary"
							onclick="skip('../datas/pkinfo?userID=${userID}')">竞技场</button>
					</c:if>
					<c:if test="${pveSecondOpen==true }">
						<button type="button" class="btn btn-sm btn-primary"
							onclick="skip('../datas/pvesecondlist?userID=${userID}')">日常副本</button>
					</c:if>
					<c:if test="${countryOpen==true }">
						<button type="button" class="btn btn-sm btn-primary"
							onclick="skip('../datas/countryinfo?userID=${userID}')">国战信息</button>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>君主信息(UID:${junZhu.id })<input
							id="userid" type="hidden" value="${junZhu.id }" /></strong>
					</h2>
					<div class="panel-actions">
						<a href="#" class="btn-close"
							onclick="javascript:skip('../datas/player?userID=${userID}')"><i
							class="fa fa-refresh"></i></a> <a href="#" class="btn-close"
							onclick="history.go(0)"><i class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<form action="" method="post" class="form-horizontal ">
						<div class="form-group">
							<label class="col-md-3 control-label">君主战力</label>
							<div class="col-md-9">
								<input type="number" value="${power }" name="text-input"
									class="form-control" disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">名字</label>
							<div class="col-md-9">
								<input id="name" placeholder="名字" type="text"
									value="${junZhu.name }" name="text-input" class="form-control"
									placeholder="君主名字" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">头像</label>
							<div class="col-md-9">
								<select id="headImg" name="select" class="form-control" size="1">
									<option value="1"
										<c:if test="${junZhu.headImg==1 }">selected="selected"</c:if>>头像1</option>
									<option value="2"
										<c:if test="${junZhu.headImg==2 }">selected="selected"</c:if>>头像2</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">角色</label>
							<div class="col-md-9">
								<select id="role" name="select" class="form-control" size="1">
									<option value="1"
										<c:if test="${junZhu.role==1 }">selected="selected"</c:if>>人物1</option>
									<option value="2"
										<c:if test="${junZhu.role==2 }">selected="selected"</c:if>>人物2</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">国家</label>
							<div class="col-md-9">
								<select id="country" name="select" class="form-control" size="1">
									<option value="1"
										<c:if test="${junZhu.country==1 }">selected="selected"</c:if>>蜀国</option>
									<option value="2"
										<c:if test="${junZhu.country==2 }">selected="selected"</c:if>>魏国</option>
									<option value="3"
										<c:if test="${junZhu.country==3 }">selected="selected"</c:if>>吴国</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">君主等级</label>
							<div class="col-md-9">
								<input placeholder="君主等级" type="number" value="${junZhu.level }"
									name="text-input" class="form-control" disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">君主经验</label>
							<div class="col-md-9">
								<input type="number" class="form-control" id="exp"
									placeholder="君主经验" value="0" /> <span class="help-block">${junZhu.exp }/
									<c:choose>
										<c:when test="${sumExp==-1 }">已满级</c:when>
										<c:otherwise>${sumExp }</c:otherwise>
									</c:choose>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">VIP等级</label>
							<div class="col-md-9">
								<input placeholder="名字" type="number" value="${junZhu.vip }"
									name="text-input" class="form-control" disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">VIP经验</label>
							<div class="col-md-9">
								<input type="number" id="vipExp" placeholder="VIP经验"
									class="form-control" value="0" /><span class="help-block">
									${junZhu.vipExp }/ <c:choose>
										<c:when test="${vipSumExp==-1 }">已满级</c:when>
										<c:otherwise>${vipSumExp }</c:otherwise>
									</c:choose>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">累计充值</label>
							<div class="col-md-9">
								<input placeholder="累计充值" type="number" id="paySum"
									value="${junZhu.paySum }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">引导步骤</label>
							<div class="col-md-9">
								<input placeholder="引导步骤" type="number" id="conduction"
									value="${junZhu.conduction }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">技能点</label>
							<div class="col-md-9">
								<input placeholder="技能点" type="number" id="skillPoint"
									value="${junZhu.skillPoint }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">军令</label>
							<div class="col-md-9">
								<input placeholder="军令" type="number" id="junling"
									value="${junZhu.junling }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">银两</label>
							<div class="col-md-9">
								<input placeholder="银两" type="number" id="coin"
									value="${junZhu.coin }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">元宝</label>
							<div class="col-md-9">
								<input placeholder="元宝" type="number" id="yuanbao"
									value="${junZhu.yuanbao }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">粮食</label>
							<div class="col-md-9">
								<input placeholder="粮食" type="number" id="food"
									value="${junZhu.food }" name="text-input" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">兵力</label>
							<div class="col-md-9">
								<input placeholder="兵力" type="number" id="soldierNum"
									value="${junZhu.soldierNum }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">军功</label>
							<div class="col-md-9">
								<input placeholder="军功" type="number" id="jungongNum"
									value="${junZhu.jungongNum }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">将魂</label>
							<div class="col-md-9">
								<input placeholder="将魂" type="number" id=jianghunNum
									value="${junZhu.jianghunNum }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">声望</label>
							<div class="col-md-9">
								<input placeholder="声望" type="number" id=shengwang
									value="${junZhu.shengwang }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">首冲</label>
							<div class="col-md-9">
								<select id="firstRecharge" name="select" class="form-control"
									size="1">
									<option
										<c:if test="${signInfo.hasFirstRecharge==1 }">selected="selected"</c:if>
										value="1">是</option>
									<option
										<c:if test="${signInfo.hasFirstRecharge==0 }">selected="selected"</c:if>
										value="0">否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">普通会员</label>
							<div class="col-md-9">
								<select id="commonVip" name="select" class="form-control"
									size="1">
									<option
										<c:if test="${signInfo.commonVip==1 }">selected="selected"</c:if>
										value="1">是</option>
									<option
										<c:if test="${signInfo.commonVip==0 }">selected="selected"</c:if>
										value="0">否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="text-input">
								超级会员</label>
							<div class="col-md-9">
								<select id="superVip" name="select" class="form-control"
									size="1">
									<option
										<c:if test="${signInfo.superVip==1 }">selected="selected"</c:if>
										value="1">是</option>
									<option
										<c:if test="${signInfo.superVip==0 }">selected="selected"</c:if>
										value="0">否</option>
								</select>
							</div>
						</div>
						<br>
					</form>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-success btn-lg btn-block"
						onclick="junZhuUpdate()">
						<i class="fa fa-dot-circle-o"></i> 修改
					</button>
					<!-- <button type="reset" class="btn btn-sm btn-danger">
						<i class="fa fa-ban"></i> Reset
					</button> -->
				</div>
			</div>
		</div>
		<!--/.col-->

		<!-- 建筑信息-->
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>建筑信息</strong>
					</h2>
					<div class="panel-actions">
						<a href="#" class="btn-close"
							onclick="javascript:skip('../datas/player?userID=${userID}')"><i
							class="fa fa-refresh"></i></a> <a href="#" class="btn-close"
							onclick="history.go(0)"><i class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<form action="" method="post" enctype="multipart/form-data"
						class="form-horizontal ">
						<div class="form-group">
							<label class="col-md-3 control-label">军机处</label>
							<div class="col-md-9">
								<input id="junjichu" placeholder="军机处" type="text"
									value="${building.junjichu }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">皇城</label>
							<div class="col-md-9">
								<input id="huangcheng" placeholder="皇城" type="text"
									value="${building.huangcheng }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">招商局</label>
							<div class="col-md-9">
								<input id="zhaoshangju" placeholder="招商局" type="text"
									value="${building.zhaoshangju }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">校场</label>
							<div class="col-md-9">
								<input id="jiaochang" placeholder="校场" type="text"
									value="${building.jiaochang }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">仓库</label>
							<div class="col-md-9">
								<input id="cangku" placeholder="仓库" type="text"
									value="${building.cangku }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">兵营</label>
							<div class="col-md-9">
								<input id="bingying" placeholder="兵营" type="text"
									value="${building.bingying }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">铁匠铺</label>
							<div class="col-md-9">
								<input id="tiejiangpu" placeholder="铁匠铺" type="text"
									value="${building.tiejiangpu }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居1</label>
							<div class="col-md-9">
								<input id="minju1" placeholder="民居1" type="text"
									value="${building.minju1 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居2</label>
							<div class="col-md-9">
								<input id="minju2" placeholder="民居2" type="text"
									value="${building.minju2 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居3</label>
							<div class="col-md-9">
								<input id="minju3" placeholder="民居3" type="text"
									value="${building.minju3 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居4</label>
							<div class="col-md-9">
								<input id="minju4" placeholder="民居4" type="text"
									value="${building.minju4 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居5</label>
							<div class="col-md-9">
								<input id="minju5" placeholder="民居5" type="text"
									value="${building.minju5 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">民居6</label>
							<div class="col-md-9">
								<input id="minju6" placeholder="民居6" type="text"
									value="${building.minju6 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田1</label>
							<div class="col-md-9">
								<input id="nongtian1" placeholder="农田1" type="text"
									value="${building.nongtian1 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田2</label>
							<div class="col-md-9">
								<input id="nongtian2" placeholder="农田2" type="text"
									value="${building.nongtian2 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田3</label>
							<div class="col-md-9">
								<input id="nongtian3" placeholder="农田3" type="text"
									value="${building.nongtian3 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田4</label>
							<div class="col-md-9">
								<input id="nongtian4" placeholder="农田4" type="text"
									value="${building.nongtian4 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田5</label>
							<div class="col-md-9">
								<input id="nongtian5" placeholder="农田5" type="text"
									value="${building.nongtian5 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">农田6</label>
							<div class="col-md-9">
								<input id="nongtian6" placeholder="农田6" type="text"
									value="${building.nongtian6 }" name="text-input"
									class="form-control" />
							</div>
						</div>
						<br>
					</form>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-success btn-lg btn-block"
						onclick="junZhuUpdate()">
						<i class="fa fa-dot-circle-o"></i> 修改
					</button>
					<!-- <button type="reset" class="btn btn-sm btn-danger">
						<i class="fa fa-ban"></i> Reset
					</button> -->
				</div>
			</div>
		</div>
		<!--/.col-->
		<!--/.row-->
	</div>

	<%-- 	<div class="player">
		<table>
			<tr>
				<td colspan="2"><h2>君主信息(UID:${junZhu.id })</h2></td>
			</tr>
			<tr>
				<th>名字</th>
				<td><input id="userid" type="hidden" value="${junZhu.id }" /><input
					id="name" placeholder="名字" value="${junZhu.name }" /></td>
			</tr>
			<tr>
				<th>头像</th>
				<td><select id="headImg">
						<option value="1"
							<c:if test="${junZhu.headImg==1 }">selected="selected"</c:if>>头像1</option>
						<option value="2"
							<c:if test="${junZhu.headImg==2 }">selected="selected"</c:if>>头像2</option>
				</select></td>
			</tr>
			<tr>
				<th>角色</th>
				<td><select id="role">
						<option value="1"
							<c:if test="${junZhu.role==1 }">selected="selected"</c:if>>人物1</option>
						<option value="2"
							<c:if test="${junZhu.role==2 }">selected="selected"</c:if>>人物2</option>
				</select></td>
			</tr>
			<tr>
				<th>国家</th>
				<td><select id="country">
						<option value="1"
							<c:if test="${junZhu.country==1 }">selected="selected"</c:if>>蜀国</option>
						<option value="2"
							<c:if test="${junZhu.country==2 }">selected="selected"</c:if>>魏国</option>
						<option value="3"
							<c:if test="${junZhu.country==3 }">selected="selected"</c:if>>吴国</option>
				</select></td>
			</tr>
			<tr>
				<th>君主等级</th>
				<td><input type="number" value="${junZhu.level }"
					disabled="disabled" /></td>
			</tr>
			<tr>
				<th>君主经验</th>
				<td><input class="exp" type="number" id="exp"
					placeholder="君主经验" value="0" /> ${junZhu.exp }/<c:choose>
						<c:when test="${sumExp==-1 }">已满级</c:when>
						<c:otherwise>${sumExp }</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>VIP等级</th>
				<td><input type="number" value="${junZhu.vip }"
					disabled="disabled" /></td>
			</tr>
			<tr>
				<th>VIP经验</th>
				<td><input class="exp" type="number" id="vipExp"
					placeholder="VIP经验" value="0" /> ${junZhu.vipExp }/<c:choose>
						<c:when test="${vipSumExp==-1 }">已满级</c:when>
						<c:otherwise>${vipSumExp }</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>军令</th>
				<td><input type="number" id="junling" placeholder="军令"
					value="${junZhu.junling}" /></td>
			</tr>
			<tr>
				<th>银两</th>
				<td><input type="number" id="coin" placeholder="银两"
					value="${junZhu.coin}" /></td>
			</tr>
			<tr>
				<th>元宝</th>
				<td><input type="number" id="yuanbao" placeholder="元宝"
					value="${junZhu.yuanbao}" /></td>
			</tr>
			<tr>
				<th>粮食</th>
				<td><input type="number" id="food" placeholder="粮食"
					value="${junZhu.food}" /></td>
			</tr>
			<tr>
				<th>精铁</th>
				<td><input type="number" id="iron" placeholder="精铁"
					value="${junZhu.iron}" /></td>
			</tr>
			<tr>
				<th>木材</th>
				<td><input type="number" id="wood" placeholder="木材"
					value="${junZhu.wood}" /></td>
			</tr>
			<tr>
				<th>兵力</th>
				<td><input type="number" id="soldierNum" placeholder="兵力"
					value="${junZhu.soldierNum}" /></td>
			</tr>
			<tr>
				<th>军功</th>
				<td><input type="number" id="jungongNum" placeholder="军功"
					value="${junZhu.jungongNum}" /></td>
			</tr>
			<tr>
				<th>将魂</th>
				<td><input type="number" id="jianghunNum" placeholder="将魂"
					value="${junZhu.jianghunNum}" /></td>
			</tr>
			<tr>
				<th>声望</th>
				<td><input type="number" id="shengwang" placeholder="声望"
					value="${junZhu.shengwang}" /></td>
			</tr>
			<tr>
				<th>首冲</th>
				<td><select id="firstRecharge">
						<option
							<c:if test="${signInfo.hasFirstRecharge==1 }">selected="selected"</c:if>
							value="1">是</option>
						<option
							<c:if test="${signInfo.hasFirstRecharge==0 }">selected="selected"</c:if>
							value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<th>普通会员</th>
				<td><select id="commonVip">
						<option
							<c:if test="${signInfo.commonVip==1 }">selected="selected"</c:if>
							value="1">是</option>
						<option
							<c:if test="${signInfo.commonVip==0 }">selected="selected"</c:if>
							value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<th>超级会员</th>
				<td><select id="superVip">
						<option
							<c:if test="${signInfo.superVip==1 }">selected="selected"</c:if>
							value="1">是</option>
						<option
							<c:if test="${signInfo.superVip==0 }">selected="selected"</c:if>
							value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<td>
					<div class="col-xs-6">
						<button type="button" onclick="junZhuUpdate()"
							class="btn btn-success btn-block">保存</button>
					</div> <!--/col-->
				</td>
			</tr>
		</table>
		<div class="building">
			<table>
				<tr>
					<th>军机处</th>
					<td><input type="number" id="junjichu" placeholder="军机处"
						value="${building.junjichu}" /></td>
				</tr>
				<tr>
					<th>皇城</th>
					<td><input type="number" id="huangcheng" placeholder="皇城"
						value="${building.huangcheng}" /></td>
				</tr>
				<tr>
					<th>招商局</th>
					<td><input type="number" id="zhaoshangju" placeholder="招商局"
						value="${building.zhaoshangju}" /></td>
				</tr>
				<tr>
					<th>校场</th>
					<td><input type="number" id="jiaochang" placeholder="校场"
						value="${building.jiaochang}" /></td>
				</tr>
				<tr>
					<th>仓库</th>
					<td><input type="number" id="cangku" placeholder="仓库"
						value="${building.cangku}" /></td>
				</tr>
				<tr>
					<th>兵营</th>
					<td><input type="number" id="bingying" placeholder="兵营"
						value="${building.bingying}" /></td>
				</tr>
				<tr>
					<th>铁匠铺</th>
					<td><input type="number" id="tiejiangpu" placeholder="铁匠铺"
						value="${building.tiejiangpu}" /></td>
				</tr>
				<tr>
					<th>民居1</th>
					<td><input type="number" id="minju1" placeholder="民居1"
						value="${building.minju1}" /></td>
				</tr>
				<tr>
					<th>民居2</th>
					<td><input type="number" id="minju2" placeholder="民居2"
						value="${building.minju2}" /></td>
				</tr>
				<tr>
					<th>民居3</th>
					<td><input type="number" id="minju3" placeholder="民居3"
						value="${building.minju3}" /></td>
				</tr>
				<tr>
					<th>民居4</th>
					<td><input type="number" id="minju3" placeholder="民居4"
						value="${building.minju4}" /></td>
				</tr>
				<tr>
					<th>民居5</th>
					<td><input type="number" id="minju5" placeholder="民居5"
						value="${building.minju5}" /></td>
				</tr>
				<tr>
					<th>民居6</th>
					<td><input type="number" id="minju6" placeholder="民居6"
						value="${building.minju6}" /></td>
				</tr>
				<tr>
					<th>农田1</th>
					<td><input type="number" id="nongtian1" placeholder="农田1"
						value="${building.nongtian1}" /></td>
				</tr>
				<tr>
					<th>农田2</th>
					<td><input type="number" id="nongtian2" placeholder="农田2"
						value="${building.nongtian2}" /></td>
				</tr>
				<tr>
					<th>农田3</th>
					<td><input type="number" id="nongtian3" placeholder="农田3"
						value="${building.nongtian3}" /></td>
				</tr>
				<tr>
					<th>农田4</th>
					<td><input type="number" id="nongtian4" placeholder="农田4"
						value="${building.nongtian4}" /></td>
				</tr>
				<tr>
					<th>农田5</th>
					<td><input type="number" id="nongtian5" placeholder="农田5"
						value="${building.nongtian5}" /></td>
				</tr>
				<tr>
					<th>农田6</th>
					<td><input type="number" id="nongtian6" placeholder="农田6"
						value="${building.nongtian6}" /></td>
				</tr>
			</table>
		</div>
		<ul>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/cardlist?userID=${userID}')">卡牌信息</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/equiplist?userID=${userID}')">装备信息</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/baglist?userID=${userID}')">背包信息</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/pvelist?userID=${userID}')">关卡信息</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/workerlist?userID=${userID}')">工人队列</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/trainlist?userID=${userID}')">训练队列</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/teclist?userID=${userID}')">科技信息</a>
				</h3>
			</li>
			<li>
				<h3>
					<a href="#" onclick="skip('../datas/tasklist?userID=${userID}')">任务信息</a>
				</h3>
			</li>
			<c:if test="${pkOpen==true }">
				<li>
					<h3>
						<a href="#" onclick="skip('../datas/pkinfo?userID=${userID}')">竞技场</a>
					</h3>
				</li>
			</c:if>
			<c:if test="${countryOpen==true }">
				<li>
					<h3>
						<a href="#"
							onclick="skip('../datas/countryinfo?userID=${userID}')">国战信息</a>
					</h3>
				</li>
			</c:if>
		</ul>
	</div> --%>
</body>
</html>