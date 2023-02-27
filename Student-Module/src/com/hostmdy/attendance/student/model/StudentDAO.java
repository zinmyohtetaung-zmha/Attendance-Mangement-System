package com.hostmdy.attendance.student.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAO {
	
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
	
	public Student getStudent(String columnName, String value) {
		Student student = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select * from student where "+columnName+"='"+value+"';");
			
			while(rs.next()) {
				student = new Student(
						rs.getInt("id"), 
						rs.getString("studentid"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("email"), 
						rs.getString("phone"), 
						rs.getString("nrc"), 
						rs.getString("address")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return student;
	}
	
	public ObservableList<Student> getStudentList(String sql){
		ObservableList<Student> studentList = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from student;");
			int count = 1;
			while(rs.next()) {
				studentList.add(new Student(
						count, 
						rs.getString("studentid"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("email"), 
						rs.getString("phone"), 
						rs.getString("nrc"), 
						rs.getString("address")
						));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return studentList;		
	}	
	
	public int createStudent(Student student) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		try {
			pStmt = connection.prepareStatement("INSERT INTO `student` "
					+ "(`studentid`, `firstname`, `lastname`, "
					+ "`email`, `phone`, `nrc`, `address`) "
					+ "VALUES (?,?,?,?,?,?,?);"
					);
			pStmt.setString(1, student.getStudentid());
			pStmt.setString(2, student.getFirstname());
			pStmt.setString(3, student.getLastname());
			pStmt.setString(4, student.getEmail());
			pStmt.setString(5, student.getPhone());
			pStmt.setString(6, student.getNrc());
			pStmt.setString(7, student.getAddress()); 
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;		
	}
	
	public int updateStudent(Student student) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		try {
			pStmt = connection.prepareStatement(
					"UPDATE `student` SET "
					+ "`studentid` = ?, `firstname` = ?, `lastname` = ?, "
					+ "`email` = ?, `phone` = ?, `nrc` = ?, `address` = ? "
					+ "WHERE (`id` = ?);"
					);
			pStmt.setString(1, student.getStudentid());
			pStmt.setString(2, student.getFirstname());
			pStmt.setString(3, student.getLastname());
			pStmt.setString(4, student.getEmail());
			pStmt.setString(5, student.getPhone());
			pStmt.setString(6, student.getNrc());
			pStmt.setString(7, student.getAddress()); 
			pStmt.setInt(8, student.getId());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int deleteByStdID(String studentid) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		try {
			pStmt = connection.prepareStatement("delete from student where studentid=?;");
			pStmt.setString(1, studentid);
			
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
