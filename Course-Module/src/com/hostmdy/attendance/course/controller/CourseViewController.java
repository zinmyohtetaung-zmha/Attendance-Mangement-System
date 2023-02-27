package com.hostmdy.attendance.course.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.course.model.Course;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CourseViewController implements Initializable{

    @FXML
    private Label lblBack;

    @FXML
    private Label lblBookCode;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblCredit;

    @FXML
    private Label lblName;
    
    @FXML
    private Label lblBookName;
    
    @FXML
    private Label lblSemester;

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/course/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	studentStage.hide(); 

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Course course = Course.getInstance();
		if(course != null) {
			lblName.setText(String.valueOf(course.getName()));
			lblCode.setText(String.valueOf(course.getCode()));
			lblCredit.setText(String.valueOf(course.getCredit()));
			lblBookCode.setText(String.valueOf(course.getCode()));
			lblBookName.setText(String.valueOf(course.getName()));
			lblSemester.setText(String.valueOf(course.getSemester()));
		}
		
	}

}
