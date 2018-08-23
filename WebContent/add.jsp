<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>添加用户页面</title>
</head>
<body>
	 <form action="add.do" method="post">
           	  员工编号<input type="text" name="empno"><br>
		           员工姓名<input type="text" name="ename"><br>
		           员工工作<input type="text" name="job"><br>
		           上司编号<input type="text" name="mgr"><br>
		           入职日期<input type="text" name="hiredate"><br>
		           员工薪水<input type="text" name="sal"><br>
		           员工福利<input type="text" name="comm"><br>
		           部门编号<input type="text" name="deptno"><br>
           	<input type="submit" value="添加">
    </form>
	
</body>
</html>