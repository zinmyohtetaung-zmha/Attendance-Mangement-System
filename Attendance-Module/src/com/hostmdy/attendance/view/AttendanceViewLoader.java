package com.hostmdy.attendance.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AttendanceViewLoader {
	public Parent getAttendanceUI() {
		
		Parent attendanceUI = null;
		
		try {
			attendanceUI = FXMLLoader.load(getClass().getResource("AttendanceUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attendanceUI;
	}
}
