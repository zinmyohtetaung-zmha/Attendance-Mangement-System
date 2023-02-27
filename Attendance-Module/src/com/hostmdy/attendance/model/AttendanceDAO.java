package com.hostmdy.attendance.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;

public class AttendanceDAO {
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
	
	public ObservableList<Attendance> getaddTableView(String sql){
		connection = DBConnection.getDBConnection();
		ObservableList<Attendance> tableData = FXCollections.observableArrayList();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RadioButton present = new RadioButton();
				RadioButton absent = new RadioButton();
				ToggleGroup toggleGroup = new ToggleGroup();
				present.setToggleGroup(toggleGroup);
				absent.setToggleGroup(toggleGroup);
				present.setText("Present");
				absent.setText("Absent");
				absent.setTextFill(Paint.valueOf("Red"));;
				present.setSelected(true);
				tableData.add(new Attendance(
						rs.getInt("id"), 
						rs.getString("studentname"), 
						present, 
						absent,
						rs.getString("studentid"),
						rs.getString("year")
						));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return FXCollections.observableArrayList(tableData);
	}
	
	public ObservableList<String> getCoursecodeList(String dayOfTheWeek){
		ObservableList<String> courseCodes = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt =connection.createStatement();
			rs = stmt.executeQuery("Select coursecode from schedule where schedule.day='"+dayOfTheWeek+"';");
			while(rs.next()) {
				String code = rs.getString("coursecode");
				courseCodes.add(code);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseCodes;
	}
	
	public ObservableList<String> getCoursecodeList(){
		ObservableList<String> courseCodes = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt =connection.createStatement();
			rs = stmt.executeQuery("Select coursecode from schedule;");
			while(rs.next()) {
				String code = rs.getString("coursecode");
				courseCodes.add(code);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseCodes;
	}
	
	public String getComboChangeValue(String input) {
		connection = DBConnection.getDBConnection();
		String result="";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select course.*,schedule.* from course inner join schedule on "
					+ "schedule.courseCode=course.code where schedule.courseCode='"+input+"'");
			while(rs.next()) {
				String semester = rs.getString("semester");
				String time = rs.getString("timeslot");
				String name = rs.getString("name");
				
				result = semester+"_"+time+"_"+name;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public int getEnrollmentId(String rollNumber) {
		connection = DBConnection.getDBConnection();
		int enrollmentId = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select id from enrollment where enrollment.studentId='"+rollNumber+"';");
			while(rs.next()) {
				enrollmentId = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return enrollmentId;
	}
	
	public int getScheduleId(String day,String courseCode) {
		connection = DBConnection.getDBConnection();
		int scheduleId = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * from schedule where schedule.day='"+day
					+ "' and schedule.courseCode='"+courseCode+"'");
			while (rs.next()) {
				scheduleId = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return scheduleId;
	}
	
	public String getCourseCodeFromDB(int scheduleId) {
		connection = DBConnection.getDBConnection();
		String courseCode = "";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select courseCode from schedule where schedule.id='"+scheduleId+"';");
			while(rs.next()) {
				courseCode =rs.getString("courseCode");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return courseCode;
	}
	
	public int createAttendance(Attendance attendance) {
		connection = DBConnection.getDBConnection();
		int rowEffected = 0;
		try {
			pstmt = connection.prepareStatement("INSERT INTO `attendance` "
					+ "(`enrollmentId`, `scheduleId`, `date`, `isPresent`) VALUES "
					+ "(?, ?, ?, ?);");
			pstmt.setInt(1, attendance.getEnrollmentId());
			pstmt.setInt(2, attendance.getScheduleId());
			Date date = Date.valueOf(attendance.getDate());
			pstmt.setDate(3, date);
			pstmt.setBoolean(4, attendance.isPresent());
			rowEffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	public ObservableList<Attendance> getAttendanceList(String date){
		ObservableList<Attendance> attendance = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select course.*,schedule.*,enrollment.*,attendance.* from course join schedule on"
					+ " course.code=schedule.courseCode join attendance on attendance.scheduleId=schedule.id"
					+ " join enrollment on enrollment.id=attendance.enrollmentId where attendance.date='"+date+"';");
			int count=1;
			while(rs.next()) {
				String status = "";
				if(rs.getBoolean("isPresent") == true) 
					status ="Present";
				else
					status = "Absent";
				
				attendance.add(new Attendance(
						count,
						rs.getString("studentname"),
						rs.getString("year"),
						rs.getString("name"),
						rs.getString("studentid"),
						rs.getDate("date").toLocalDate(),
						status));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		return attendance;
	}
	
	public ObservableList<Attendance> getAttendanceList(String date,int scheduleId){
		ObservableList<Attendance> attendance = FXCollections.observableArrayList();
		connection = DBConnection.getDBConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select course.*,schedule.*,enrollment.*,attendance.* from course join schedule on"
					+ " course.code=schedule.courseCode join attendance on attendance.scheduleId=schedule.id"
					+ " join enrollment on enrollment.id=attendance.enrollmentId where attendance.date='"+date+"' and"
							+ " attendance.scheduleid='"+scheduleId+"';");
			int count=1;
			while(rs.next()) {
				String status = "";
				if(rs.getBoolean("isPresent") == true) 
					status ="Present";
				else 
					status = "Absent";
					
					
				attendance.add(new Attendance(
						count,
						rs.getString("studentName"),
						rs.getString("year"),
						rs.getString("name"),
						rs.getString("studentid"),
						rs.getDate("date").toLocalDate(),
						status));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		return attendance;
	}
	
	public int idDoesExist(String date,int scheduleId) {
		connection = DBConnection.getDBConnection();
		int idCount =0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(id) from attendance where attendance.date='"+date+"' and"
					+ " attendance.scheduleId='"+scheduleId+"';");
			while (rs.next()) {
				idCount = rs.getInt("count(id)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return idCount;
	}
	
	public String getLatestDate() {
		connection = DBConnection.getDBConnection();
		String latestdate = "";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select max(date) from attendance");
			while(rs.next()) {
				latestdate =rs.getString("max(date)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return latestdate;
	}
	
	private int latestId(String date) {
		connection = DBConnection.getDBConnection();
		int id = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select max(id) from attendance where attendance.date='"+date+"';");
			while(rs.next()) {
				id =rs.getInt("max(id)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public int getScheduleIdFromLatestAttendanceId(String date) {
		connection = DBConnection.getDBConnection();
		int id = latestId(date);
		int scheduleId = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * from attendance where attendance.id='"+id+"';");
			while(rs.next()) {
				scheduleId = rs.getInt("scheduleId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return scheduleId;
	}
	
	public int updateAttendance(Attendance attendance) {
		connection = DBConnection.getDBConnection();
		int rowEffected = 0;
		try {
			pstmt = connection.prepareStatement("UPDATE `attendance` SET "
					+ "`isPresent` = ? WHERE (`enrollmentId` = ? and `date` = ? and `scheduleId` = ?);"
					+ "");
			pstmt.setBoolean(1, attendance.isPresent());
			pstmt.setInt(2, attendance.getEnrollmentId());
			Date date = Date.valueOf(attendance.getDate());
			pstmt.setDate(3,date);
			pstmt.setInt(4, attendance.getScheduleId());
			rowEffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
}
