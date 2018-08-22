package com.lxq.web.emp.servlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxq.web.emp.util.JdbcUtil;

public class EmpDelServlet extends HttpServlet {
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
	 	String empno=req.getParameter("empno");
        boolean b=del(Integer.parseInt(empno));	        
        PrintWriter out=resp.getWriter();
        if(b){
        	resp.sendRedirect("list.do");
        }else{
        	out.println("É¾³ýÊ§°Ü");
        }
	}

	
	public boolean del(int empno){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn = JdbcUtil.getConnection();
			pst=conn.prepareStatement("delete from emp where empno=?");
			pst.setInt(1, empno);
			int count=pst.executeUpdate();
			if(count>=1){
				return true;
			}else{
				return false;
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally {
			JdbcUtil.closeDb(pst, conn);
		}
		return false;
	}
}
