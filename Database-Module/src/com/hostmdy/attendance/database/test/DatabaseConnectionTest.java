package com.hostmdy.attendance.database.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.hostmdy.attendance.database.connection.DBConnection;

class DatabaseConnectionTest {

	@Test
	void dbConnectionNotNullTest() {
		
		Connection connection = DBConnection.getDBConnection();
		assertNotNull(connection);		
		System.out.println("Successfully connected to DB");
	}

}
