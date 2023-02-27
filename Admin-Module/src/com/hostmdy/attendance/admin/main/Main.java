package com.hostmdy.attendance.admin.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/admin/view/AdminMainUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/admin/style/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Attendance Management System");
			Image image = new Image(getClass().getResourceAsStream("/com/hostmdy/attendance/admin/main/attendancelogo.png"));
			primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
//			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
