#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日志管理</title>
    <script type="text/javascript"
            src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function update() {
            var datas = {
                sdk: ${symbol_dollar}("input[name='sdk']:checked").val() == 'on' ? true : false,
                msg: ${symbol_dollar}("input[name='msg']:checked").val() == 'on' ? true : false,
                pay: ${symbol_dollar}("input[name='pay']:checked").val() == 'on' ? true : false,
                client: ${symbol_dollar}("input[name='client']:checked").val() == 'on' ? true
                        : false
            };;;;;;
            alert('修改配置成功');
            ${symbol_dollar}.ajax({
                url: "../log/logConfigHandle",
                type: "post",
                data: datas,
                success: function (data) {
                    alert(data);
                    if (data == "success") {
                        /* alert("修改配置成功"); */
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
                                                                             <c:if test="${symbol_dollar}{sdk==true }">checked="checked"</c:if>>
                                    <span
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
                                                                             <c:if test="${symbol_dollar}{msg==true }">checked="checked"</c:if>>
                                    <span
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
                                        <c:if test="${symbol_dollar}{client==true }">checked="checked"</c:if>>
                                    <span class="switch-label" data-on="On" data-off="Off"></span>
                                    <span class="switch-handle"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-6 control-label">支付开启</label>
                        <div class="controls">
                            <div class="col-md-1 col-sm-2 col-xs-3">
                                <label class="switch switch-success"> <input name="pay"
                                                                             type="checkbox" class="switch-input"
                                                                             <c:if test="${symbol_dollar}{pay==true }">checked="checked"</c:if>>
                                    <span
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
                        onclick="update()">
                    <i class="fa fa-dot-circle-o"></i> 修改
                </button>
            </div>
        </div>
    </div>
    <!--/col-->
</div>
<!--/row-->
</body>
</html>