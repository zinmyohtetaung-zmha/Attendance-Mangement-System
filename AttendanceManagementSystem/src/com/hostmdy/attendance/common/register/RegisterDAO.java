package com.hostmdy.attendance.common.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hostmdy.attendance.admin.model.Admin;
import com.hostmdy.attendance.database.connection.DBConnection;

class RegisterDAO {
	
	private Connection connection;
	private PreparedStatement pStmt;
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	int createAdmin(Admin admin) {
		
		connection = DBConnection.getDBConnection();
		int rowEffected = 0;
		try {
			pStmt = connection.prepareStatement("INSERT INTO `admin` "
					+ "(`firstname`, `lastname`, `middlename`, "
					+ "`email`, `code`, `password`, `nrc`, `phone`, "
					+ "`address`, `role`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);"
					);
			pStmt.setString(1, admin.getFirstnmae());
			pStmt.setString(2, admin.getLastname());
			pStmt.setString(3, admin.getMiddlename());
			pStmt.setString(4, admin.getEmail());
			pStmt.setString(5, admin.getCode());
			pStmt.setString(6, admin.getPassword());
			pStmt.setString(7, admin.getNrc());
			pStmt.setString(8, admin.getPhone());
			pStmt.setString(9, admin.getAddress());
			pStmt.setString(10, admin.getRole());
			
			rowEffected = pStmt.executeUpdate();
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		 
	}
	
	
	

}
