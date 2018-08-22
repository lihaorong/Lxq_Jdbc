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
		if (e != null) {// ��ǰ��д���±�
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head><title>Ա���޸��б�</title></head>");
			out.println("<body>");
			out.println("<form action='EmptoUpdate.do' method='post'>");
			out.println("Ա����ţ�<input type='text' name='empno' value='" + e.getEmpno() + "' readonly><br>");
			out.println("Ա��������<input type='text' name='ename' value='" + e.getEname() + "'><br>");
			out.println("������λ��<input type='text' name='job' value='" + e.getJob() + "'><br>");
			out.println("�쵼��ţ�<input type='text' name='mgr' value='" + e.getMgr() + "'><br>");
			out.println("��ְ���ڣ�<input type='text' name='hiredate' value='" + e.getHiredate() + "'><br>");
			out.println("Ա�����ʣ�<input type='text' name='sal' value='" + e.getSal() + "'><br>");
			out.println("Ա��������<input type='text' name='comm' value='" + e.getComm() + "'><br>");
			out.println("���ű�ţ�<input type='text' name='deptno' value='" + e.getDeptno() + "'><br>");
			out.println("<input type='submit' value='�޸�'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} else {
			System.out.println("���޴���");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
