package com.hostmdy.attendance.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hostmdy.attendance.database.connection.DBConnection;

public class AdminDAO {
	
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
	
	public int getRowCount(String tableName) {
		connection = DBConnection.getDBConnection();
		int rowNumbers = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(*) from "+tableName+" where status != 'deleted';");
			
			while (rs.next()) {
				rowNumbers = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowNumbers;
	}
	
	public int getStdRowCount(String tableName) {
		connection = DBConnection.getDBConnection();
		int rowNumbers = 0;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(*) from "+tableName+";");
			
			while (rs.next()) {
				rowNumbers = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowNumbers;
	}
	
	

}
