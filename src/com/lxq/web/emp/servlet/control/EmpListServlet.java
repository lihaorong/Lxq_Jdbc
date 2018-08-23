package com.lxq.web.emp.servlet.control;
/**
 * @function:��ҳ��չ�ֳ����ݿ�lxq�е�emp����������ݣ������в���������
 * @author ������
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
			//ͨ��Request���������������ݣ�����Χ��
			req.setAttribute("elist", elist);
			//session
			HttpSession session=req.getSession();
			
			String username=(String) session.getAttribute("username");
			if(username!=null) {
				//�������ڲ���ת
				req.getRequestDispatcher("list.jsp").forward(req, resp);
			}else{
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out=resp.getWriter();
				resp.setHeader("refresh", "5;url=login.html");
				out.println("�Բ��𣡷ǵ�¼�û�����ִ�д˲�����5���Ӻ�ϵͳ�Զ���ת����¼ҳ�棬"
						+ "���û����ת������<a href='login.html'>����</a>");					
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.closeDb(rs, pst, conn);
		}
	}
	
	/**
	 * @function:ҳ������ʽ
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
