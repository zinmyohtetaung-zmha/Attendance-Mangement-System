package com.hostmdy.attendance.schedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.schedule.model.Schedule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ScheduleViewController implements Initializable{

    @FXML
    private Label lblBack;

    @FXML
    private Label lblCourseName;

    @FXML
    private Label lblDay;

    @FXML
    private Label lblCourseCode;

    @FXML
    private Label lblTimesllot;

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/schedule/view/ScheduleUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide(); 
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/schedule/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Schedule schedule = Schedule.getInstance();
		if(schedule != null) {
			lblCourseCode.setText(String.valueOf(schedule.getCoursecode()));
			lblCourseName.setText(schedule.getCoursename());
			lblDay.setText(schedule.getDay());
			lblTimesllot.setText(schedule.getTimeslot());
		}
		
	}

}
