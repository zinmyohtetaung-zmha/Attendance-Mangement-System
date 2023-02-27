package com.hostmdy.attendance.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.student.model.Student;
import com.hostmdy.attendance.student.model.StudentDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentUpdateController implements Initializable{

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblIdno;

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
    void processConfirm(ActionEvent event) throws IOException {
    	int id = Integer.parseInt(lblIdno.getText().trim());
    	String studentid = tfStudentID.getText().trim();
    	String firstname = tfFname.getText().trim();
    	String lastname = tfLname.getText().trim();
    	String email = tfEmail.getText().trim();
    	String phone = tfPhone.getText().trim();
    	String nrc = tfNrc.getText().trim();
    	String address = tfAddress.getText().trim();
    	
    	Student student = new Student(id, studentid, firstname, lastname, email, phone, nrc, address);
    	
    	int rowEffected = this.studentDAO.updateStudent(student); 
    	
    	Stage studentStage = (Stage) ((Button)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/student/view/StudentUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/student/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	
    	String title = "Update Student";
		String message = "Successfully update!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);
   
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Student student= Student.getInstance();
		if(student != null) {
			lblIdno.setText(String.valueOf(student.getId()));
			tfStudentID.setText(String.valueOf(student.getStudentid()));
			tfFname.setText(student.getFirstname());
			tfLname.setText(student.getLastname());
			tfEmail.setText(student.getEmail());
			tfPhone.setText(student.getPhone());
			tfNrc.setText(student.getNrc());
			tfAddress.setText(student.getAddress());
		}
		
	}

}
