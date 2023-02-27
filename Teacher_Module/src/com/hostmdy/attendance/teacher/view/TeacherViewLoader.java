package com.hostmdy.attendance.teacher.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TeacherViewLoader {
	public Parent getTeacherUI() {

		Parent teacherUI = null;
		try {
			teacherUI = FXMLLoader.load(getClass().getResource("TeacherSectionUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherUI;
	}

}
