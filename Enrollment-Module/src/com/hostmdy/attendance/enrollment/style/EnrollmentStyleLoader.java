package com.hostmdy.attendance.enrollment.style;

public class EnrollmentStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;
	}

}
