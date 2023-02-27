package com.hostmdy.attendance.enrollment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;
import com.hostmdy.attendance.student.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnrollmentDAO {
	
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
	
	public Enrollment getEnrollment(String columnName, String value) {
		Enrollment enrollment = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select * from enrollment where "+columnName+"='"+value+"';");
			
			while (rs.next()) {
				enrollment = new Enrollment(
						rs.getInt("id"), 
						rs.getString("studentid"), 
						rs.getString("studentname"),
						rs.getString("year"), 
						rs.getString("major"), 
						rs.getString("academicyear")
						
						);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return enrollment;
		
	}
	
	
	public ObservableList<Enrollment> getEnrollmentList(String sql){
		ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from enrollment;");
			int count = 1;
			while(rs.next()) {
				enrollmentList.add(new Enrollment(
						count, 
						rs.getString("studentid"), 
						rs.getString("studentname"), 
						rs.getString("year"), 
						rs.getString("major"), 
						rs.getString("academicyear")
						));
						count++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return enrollmentList;
		
	}
	
	public int createEnrollment(Enrollment enrollment) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		try {
			pStmt = connection.prepareStatement(
					"INSERT INTO `enrollment` "
					+ "(`studentid`, `year`, `major`, `academicyear`, `studentname`) "
					+ "VALUES (?,?,?,?,?);"
					);
			pStmt.setString(1, enrollment.getStudentid());
			pStmt.setString(2, enrollment.getYear());
			pStmt.setString(3, enrollment.getMajor());
			pStmt.setString(4, enrollment.getAcademicyear());
			pStmt.setString(5, enrollment.getStudentname());
			
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
		connection =DBConnection.getDBConnection();
		try {
			pStmt = connection.prepareStatement("delete from enrollment where studentid=?;");
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
	

	
	public ObservableList<String> getStudentIdList(String sql){
		ObservableList<String> studentIdList = FXCollections.observableArrayList();
		Student student = new Student();
		connection = DBConnection.getDBConnection();
		
		try {
			stmt= connection.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String list = rs.getString("studentid");
				studentIdList.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return studentIdList;
		
	}
	
	public String getStdName(String id) {
		String fullname = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs= stmt.executeQuery("select `firstname`,`lastname` from student where studentid='"+id+"';");
			
			while(rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				
				fullname = fname +" "+ lname;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return fullname;
		
		
	}
	
	public int updateEnrollment(Enrollment enrollment) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement(
					"UPDATE `enrollment` SET "
					+ "`studentid` = ?, "
					+ "`year` = ?, "
					+ "`major` = ?, "
					+ "`academicyear` = ?, "
					+ "`studentname` = ? "
					+ "WHERE (`studentid` = ?);"
					);
			
			pStmt.setString(1, enrollment.getStudentid());
			pStmt.setString(2, enrollment.getYear());
			pStmt.setString(3, enrollment.getMajor());
			pStmt.setString(4, enrollment.getAcademicyear());
			pStmt.setString(5, enrollment.getStudentname());

			pStmt.setString(6, enrollment.getStudentid());

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
