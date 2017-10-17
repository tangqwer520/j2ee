package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnDB;
import com.entity.Student;

public class Index extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConnDB con = new ConnDB();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String attribute = request.getParameter("attribute");
		System.out.println("attribute:" + attribute);

		if (attribute == null || attribute.equals("")) {
			String sql = "select * from student";
			try {
				List<Student> list = con.executeQuery(sql);
				request.setAttribute("list", list);
				System.out.println("xxx");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}

		else if (attribute.equals("age")) {
			age(request, response);
		}

		else if (attribute.equals("name") || attribute.equals("id")) {
			idname(request, response);
		}
	}

	private void age(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String min = request.getParameter("ch");
		String query = request.getParameter("input_value");
		String sql = "";
		if (min.equals("0")) {
			sql = "select * from student where age <" + query;
		} else
			sql = "select * from student where age" + min + query;
		System.out.println(sql);
		try {
			List<Student> list = con.executeQuery(sql);
			request.setAttribute("list", list);
			System.out.println("xxx");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void idname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String attribute = request.getParameter("attribute");
		String ch = request.getParameter("ch");
		String query = request.getParameter("input_value");
		query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		String sql = "";
		if (ch.equals("include")) {
			sql = "select * from student where " + attribute + " LIKE'%"
					+ query + "%'";
			System.out.println(sql);
		} else {
			sql = "select * from student where " + attribute + " LIKE'" + query
					+ "'";
		}
		try {
			List<Student> list = con.executeQuery(sql);
			request.setAttribute("list", list);
			System.out.println("xxx");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
