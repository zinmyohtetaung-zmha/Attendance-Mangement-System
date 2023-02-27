package com.hostmdy.attendance.schedule.style;

public class ScheduleStyleLoader {
	
	public String getStyleURL() {
		
		String urlString = getClass().getResource("style.css").toExternalForm();
		return urlString;
		
	}

}
