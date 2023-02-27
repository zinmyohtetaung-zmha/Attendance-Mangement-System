package com.hostmdy.attendance.schedule.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ScheduleViewLoader {
	
	public Parent getScheduleUI() {
		
		Parent scheduleUI = null;
		
		try {
			scheduleUI = FXMLLoader.load(getClass().getResource("ScheduleUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduleUI;
	}

}
