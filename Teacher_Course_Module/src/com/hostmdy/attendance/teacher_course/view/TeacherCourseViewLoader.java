package com.hostmdy.attendance.teacher_course.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TeacherCourseViewLoader {
		public Parent getTeacherCourseUI() {
				
				Parent teacherCourseUI = null;
				
				try {
					teacherCourseUI = FXMLLoader.load(getClass().getResource("TeacherCourseSectionUI.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return teacherCourseUI;
			}

}
