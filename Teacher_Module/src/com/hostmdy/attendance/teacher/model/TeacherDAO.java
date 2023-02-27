package com.hostmdy.attendance.teacher.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherDAO {
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Teacher getTeacher(String columnName,String value) {
		Teacher teacher = null;
		connection = DBConnection.getDBConnection();
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from teacher where "+columnName+"='"+value+"';");
			while(rs.next()) {
				teacher=new Teacher(
						rs.getInt("id"),
						rs.getString("stuffId"),
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("nrc"),
						rs.getString("address"),
						rs.getString("faculty"),
						rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teacher;
	}
	
	public int createTeacher(Teacher teacher) {
		int rowEffected= 0;
		connection = DBConnection.getDBConnection();
		try {
			pstmt=connection.prepareStatement("INSERT INTO `teacher` (`staff_id`, `first_name`, `last_name`, "
					+ "`email`, `phone`, `nrc`, `address`, "
					+ "`faculty`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);");
			pstmt.setString(1,teacher.getStaffId());
			pstmt.setString(2, teacher.getFirstName());
			pstmt.setString(3, teacher.getLastName());
			pstmt.setString(4, teacher.getEmail());
			pstmt.setString(5, teacher.getPhone());
			pstmt.setString(6, teacher.getNrc());
			pstmt.setString(7, teacher.getAddress());
			pstmt.setString(8, teacher.getFaculty());
			pstmt.setString(9, teacher.getStatus());
			rowEffected=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public ObservableList<Teacher> getTeacherList(String sql){
		ObservableList<Teacher>teacherList = FXCollections.observableArrayList();
		
		connection= DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs =stmt.executeQuery(sql);
			int count=1;
			while(rs.next()) {
			teacherList.add(new Teacher(
					count,
//					rs.getInt("id"),
					rs.getString("staff_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getString("nrc"),
					rs.getString("address"),
					rs.getString("faculty"),
					rs.getString("status")));
			count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return teacherList;
	}
	public int editTeacher(Teacher teacher) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		try {
			pstmt = connection.prepareStatement("UPDATE `teacher` SET "
					/*+ "`staff_id` = ?, "*/
					+ "`first_name` = ?, "
					+ "`last_name` = ?, "
					+ "`email` = ?, "
					+ "`phone` = ?, "
					+ "`nrc` = ?, "
					+ "`address` = ?, "
					+ "`faculty` = ?, "
					+ "`status` = ? where `staff_id`= ?");
		
			pstmt.setString(1, teacher.getFirstName());
			pstmt.setString(2, teacher.getLastName());
			pstmt.setString(3, teacher.getEmail());
			pstmt.setString(4, teacher.getPhone());
			pstmt.setString(5, teacher.getNrc());
			pstmt.setString(6, teacher.getAddress());
			pstmt.setString(7, teacher.getFaculty());
			pstmt.setString(8, teacher.getStatus());
			pstmt.setString(9, teacher.getStaffId());
			
			rowEffected=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int deleteById(String staff_id) {
		connection = DBConnection.getDBConnection();
		int rowEffected = 0;
		try {
			pstmt = connection.prepareStatement("UPDATE `teacher` SET `status` = ?"
					+ " WHERE (`staff_id` = ?);");
			pstmt.setString(1, "deleted");
			pstmt.setString(2,staff_id);
			rowEffected=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	public Teacher getTeacherID(String columnName, String value) {
		Teacher teacher = null;
		connection = DBConnection.getDBConnection();
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from teacher where "+columnName+"='"+value+"';");
			while(rs.next()) {
				teacher=new Teacher(
						rs.getInt("id"),
						rs.getString("staff_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("nrc"),
						rs.getString("address"),
						rs.getString("faculty"),
						rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return teacher;
	}
}
