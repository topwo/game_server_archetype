<%@page import="com.kidbear.logical.util.hibernate.HibernateUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="javax.persistence.Id"%>
<%@page import="javax.persistence.GeneratedValue"%>
<%@page import="org.hibernate.internal.SessionFactoryImpl"%>
<%@page import="org.hibernate.metadata.ClassMetadata"%>
<%@page import="org.hibernate.persister.entity.AbstractEntityPersister"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="java.lang.reflect.Field"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<%
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Map<String, ClassMetadata> map = sessionFactory
				.getAllClassMetadata();
	%>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-table red"></i><span class="break"></span><strong>相关类</strong>
					</h2>
					<div class="panel-actions">
						<a href="#" class="btn-close"
							onclick="skip('../datas/class')"><i
							class="fa fa-reply"></i></a><a href="#" class="btn-close"
							onclick="history.go(0)"><i class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<table
						class="table table-bordered table-striped table-condensed table-hover">
						<thead>
							<tr>
								<td>类名</td>
								<td>包名</td>
								<td>主键</td>
								<td>主键是否自增</td>
								<td>实现MCSupport接口</td>
							</tr>
						</thead>
						<tbody>
							<%
								if (map.keySet().size() == 0) {
							%><tr>
								<th colspan="5">暂无相关类</th>
							</tr>
							<%
								} else {
									for (String entityName : map.keySet()) {
										SessionFactoryImpl sfImpl = (SessionFactoryImpl) sessionFactory;
										String tableName = ((AbstractEntityPersister) sfImpl
												.getEntityPersister(entityName)).getTableName();
										Class className = null;
										try {
											className = Class.forName(entityName);
										} catch (Exception e) {
											e.printStackTrace();
										}
										Class<?> face[] = className.getInterfaces();
										boolean isMCSupport = false;
										for (int i = 0; i < face.length; i++) {
											if ("com.kidbear.logical.util.cache.MCSupport"
													.equals(face[i].getName())) {
												isMCSupport = true;
											}
										}
										String idName = null;
										boolean isAutoAdd = false;
										//获取类的属性
										Field[] fields = className.getDeclaredFields();
										for (Field f : fields) {
											//获取字段中包含fieldMeta的注解
											Id meta = f.getAnnotation(Id.class);
											if (meta != null) {
												idName = f.getName();
												GeneratedValue generatedValue = f
														.getAnnotation(GeneratedValue.class);
												if (generatedValue != null) {
													isAutoAdd = true;
												}
											}
										}
							%>
							<tr>
								<td><%=tableName%></td>
								<td><%=entityName%></td>
								<td><%=idName%></td>
								<td>
									<%
										if (isAutoAdd) {
									%> <font color='red'>是</font> <%
 	} else {
 %> <font color='green'>否</font> <%
 	}
 %>
								</td>
								<td>
									<%
										if (isMCSupport) {
									%> <font color='red'>是</font> <%
 	} else {
 %> <font color='green'>否</font> <%
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
