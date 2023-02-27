package com.hostmdy.attendance.report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.ObservableList;

public class ReportDAO {
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
	
	public int getNoOfStudents(String date, int scheduleId){
		connection = DBConnection.getDBConnection();
		int noOfStudents = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT count(enrollmentId) from attendance "
					+ "where attendance.date='"+date+"' and scheduleId = '"+scheduleId+"'");
			while (rs.next()) {
				noOfStudents = rs.getInt("count(enrollmentId)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return noOfStudents;
	}
	
	public int getNoOfPresentStudents(String date, int scheduleId){
		connection = DBConnection.getDBConnection();
		int noOfPresentStudents = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT count(enrollmentId) from attendance "
					+ "where attendance.date='"+date+"' and scheduleId = '"+scheduleId+"'"
							+ " and isPresent = true");
			while (rs.next()) {
				noOfPresentStudents = rs.getInt("count(enrollmentId)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return noOfPresentStudents;
	}
	
	public int getNoOfStudentsPerMonth(LocalDate startDate, LocalDate endDate, int scheduleId){
		connection = DBConnection.getDBConnection();
		int noOfStudents = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT count(enrollmentId) from attendance "
					+ "where attendance.date>='"+startDate+"' and attendance.date <= '"+
							endDate+ "' and scheduleId = '"+scheduleId+"'");
			while (rs.next()) {
				noOfStudents = rs.getInt("count(enrollmentId)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return noOfStudents;
	}
	
	public int getNoOfPresentStudentsPerMonth(LocalDate startDate, LocalDate endDate, int scheduleId){
		connection = DBConnection.getDBConnection();
		int noOfPresentStudents = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT count(enrollmentId) from attendance "
					+ "where attendance.date >='"+startDate+"' and attendance.date <= '"+endDate
							+ "' and scheduleId = '"+scheduleId+"'"
							+ " and isPresent = true");
			while (rs.next()) {
				noOfPresentStudents = rs.getInt("count(enrollmentId)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return noOfPresentStudents;
	}
}
