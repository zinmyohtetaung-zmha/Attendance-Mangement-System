package com.hostmdy.attendance.teacher_course.style;

public class TeacherCourseStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;

	}
}
