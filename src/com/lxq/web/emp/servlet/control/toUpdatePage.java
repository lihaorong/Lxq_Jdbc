package com.lxq.web.emp.servlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxq.web.emp.servlet.bean.Emp;
import com.lxq.web.emp.util.JdbcUtil;

public class toUpdatePage extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empno = req.getParameter("empno");
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			pst = conn.prepareStatement("select * from emp where empno=?");
			pst.setInt(1, Integer.parseInt(empno));
			rs = pst.executeQuery();
			while (rs.next()) {
				Emp e = new Emp();
				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setMgr(rs.getInt("mgr"));
				// java.sql.Date -> java.util.Date
				e.setHiredate(rs.getDate("hiredate"));
				e.setSal(rs.getDouble("sal"));
				e.setComm(rs.getDouble("comm"));
				e.setDeptno(rs.getInt("deptno"));
				req.setAttribute("emp", e);
			}
			//req.getRequestDispatcher("toUpdatePageView.do").forward(req, resp);
			HttpSession session=req.getSession();
			String username=(String) session.getAttribute("username");
			if(username!=null) {				
				req.getRequestDispatcher("toUpdatePageView.do").forward(req, resp);
			}else {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter ou=resp.getWriter();
				resp.setHeader("refresh", "5;url=login.html");
				ou.println("用户名或者密码错误，5秒钟后自动跳转到登录页面<a href='login.html'>点击</a>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.closeDb(rs, pst, conn);
		}	
	}
}
