package com.hostmdy.attendance.course.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CourseViewLoader {
	
	public Parent getCourseUI() {
		
		Parent courseUI = null;
		
		try {
			courseUI = FXMLLoader.load(getClass().getResource("CourseUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseUI;
				
				
	}

}
