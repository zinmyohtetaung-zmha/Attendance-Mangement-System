package com.hostmdy.attendance.schedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.schedule.model.Schedule;
import com.hostmdy.attendance.schedule.model.ScheduleDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScheduleRegisterController implements Initializable{

    @FXML
    private Button btnAdd;
    
    @FXML
    private ComboBox<String> cobCourseCode;
    
    @FXML
    private Label lblCourseName;

    @FXML
    private ComboBox<String> cobDay;

    @FXML
    private ComboBox<String> cobTimeslot;

    @FXML
    private Label lblBack;
    
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processAdd(ActionEvent event) {
    	if(cobCourseCode.getValue() == null || cobDay.getValue() == null || cobTimeslot.getValue() == null) {
    		String title = "Required data";
    		String message = "Please fill out empty fields!";
    		MyNotificationType notitype = MyNotificationType.ERROR;
    		Duration dismissTime = Duration.seconds(3);
    		myNotification.getNotification(title, message, notitype, dismissTime);
    	}else {
    	
    	String coursecode = cobCourseCode.getValue().trim();
    	String coursename = lblCourseName.getText().trim();
    	String day = cobDay.getValue().trim();
    	String timeslot = cobTimeslot.getValue().trim();
    	
    	Schedule schedule = new Schedule(coursecode, coursename, day, timeslot);
    	
    	int rowEffected = this.scheduleDAO.createSchedule(schedule);


    	String title = "Add Course";
		String message = "Successfully add course!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);
    	}
    	
    }

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
		
		ObservableList<String> coursecode = scheduleDAO.getCourseCodeList("select code from course;");
		cobCourseCode.setItems(coursecode);
		
		cobCourseCode.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				
				String selectCourseName = scheduleDAO.getCourseName(newValue);
				lblCourseName.setText(selectCourseName);
				
			}
		});
		
		ObservableList<String> day =FXCollections.observableArrayList(
				"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
				);
		cobDay.setItems(day);
		
		ObservableList<String> timeslot =FXCollections.observableArrayList(
				"09:00 AM - 11:00 AM","11:00 AM - 01:00 PM","02:00 PM - 04:00 PM"
				);
		cobTimeslot.setItems(timeslot);
	}

}
