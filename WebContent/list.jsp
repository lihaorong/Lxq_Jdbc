<%@page import="com.lxq.web.emp.servlet.bean.Emp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="lxq" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
</head>
<body>
	<%	
	response.setContentType("text/html;charset=utf-8");	
	List<Emp> eList=(List<Emp>)request.getAttribute("elist");
	pageContext.setAttribute("elist", eList);	
	%>
	<table border='1px' width='80%'>
		<tr>
			<td>员工编号</td>
			<td>员工姓名</td>
			<td>员工工作</td>
			<td>上司编号</td>
			<td>入职日期</td>
			<td>员工薪水</td>
			<td>员工福利</td>
			<td>部门编号</td>
			<td>操作</td>
		</tr>
	<%-- <% for(Emp e:eList){ 
		pageContext.setAttribute("emp", e);		
	%> --%>	
	<lxq:forEach items="${elist}" var="emp">
		<tr>
			<td>${emp.empno}</td>
			<td>${emp.ename}</td>
			<td>${emp.job}</td>
			<td>${emp.mgr}</td>
			<td>${emp.hiredate}</td>
			<td>${emp.sal}</td>
			<td>${emp.comm}</td>
			<td>${emp.deptno}</td>
			<td>
				<a href="del.do?empno=${emp.empno}">删除</a>
				&nbsp;&nbsp;&nbsp;
				<a href="toUpdate.do?empno=${emp.empno}">修改</a>
			</td>
		</tr>
		<%-- <%} %>  --%>
	</lxq:forEach>	
	</table>

	<a href="add.jsp">添加</a><br>
	<a href="EmpExitServlet.do">退出系统</a>
</body>
</html>