package com.hostmdy.attendance.enrollment.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.enrollment.model.Enrollment;
import com.hostmdy.attendance.enrollment.model.EnrollmentDAO;
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

public class EnrollmentUpdateController implements Initializable{

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cobAcademicYear;

    @FXML
    private ComboBox<String> cobMajor;

    @FXML
    private ComboBox<String> cobYear;
    
    @FXML
    private Label lblName;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblStudentId;
    
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    
    private final MyNotification myNotification = new MyNotification();


    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	 
    }

    @FXML
    void processConfirm(ActionEvent event) throws IOException {
    	
    	String studentid = lblStudentId.getText().trim();
    	String studentname = lblName.getText();
    	String year = cobYear.getValue();
    	String major = cobMajor.getValue();
    	String academicyear = cobAcademicYear.getValue();
    	
    	Enrollment enrollment = new Enrollment( studentid, studentname, year, major, academicyear);
    	
    	int rowEffected = this.enrollmentDAO.updateEnrollment(enrollment);
    	
    	Stage studentStage = (Stage)((Button) event.getSource()).getScene().getWindow();  	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	
    	String title = "Enrollment Update Student";
		String message = "Successfully enrolment update student!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Enrollment enrollment = Enrollment.getInstance();
		if(enrollment != null) {
			lblStudentId.setText(String.valueOf(enrollment.getStudentid()));
			lblName.setText(String.valueOf(enrollment.getStudentname()));
			cobYear.setValue(enrollment.getYear());
			cobMajor.setValue(enrollment.getMajor());
			cobAcademicYear.setValue(enrollment.getAcademicyear());
		}
		
//		ObservableList<String> studentid = enrollmentDAO.getStudentIdList("select studentid from student;");
//		lblStudentId.setItems(studentid);
//		
//		cobStudentID.valueProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue observable, String oldValue, String newValue) {
//				// TODO Auto-generated method stub
//				
//				String selcetedName = enrollmentDAO.getStdName(newValue);
//				lblName.setText(selcetedName);
//				
//			}
//			
//		});
		ObservableList<String> year = FXCollections.observableArrayList(
				"1st Year","2nd Year","3th Year","4th Year","5th Year"
				);
		cobYear.setItems(year);
		
		ObservableList<String> major = FXCollections.observableArrayList(
				"Civil","Mechanical","Electrical","Chemical","Systems"
				);
		cobMajor.setItems(major);
		
		ObservableList<String> academicyear = FXCollections.observableArrayList(
				"2022-2023","2023-2024","2024-2025","2025-2026","2026-2027"
				);
		cobAcademicYear.setItems(academicyear);
		
	}

}
