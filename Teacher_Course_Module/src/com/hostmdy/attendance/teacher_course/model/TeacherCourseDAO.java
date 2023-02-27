package com.hostmdy.attendance.teacher_course.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherCourseDAO {
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
	public ObservableList<String> getTeacherIdList(){
		ObservableList<String> teacherIds = FXCollections.observableArrayList();
		connection=DBConnection.getDBConnection();
		try {
			stmt= connection.createStatement();
			rs = stmt.executeQuery("Select * from teacher where teacher.status !='deleted';");
			while(rs.next()) {
				String ids = rs.getString("staff_id");
				teacherIds.add(ids);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return teacherIds;	
	}
	
	public String getTeacherName(String teacherId) {
		connection = DBConnection.getDBConnection();
		String name = "";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * from teacher where teacher.staff_id='"+teacherId+"';");
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				name = firstName+" "+lastName; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return name;
	}
	
	public int getDatabaseId(String sql) {
		connection = DBConnection.getDBConnection();
		int id=0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				 id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return id;
	}
	
	public ObservableList<String> getCourseCodeList(){
		ObservableList<String> courseCodes = FXCollections.observableArrayList();
		connection=DBConnection.getDBConnection();
		try {
			stmt= connection.createStatement();
			rs = stmt.executeQuery("Select * from course "
					+ "where not exists (select teachercourse.courseid from "
					+ "teachercourse where course.id=teachercourse.courseid);");
			while(rs.next()) {
				String codes = rs.getString("code");
				courseCodes.add(codes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseCodes;	
	}
	
	public int createTeacherCourse(TeacherCourse teacherCourse) {
		connection = DBConnection.getDBConnection();
		int rowEffected = 0;
		try {
			pStmt = connection.prepareStatement("INSERT INTO `teachercourse` "
					+ "(`courseid`, `teacherid`, `academicYear`) VALUES (?, ?, ?)");
			pStmt.setInt(1, teacherCourse.getCourseId());
			pStmt.setInt(2, teacherCourse.getTeacherId());
			pStmt.setString(3, teacherCourse.getAcademicYear());
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public ObservableList<TeacherCourse> getTeacherCourseData() {
		ObservableList<TeacherCourse>teacherCourseData = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select teacher.first_name,teacher.last_name,course.name,course.code,"
					+ "teachercourse.academicYear,teachercourse.id as i from teacher left join teachercourse on teachercourse.teacherid = teacher.id left join"
					+ " course on teachercourse.courseid=course.id"
					+ " where teachercourse.courseid=course.id and teachercourse.teacherid=teacher.id;");
//			int count = 1;
			String teacherName ="";
			while (rs.next()) {
				teacherName = rs.getString("first_name")+" "+rs.getString("last_name");
				teacherCourseData.add(new TeacherCourse(
						rs.getInt("i"),
						teacherName,
						rs.getString("name"),
						rs.getString("academicYear"),
						rs.getString("code")));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return teacherCourseData;
		
	}
	
	public int getTeacherId(int courseId) {
		connection = DBConnection.getDBConnection();
		int teacherId = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select teacherid from teachercourse where courseid='"+courseId+"';");
			
			while(rs.next()) {
				teacherId = rs.getInt("teacherid");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return teacherId;
	}
	
	public int deleteBycourseId(int courseId) {
		connection = DBConnection.getDBConnection();
		int rowEffected=0;
		try {
			pStmt = connection.prepareStatement("delete from teachercourse where courseid=?;");
			pStmt.setInt(1, courseId);
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
