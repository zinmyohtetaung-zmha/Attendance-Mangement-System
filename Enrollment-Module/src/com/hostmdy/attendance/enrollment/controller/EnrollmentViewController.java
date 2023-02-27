package com.hostmdy.attendance.enrollment.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.enrollment.model.Enrollment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EnrollmentViewController implements Initializable{

    @FXML
    private Label lblAcademinYear;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblMajor;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStudentid;

    @FXML
    private Label lblYear;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Enrollment enrollment = Enrollment.getInstance();
		if(enrollment != null) {
			lblStudentid.setText(String.valueOf(enrollment.getStudentid()));
			lblName.setText(String.valueOf(enrollment.getStudentname()));
			lblYear.setText(String.valueOf(enrollment.getYear()));
			lblMajor.setText(String.valueOf(enrollment.getMajor()));
			lblAcademinYear.setText(String.valueOf(enrollment.getAcademicyear()));
		}
		
	}

}
