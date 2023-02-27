package com.hostmdy.attendance.course.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDAO {
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
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
	
	public Course getCourse(String columnName, String value) {
		Course course = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from course where "+columnName+"='"+value+"';");
			
			while(rs.next()) {
				course = new Course(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("code"),
						rs.getInt("credit"),
						rs.getString("semester")
						);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return course;		
	}
	
	public ObservableList<Course> getCourseList(String sql){
		ObservableList<Course> courseList = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from course;");
			int count = 1;
			while(rs.next()) {
				courseList.add(new Course(
						count,
						rs.getString("name"),
						rs.getString("code"),
						rs.getInt("credit"),
						rs.getString("semester")
						));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseList;		
	}
	
	public int createCourse(Course course) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement("INSERT INTO `course` "
					+ "(`name`, `code`, `credit`,`semester`) "
					+ "VALUES (?, ?, ?, ?);"
					);
			
			pStmt.setString(1, course.getName());
			pStmt.setString(2, course.getCode());
			pStmt.setInt(3, course.getCredit());
			pStmt.setString(4, course.getSemester());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int updateCourse(Course course) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement(
					"UPDATE `course` SET "
					+ "`name` = ?, `code` = ?, `credit` = ?, `semester` = ? "
					+ "WHERE (`code` = ?);"
					);
			
			pStmt.setString(1, course.getName());
			pStmt.setString(2, course.getCode());
			pStmt.setInt(3, course.getCredit());
			pStmt.setString(4, course.getSemester());
			pStmt.setString(5, course.getCode());
			
			rowEffected = pStmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int deleteByCourseCode(String code) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement("delete from course where code=?;");
			pStmt.setString(1, code);
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	

}
