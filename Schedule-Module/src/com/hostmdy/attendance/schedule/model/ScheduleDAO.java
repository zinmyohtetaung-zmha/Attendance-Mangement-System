package com.hostmdy.attendance.schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.course.model.Course;
import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScheduleDAO {
	
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
	
	public Schedule getSchedule(String columnName, String value) {
		Schedule schedule = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from schedule where "+columnName+"='"+value+"';");

			while(rs.next()) {
				schedule = new Schedule(
						rs.getInt("id"), 
						rs.getString("coursecode"), 
						//rs.getString("coursename"), 
						rs.getString("day"), 
						rs.getString("timeslot"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return schedule;		
	
	}
	
	
	
	
	public int getIdfromDB(String coursecode, String day) {

		connection = DBConnection.getDBConnection();
		int id=0;
		
		try {
			stmt = connection.createStatement();
			rs= stmt.executeQuery(
					"select * from schedule where schedule.coursecode='"+coursecode+"' and schedule.day='"+day+"';");
			
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
	
	public ObservableList<Schedule> getScheduleList(String sql){
		ObservableList<Schedule> schedulesList = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select course.name,schedule.* from course inner join schedule on course.code=schedule.coursecode;");
			int count=1;
			while(rs.next()) {
				schedulesList.add(new Schedule(
						count, 
						rs.getString("coursecode"), 
						rs.getString("name"), 
						rs.getString("day"), 
						rs.getString("timeslot")
						));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return schedulesList;
	}
	
	public int createSchedule(Schedule schedule) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement("INSERT INTO `schedule` "
					+ "(`coursecode`, `day`, `timeslot`) "
					+ "VALUES (?, ?, ?);"
					);
			
			pStmt.setString(1, schedule.getCoursecode());
			pStmt.setString(2, schedule.getDay());
			pStmt.setString(3, schedule.getTimeslot());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
			
	}
	
	public int updateSchedule(Schedule schedule) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement(
					"UPDATE `schedule` SET "
					
					+ "`day` = ?, "
					+ "`timeslot` = ? "
					+ "WHERE (`coursecode` = ?);"
					);
			
			
			pStmt.setString(1, schedule.getDay());
			pStmt.setString(2, schedule.getTimeslot());
			pStmt.setString(3, schedule.getCoursecode());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	public String getCourseName(String code) {
		String courseName = null;
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select name from course where code='"+code+"';");
			
			while(rs.next()) {
				String name = rs.getString("name");
				
				courseName = name;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseName;
		
		
	}
	
	public ObservableList<String> getCourseCodeList(String sql){
		ObservableList<String> courseCodeList = FXCollections.observableArrayList();
		Course course = new Course();
		connection = DBConnection.getDBConnection();
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String list = rs.getString("code");
				courseCodeList.add(list);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseCodeList;
	
	
		
		
	}
	
	public int deleteByID(int id) {
		int rowEffected = 0;
		connection = DBConnection.getDBConnection();
		
		try {
			pStmt = connection.prepareStatement("delete from schedule where id=?;");
			pStmt.setInt(1, id);
			
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
