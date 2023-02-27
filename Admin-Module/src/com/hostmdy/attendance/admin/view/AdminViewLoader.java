package com.hostmdy.attendance.admin.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AdminViewLoader {
	
	public Parent getAdminMainUI() {
		
		Parent adminMainUI = null;		
		try {
			adminMainUI = FXMLLoader.load(getClass().getResource("AdminMainUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminMainUI;
	}

}
