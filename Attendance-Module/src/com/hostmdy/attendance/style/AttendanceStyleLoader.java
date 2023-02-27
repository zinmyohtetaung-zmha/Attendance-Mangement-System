package com.hostmdy.attendance.style;

public class AttendanceStyleLoader {
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;
	}
}
