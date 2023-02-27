package com.hostmdy.attendance.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.student.model.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StudentViewController implements Initializable{

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFname;

    @FXML
    private Label lblStdID;

    @FXML
    private Label lblLname;

    @FXML
    private Label lblNrc;

    @FXML
    private Label lblPhone;


    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/student/view/StudentUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/student/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	 

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Student student= Student.getInstance();
		if(student != null) {
			lblStdID.setText(String.valueOf(student.getStudentid()));
			lblFname.setText(student.getFirstname());
			lblLname.setText(student.getLastname());
			lblEmail.setText(student.getEmail());
			lblPhone.setText(student.getPhone());
			lblNrc.setText(student.getNrc());
			lblAddress.setText(student.getAddress());
		}
		
		
		
	}

}
