package com.lxq.web.emp.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxq.web.emp.servlet.bean.Emp;

public class toUpdatePageView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Emp e = new Emp();
		e = (Emp) req.getAttribute("emp");
		if (e != null) {// 向前端写更新表单
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head><title>员工修改列表</title></head>");
			out.println("<body>");
			out.println("<form action='EmptoUpdate.do' method='post'>");
			out.println("员工编号：<input type='text' name='empno' value='" + e.getEmpno() + "' readonly><br>");
			out.println("员工姓名：<input type='text' name='ename' value='" + e.getEname() + "'><br>");
			out.println("工作岗位：<input type='text' name='job' value='" + e.getJob() + "'><br>");
			out.println("领导编号：<input type='text' name='mgr' value='" + e.getMgr() + "'><br>");
			out.println("入职日期：<input type='text' name='hiredate' value='" + e.getHiredate() + "'><br>");
			out.println("员工工资：<input type='text' name='sal' value='" + e.getSal() + "'><br>");
			out.println("员工津贴：<input type='text' name='comm' value='" + e.getComm() + "'><br>");
			out.println("部门编号：<input type='text' name='deptno' value='" + e.getDeptno() + "'><br>");
			out.println("<input type='submit' value='修改'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} else {
			System.out.println("查无此人");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
