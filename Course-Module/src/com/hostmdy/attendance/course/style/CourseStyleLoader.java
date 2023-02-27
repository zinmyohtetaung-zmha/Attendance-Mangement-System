package com.hostmdy.attendance.course.style;

public class CourseStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;
	}

}
