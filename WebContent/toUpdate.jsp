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
		if (e != null) {// 向前端写更新表单
			response.setContentType("text/html;charset=utf-8");
		%>
	<form action="EmptoUpdate.do" method="post">
		员工编号：<input type="text" name="empno" value=<%=e.getEmpno() %> readonly><br>
		员工姓名：<input type="text" name="ename" value=<%=e.getEname() %>><br>
		工作岗位：<input type="text" name="job" value=<%=e.getJob() %>><br>
		领导编号：<input type="text" name="mgr" value=<%=e.getMgr() %>><br>
		入职日期：<input type="text" name="hiredate" value=<%=e.getHiredate() %>><br>
		员工工资：<input type="text" name="sal" value=<%=e.getSal() %>><br>
		员工津贴：<input type="text" name="comm" value=<%=e.getComm() %>><br>
		部门编号：<input type="text" name="deptno" value=<%=e.getDeptno() %>><br>
		<input type="submit" value="修改">
	</form>

	<% }
	 else { System.out.println("查无此人"); }%>
</body>
</html>