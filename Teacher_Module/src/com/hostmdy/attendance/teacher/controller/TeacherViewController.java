package com.hostmdy.attendance.teacher.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.teacher.model.Teacher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TeacherViewController implements Initializable{

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFaculty;

    @FXML
    private Label lblFname;

    @FXML
    private Label lblLname;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblnrc;

    @FXML
    private Label tfTeacherID;

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherSectionUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Teacher teacher =Teacher.getInstance();
		if(teacher != null) {
			tfTeacherID.setText(teacher.getStaffId());
			lblFname.setText(teacher.getFirstName());
			lblLname.setText(teacher.getLastName());
			lblPhone.setText(teacher.getPhone());
			lblEmail.setText(teacher.getEmail());
			lblnrc.setText(teacher.getNrc());
			lblAddress.setText(teacher.getAddress());
			lblFaculty.setText(teacher.getFaculty());
		
			
		}
		
	}

}
