<%@page import="com.lxq.web.emp.servlet.bean.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新页面</title>
</head>
<body>
	<% Emp e = new Emp();
		e = (Emp) request.getAttribute("emp");
		pageContext.setAttribute("emp", e);
		if (e != null) {// 向前端写更新表单
			response.setContentType("text/html;charset=utf-8");
		%>
	<form action="EmptoUpdate.do" method="post">
		员工编号：<input type="text" name="empno" value=${emp.empno} readonly><br>
		员工姓名：<input type="text" name="ename" value=${emp.ename}><br>
		工作岗位：<input type="text" name="job" value=${emp.job}><br>
		领导编号：<input type="text" name="mgr" value=${emp.mgr}><br>
		入职日期：<input type="text" name="hiredate" value=${emp.hiredate}><br>
		员工工资：<input type="text" name="sal" value=${emp.sal}><br>
		员工津贴：<input type="text" name="comm" value=${emp.comm}><br>
		部门编号：<input type="text" name="deptno" value=${emp.deptno}><br>
		<input type="submit" value="修改">
	</form>

	<% }
	 else { System.out.println("查无此人"); }%>
</body>
</html>