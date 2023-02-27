package com.hostmdy.attendance.common;

import com.hostmdy.attendance.admin.test.AdminModuleTest;
import com.hostmdy.attendance.database.connection.DBConnection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AdminModuleTest adminModuleTest = new AdminModuleTest();
		System.out.println(adminModuleTest.response());
		
		if(DBConnection.getDBConnection() != null)
			System.out.println("Successfull connected to DB");
		else
			System.out.println("Fail to connect!");
	}
	
	

}
