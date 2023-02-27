package com.hostmdy.attendance.student.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class StudentViewLoader {
	public Parent getStudentUI() {

		Parent studentUI = null;
		try {
			studentUI = FXMLLoader.load(getClass().getResource("StudentUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentUI;
	}


}
