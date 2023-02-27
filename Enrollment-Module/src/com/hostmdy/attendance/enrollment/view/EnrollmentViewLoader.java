package com.hostmdy.attendance.enrollment.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class EnrollmentViewLoader {
	public Parent getEnrollmentUI() {
		
		Parent enrollmentUI  = null;
		
		try {
			enrollmentUI = FXMLLoader.load(getClass().getResource("EnrollmentUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enrollmentUI;
		
	}

}
