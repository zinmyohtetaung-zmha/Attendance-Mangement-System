package com.hostmdy.attendance.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getDBConnection() {
		String url = "jdbc:mysql://localhost:3306/attendancedb?useSSL=false";
		String user = "root";
		String password = "1234";
		
		Connection connection = null;
		 
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}

}
