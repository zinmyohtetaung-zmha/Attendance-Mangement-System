package com.hostmdy.attendance.schedule.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/schedule/view/ScheduleUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/schedule/style/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Course Table");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
