package com.hostmdy.attendance.teacher.style;

public class TeacherStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;

	}


}
