package com.lxq.web.emp.servlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.lxq.web.emp.util.JdbcUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class EmpLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");				
		if(login(username, password)) {
			String ten=req.getParameter("ten");
			if("true".equals(ten)) {
				Cookie cookie=new Cookie("username",username);
				cookie.setMaxAge(60*60*24*10);
				resp.addCookie(cookie);			
			}
			//将获取的用户名赋值给session，用于保存用户记录
			HttpSession session=req.getSession();
			session.setAttribute("username",username);
			
			resp.sendRedirect("list.do");
		}else {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter ou=resp.getWriter();
			resp.setHeader("refresh", "5;url=login.html");
			ou.println("用户名或者密码错误，5秒钟后自动跳转到登录页面<a href='login.html'>点击</a>");
		}
	}
	public boolean login(String username,String password) {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			String sql="select username from user where username=? and password=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.closeDb(rs, pst, conn);
		}
		
		return false;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
