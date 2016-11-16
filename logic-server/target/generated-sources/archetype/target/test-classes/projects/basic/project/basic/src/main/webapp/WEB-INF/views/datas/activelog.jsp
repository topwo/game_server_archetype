<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志</title>
</head>
<body>

	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-retweet red"></i><strong>每日活跃分布图</strong>
					</h2>
					<div class="panel-actions">
						<a href="#" class="btn-close"
							onclick="javascript:skip('../datas/activelog')"><i
							class="fa fa-refresh"></i></a> <a href="#" class="btn-close"
							onclick="history.go(0)"><i class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
					<div id="main" style="height: 400px"></div>
					<!-- ECharts单文件引入 -->
					<script type="text/javascript">
						var myChart = echarts.init(document
								.getElementById('main'), 'dark');
						// loading
						myChart.showLoading();
						$
								.ajax({
									type : "GET",
									dataType : "text",
									url : "../datas/activelogHandle",
									success : function(data) {
										var ret = JSON.parse(data);
										myChart.hideLoading();
										var option = {
											tooltip : {
												trigger : 'item'
											},
											toolbox : {
												show : true,
												feature : {
													mark : {
														show : true
													},
													dataView : {
														show : true,
														readOnly : false
													},
													dataZoom : {
														show : true,
													},
													magicType : {
														show : true,
														type : [ 'line', 'bar' ]
													},
													restore : {
														show : true
													},
													saveAsImage : {
														show : true
													}
												}
											},
											calculable : true,
											grid : {
												top : '12%',
												left : '1%',
												right : '10%',
												containLabel : true
											},
											legend : {
												data : [ '每日活跃分布' ]
											},
											xAxis : [ {
												name : '日期',
												type : 'category',
												data : ret.dateCharts
											} ],
											yAxis : [ {
												name : '人数',
												type : 'value'
											} ],
											dataZoom : {
												type : 'inside',
												show : true,
												realtime : true,
												y : 36,
												height : 20,
												backgroundColor : 'rgba(221,160,221,0.5)',
												dataBackgroundColor : 'rgba(138,43,226,0.5)',
												fillerColor : 'rgba(38,143,26,0.6)',
												handleColor : 'rgba(128,43,16,0.8)',
												start : 20,
												end : 80
											},
											series : [ {
												"name" : "每日活跃分布",
												"type" : "bar",
												"data" : ret.countCharts
											} ]
										};
										// 为echarts对象加载数据 
										myChart.setOption(option);
									},
								});
					</script>
				</div>
			</div>

		</div>
		<!--/col-->

	</div>
	<!--/row-->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-table red"></i><span class="break"></span><strong>每日活跃分布表</strong>
					</h2>
				</div>
				<div class="panel-body">
					<table
						class="table table-bordered table-striped table-condensed table-hover">
						<thead>
							<tr>
								<th>日期</th>
								<th>活跃玩家数</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(logs)==0 }">
									<tr>
										<th colspan="2">暂无每日活跃数据</th>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${logs }" var="log">
										<tr>
											<td>${log[0] }</td>
											<td>${log[1] }</td>
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