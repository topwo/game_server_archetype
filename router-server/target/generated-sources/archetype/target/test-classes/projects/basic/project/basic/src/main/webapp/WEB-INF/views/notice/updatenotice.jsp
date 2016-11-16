<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- <script type="text/javascript" src="../js/server/server.js"></script>-->
    <script type="text/javascript">
        function saveNotice() {
            var notice = $('#notice').val();
            if (notice == "") {
                alert("请填写公告内容");
                return false;
            }
            var datas = {
                "notice": notice
            };
            $.ajax({
                data: datas,
                type: "POST",
                url: "../notice/updateNoticeHandle",
                success: function (result) {
                    if (result == "success") {
                        alert("更改公告内容成功");
                        $('#right').load("../notice/updatenotice");
                    } else {
                        alert("更改公告内容失败");
                    }
                },
                error: function (result) {
                    alert("更改公告内容失败");
                }
            });
        }
    </script>
    <title>更新游戏公告内容</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>
                    <i class="fa fa-indent red"></i><strong>更新游戏公告内容</strong>
                </h2>
            </div>
            <div class="panel-body">
                <form action="" method="post" class="form-horizontal ">
                    <div class="form-group">
                        <div class="col-md-12">
								<textarea id="notice" class="form-control" style="height: 500px"
                                          placeholder="请输入公告内容">${notice }</textarea>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="panel-footer">
                <button type="button" class="btn btn-success btn-lg btn-block"
                        onclick="saveNotice()">
                    <i class="fa fa-dot-circle-o"></i> 保存
                </button>
            </div>
        </div>
    </div>
    <!--/.col-->
</body>
</html>