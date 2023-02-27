package com.hostmdy.attendance.student.style;

public class StudentStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;

	}

}
