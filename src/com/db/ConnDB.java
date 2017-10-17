package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Student;

public class ConnDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ConnDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Student> executeQuery(String sql) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/student";
		String userName = "root";
		String password = "123456";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		List<Student> list = studentList(rs);
		return list;

	}

	private List<Student> studentList(ResultSet rs2) throws SQLException {
		List<Student> list = new ArrayList<Student>();
		while (rs.next()) {
			Student stu = new Student();

			stu.setId(rs.getString("id"));
			stu.setName(rs.getString("name"));
			stu.setAge(rs.getInt("age"));
			list.add(stu);
		}
		return list;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		try {
			if (stmt != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		try {
			if (conn != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}
}
