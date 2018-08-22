package com.lxq.web.emp.servlet.view;
/**
 * @function:�б����ͼ��
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
		resp.setContentType("text/html;charset=utf-8");//�ύ�ĸ�ʽ���ı�����
		PrintWriter out=resp.getWriter();//������
		out.println("<html>");
		out.println("<head><title>Ա���б�</title></head>");
		out.println("<body>");
		out.println("<table border='1px' width='80%'>");
		out.println("<tr><td>Ա�����</td><td>Ա������</td>"
				      + "<td>Ա������</td><td>��˾���</td>"
				      + "<td>��ְ����</td><td>Ա��нˮ</td>"
				      + "<td>Ա������</td><td>���ű��</td>"
				      + "<td>����</td>"
				      + "</tr>");
		for(Emp e:elList){
			out.println("<tr><td>"+e.getEmpno()+"</td><td>"+e.getEname()+"</td>"
				      + "<td>"+e.getJob()+"</td><td>"+e.getMgr()+"</td>"
				      + "<td>"+e.getHiredate()+"</td><td>"+e.getSal()+"</td>"
				      + "<td>"+e.getComm()+"</td><td>"+e.getDeptno()+"</td>"
				      + "<td><a href='del.do?empno="+e.getEmpno()+"'>ɾ��</a>"
				      		+ "&nbsp;&nbsp;&nbsp;<a href='toUpdate.do?empno="+e.getEmpno()+"'>�޸�</a></td>"
				      + "</tr>");
		}
		out.println("</table>");
		out.println("<a href='add.html'>���</a>");
		out.println("<a href='EmpExitServlet.do'>�˳�ϵͳ</a>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
