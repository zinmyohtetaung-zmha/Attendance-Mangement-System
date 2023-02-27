package com.hostmdy.attendance.enrollment.main;

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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Enrollment Table");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
