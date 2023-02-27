package com.hostmdy.attendance.student.controller;

import java.io.IOException;

import com.hostmdy.attendance.student.model.Student;
import com.hostmdy.attendance.student.model.StudentDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewStudentController {

    @FXML
    private Button btnAddStudent;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFname;

    @FXML
    private TextField tfLname;

    @FXML
    private TextField tfNrc;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfStudentID;
    
    private final StudentDAO studentDAO = new StudentDAO();

    private final MyNotification myNotification = new MyNotification();
    
    
    @FXML
    void processAddStudent(ActionEvent event) {
    	if(tfStudentID.getLength() == 0 || tfFname.getLength() == 0 || tfEmail.getLength() == 0 || tfPhone.getLength() == 0 || tfNrc.getLength() == 0 || tfAddress.getLength() == 0) {
    		String title = "Required data";
    		String message = "Please fill out empty fields!";
    		MyNotificationType notitype = MyNotificationType.ERROR;
    		Duration dismissTime = Duration.seconds(3);
    		myNotification.getNotification(title, message, notitype, dismissTime);
    	}else {
    	
    	String studentid = tfStudentID.getText().trim();
    	String firstname = tfFname.getText().trim();
    	String lastname = tfLname.getText().trim();
    	String email = tfEmail.getText().trim();
    	String phone = tfPhone.getText().trim();
    	String nrc = tfNrc.getText().trim();
    	String address = tfAddress.getText().trim();
    	
    	Student student = new Student(studentid, firstname, lastname, email, phone, nrc, address);
    	int rowEffected = this.studentDAO.createStudent(student);
    	
    	clear();
    	
    	String title = "Add Student";
		String message = "Successfully add student!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);
    	}
    }
    
    private void clear() {
    	tfStudentID.clear();
    	tfFname.clear();
    	tfLname.clear();
    	tfEmail.clear();
    	tfPhone.clear();
    	tfNrc.clear();
    	tfAddress.clear();
    }
    
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

}
