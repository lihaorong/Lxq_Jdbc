package com.lxq.web.emp.servlet.control;
/**
 * @function:首页，展现出数据库lxq中的emp表的所有数据，并附有操作的属性
 * @author 李晓晴
 * @Time:2018/08/20
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

public class EmpListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Emp> elist=new ArrayList<Emp>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			pst=conn.prepareStatement("select * from emp");
			rs=pst.executeQuery();
			while(rs.next()){
			  Emp e=new Emp();
			  e.setEmpno(rs.getInt("empno"));
			  e.setEname(rs.getString("ename"));
			  e.setJob(rs.getString("job"));
			  e.setMgr(rs.getInt("mgr"));
			  //java.sql.Date  ->  java.util.Date
			  e.setHiredate(rs.getDate("hiredate"));
			  e.setSal(rs.getDouble("sal"));
			  e.setComm(rs.getDouble("comm"));
			  e.setDeptno(rs.getInt("deptno"));
			  elist.add(e);
			}
			//通过Request作用域来传递数据（请求范围）
			req.setAttribute("elist", elist);
			//session
			HttpSession session=req.getSession();
			
			String username=(String) session.getAttribute("username");
			if(username!=null) {
				//服务器内部跳转
				req.getRequestDispatcher("list.jsp").forward(req, resp);
			}else{
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out=resp.getWriter();
				resp.setHeader("refresh", "5;url=login.html");
				out.println("对不起！非登录用户不能执行此操作，5秒钟后系统自动跳转到登录页面，"
						+ "如果没有跳转，请点击<a href='login.html'>这里</a>");					
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.closeDb(rs, pst, conn);
		}
	}
	
	/**
	 * @function:页面表格样式
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
