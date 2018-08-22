package com.lxq.web.emp.servlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxq.web.emp.servlet.bean.Emp;
import com.lxq.web.emp.util.JdbcUtil;

public class EmpAddServlet extends HttpServlet {

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String empno=req.getParameter("empno");
		String ename=req.getParameter("ename");
		String job=req.getParameter("job");
		String mgr=req.getParameter("mgr");
		String hiredate=req.getParameter("hiredate");
		String sal=req.getParameter("sal");
		String comm=req.getParameter("comm");
		String deptno=req.getParameter("deptno");
		
		Emp e=new Emp();
		e.setEmpno(Integer.parseInt(empno));
		e.setEname(ename);
		e.setJob(job);
		e.setMgr(Integer.parseInt(mgr));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			e.setHiredate(sdf.parse(hiredate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.setSal(Double.parseDouble(sal));
		e.setComm(Double.parseDouble(comm));
		e.setDeptno(Integer.parseInt(deptno));
		
		boolean b=add(e);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		if(b) {
			resp.sendRedirect("list.do");
		}else {
			out.println("Ìí¼ÓÊ§°Ü");
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	public boolean add(Emp e){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn = JdbcUtil.getConnection();
			pst=conn.prepareStatement("insert into emp values(?,?,?,?,?,?,?,?)");
			pst.setInt(1, e.getEmpno());
			pst.setString(2, e.getEname());
			pst.setString(3, e.getJob());
			pst.setInt(4, e.getMgr());
			//java.uitl.date  --ºÁÃëÊý--> java.sql.date
			java.util.Date date=e.getHiredate();
			pst.setDate(5, new java.sql.Date(date.getTime()));
			pst.setDouble(6, e.getSal());
			pst.setDouble(7, e.getComm());
			pst.setInt(8, e.getDeptno());
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
