package com.hostmdy.attendance.common.main_login;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hostmdy.attendance.database.connection.DBConnection;
import com.hostmdy.attendance.utility.crypto.PasswordValidator;

public class SigninDAO {
	
	private Connection connection;
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
	
	boolean isCredentialsValid(String email,String password, String role) {
		connection = DBConnection.getDBConnection();
		String storedPassword = null;
		boolean signinOk = false;
		
		try {
			pStmt = connection.prepareStatement("select email,password from "+role+
					" where email=?;");
			pStmt.setString(1, email);
			rs = pStmt.executeQuery();
			
			while (rs.next()) {
				storedPassword = rs.getString("password");				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		if (storedPassword != null) {
			try {
				signinOk = PasswordValidator.validatePassword(password, storedPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return signinOk;
		
		
	}
	

}
