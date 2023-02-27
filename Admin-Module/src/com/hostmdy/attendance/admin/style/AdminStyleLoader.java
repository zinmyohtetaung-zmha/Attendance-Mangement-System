package com.hostmdy.attendance.admin.style;

public class AdminStyleLoader {
	
	public String getStyleURL() {
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;
		
	}

}
