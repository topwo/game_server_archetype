#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@page import="java.util.Map"%>
<%@page import="com.kidbear.${artifactId}.net.http.HttpServer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		ThreadGroup topGroup = group;
		// traverse the ThreadGroup tree to the top  
		while (group != null) {
			topGroup = group;
			group = group.getParent();
		}
		// Create a destination array that is about  
		// twice as big as needed to be very confident  
		// that none are clipped.  
		int estimatedSize = topGroup.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// Load the thread references into the oversized  
		// array. The actual number of threads loaded   
		// is returned.  
		int actualSize = topGroup.enumerate(slackList);
		// copy into a list that is the exact size
	%>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-table red"></i><span class="break"></span><strong>线程信息(线程数：<%=actualSize%>)
						</strong>
					</h2>
					<div class="panel-actions">
						<a href="${symbol_pound}" class="btn-close"
							onclick="skip('../datas/thread')"><i
							class="fa fa-reply"></i></a><a href="${symbol_pound}" class="btn-close"
							onclick="history.go(0)"><i class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<table
						class="table table-bordered table-striped table-condensed table-hover">
						<thead>
							<tr>
								<th>线程id</th>
								<th>线程名</th>
								<th>线程优先级</th>
								<th>守护线程</th>
								<th>活跃</th>
								<th>终止</th>
								<th>线程状态</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (actualSize == 0) {
							%>
							<tr>
								<th colspan="7">暂无线程信息</th>
							</tr>
							<%
								} else {
									for (int i = 0; i < actualSize; i++) {
										Thread t = slackList[i];
										if (t == null)
											continue;
							%>
							<tr>
								<td><%=t.getId()%></td>
								<td><%=t.getName()%></td>
								<td><%=t.getPriority()%></td>
								<td><%=t.isDaemon()%></td>
								<td>
									<%
										if (t.isAlive()) {
									%> <font color='red'>是</font> <%
 	} else {
 %> <font color='green'>否</font> <%
 	}
 %>
								</td>
								<td>
									<%
										if (t.isInterrupted()) {
									%> <font color='red'>是</font> <%
 	} else {
 %> <font color='green'>否</font> <%
 	}
 %>
								</td>
								<td><%=t.getState().name()%></td>
							</tr>
							<tr>
								<td colspan="7">
									<%
										for (StackTraceElement element : t.getStackTrace()) {
									%> <%=element%><br> <%
 	}
 %>
								</td>
							</tr>
							<%
								}
								}
							%>
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
















</body>
</html>