package com.hostmdy.attendance.common.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/common/main_login/SigninUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/common/style/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Attendance Management System");
			
			Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
			primaryStage.getIcons().add(image);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
