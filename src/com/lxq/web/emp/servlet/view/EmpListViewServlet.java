package com.lxq.web.emp.servlet.view;
/**
 * @function:列表的视图层
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxq.web.emp.servlet.bean.Emp;

public class EmpListViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Emp> elList=(List<Emp>) req.getAttribute("elist");
		resp.setContentType("text/html;charset=utf-8");//提交的格式和文本类型
		PrintWriter out=resp.getWriter();//输出语句
		out.println("<html>");
		out.println("<head><title>员工列表</title></head>");
		out.println("<body>");
		out.println("<table border='1px' width='80%'>");
		out.println("<tr><td>员工编号</td><td>员工姓名</td>"
				      + "<td>员工工作</td><td>上司编号</td>"
				      + "<td>入职日期</td><td>员工薪水</td>"
				      + "<td>员工福利</td><td>部门编号</td>"
				      + "<td>操作</td>"
				      + "</tr>");
		for(Emp e:elList){
			out.println("<tr><td>"+e.getEmpno()+"</td><td>"+e.getEname()+"</td>"
				      + "<td>"+e.getJob()+"</td><td>"+e.getMgr()+"</td>"
				      + "<td>"+e.getHiredate()+"</td><td>"+e.getSal()+"</td>"
				      + "<td>"+e.getComm()+"</td><td>"+e.getDeptno()+"</td>"
				      + "<td><a href='del.do?empno="+e.getEmpno()+"'>删除</a>"
				      		+ "&nbsp;&nbsp;&nbsp;<a href='toUpdate.do?empno="+e.getEmpno()+"'>修改</a></td>"
				      + "</tr>");
		}
		out.println("</table>");
		out.println("<a href='add.html'>添加</a>");
		out.println("<a href='EmpExitServlet.do'>退出系统</a>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
