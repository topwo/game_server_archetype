<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支付列表</title>
    <!-- <link rel="stylesheet" type="text/css" href="../css/page.css" /> -->
    <script type="text/javascript">
        function getWhere() {
            var where = "";
            var orderId = $('#orderId').val();
            var billno = $('#billno').val();
            var userId = $('#userId').val();
            if (orderId != '') {
                where += "&orderId=" + orderId + "";
            }
            if (billno != '') {
                where += "&billno=" + billno + "";
            }
            if (userId != '') {
                where += "&userId=" + userId + "";
            }
            return where;
        }

        function search(page) {
            $('#searchBtn').html('搜索中...');
            skip("../basicmgr/basiclist?" + getWhere());
        }
    </script>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>
                    <i class="fa fa-indent red"></i><strong>订单查询条件</strong>
                </h2>
            </div>
            <div class="panel-body">
                <form action="" method="post" class="form-horizontal ">
                    <div class="form-group">
                        <label class="col-md-3 control-label">游戏服订单号</label>
                        <div class="col-md-9">
                            <input type="number" name="billno" id="billno"
                                   value="${billno }" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">联运订单号</label>
                        <div class="col-md-9">
                            <input type="text" name="orderId" id="orderId"
                                   value="${orderId }" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">玩家ID</label>
                        <div class="col-md-9">
                            <input type="number" name="userId" id="userId"
                                   value="${userId }" class="form-control"/>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="panel-footer">
                <button id="searchBtn" type="button"
                        class="btn btn-success btn-lg btn-block"
                        onclick="search(${pageNo})">
                    <i class="fa fa-dot-circle-o"></i> 搜索
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

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>
                    <i class="fa fa-table red"></i><span class="break"></span><strong>支付订单</strong>
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
                        <th>游戏服订单号</th>
                        <th>平台账号id</th>
                        <th>玩家id</th>
                        <th>厂商订单号</th>
                        <th>充值金额（元）</th>
                        <th>渠道</th>
                        <th>游戏名</th>
                        <th>商品名</th>
                        <th>支付日期</th>
                        <th>完成状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${fn:length(basicList)==0 }">
                            <tr>
                                <th colspan="10">
                                    <center>暂无充值记录</center>
                                </th>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${basicList }" var="basic">
                                <tr>
                                    <td>${basic.billno }</td>
                                    <td>${basic.account }</td>
                                    <td>${basic.userid }</td>
                                    <td>${basic.orderid }</td>
                                    <td>${basic.amount }</td>
                                    <td><c:choose>
                                        <c:when test="${basic.channel=='ios-xy' }">xy</c:when>
                                        <c:otherwise>${basic.channel }</c:otherwise>
                                    </c:choose></td>
                                    <td>${basic.gamename }</td>
                                    <td><c:choose>
                                        <c:when test="${basic.goodType==1 }">首冲</c:when>
                                        <c:when test="${basic.goodType==2 }">普通会员</c:when>
                                        <c:when test="${basic.goodType==3 }">超级会员</c:when>
                                        <c:when test="${basic.goodType==4 }">豪华签到</c:when>
                                        <c:when test="${basic.goodType==5 }">购买元宝14888</c:when>
                                        <c:when test="${basic.goodType==6 }">购买元宝6888</c:when>
                                        <c:when test="${basic.goodType==7 }">购买元宝3888</c:when>
                                        <c:when test="${basic.goodType==8 }">购买元宝1888</c:when>
                                        <c:when test="${basic.goodType==9 }">购买元宝1088</c:when>
                                        <c:when test="${basic.goodType==10 }">购买元宝388</c:when>
                                        <c:when test="${basic.goodType==11 }">普通玩家每日折扣</c:when>
                                        <c:when test="${basic.goodType==12 }">普通会员每日折扣</c:when>
                                        <c:when test="${basic.goodType==13 }">超级会员每日折扣</c:when>
                                        <c:when test="${basic.goodType==14 }">神将礼包</c:when>
                                    </c:choose></td>
                                    <td>${basic.basicDate }</td>
                                    <td><c:choose>
                                        <c:when test="${basic.isFinished==0 }">待验证</c:when>
                                        <c:when test="${basic.isFinished==1 }">验证失败</c:when>
                                        <c:when test="${basic.isFinished==2 }">未发货</c:when>
                                        <c:when test="${basic.isFinished==3 }">发货失败</c:when>
                                        <c:when test="${basic.isFinished==4 }">订单完成</c:when>
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