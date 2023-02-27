package com.hostmdy.attendance.teacher.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.teacher.model.Teacher;
import com.hostmdy.attendance.teacher.model.TeacherDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TeacherUpdateController implements Initializable {

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cbFaculty;

    @FXML
    private Label lblBack;

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
    private Label tfTeacherID;
    
    private String status;
    
    private final Teacher teacherInstance = Teacher.getInstance();
    
    private final TeacherDAO teacherDAO = new TeacherDAO();

    private final MyNotification myNotification = new MyNotification();
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage teacherStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherSectionUI.fxml"));
		Scene scene = new Scene(root);
		teacherStage.hide(); 
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher/style/style.css").toExternalForm());
		teacherStage.setScene(scene);
		teacherStage.setTitle("Attendance Management Systerm");
		teacherStage.show();
    }

    @FXML
    void processConfirm(ActionEvent event) throws IOException {
    	String teacherID= tfTeacherID.getText();
    	String firstName = tfFname.getText();
    	String lastName = tfLname.getText();
    	String email = tfEmail.getText();
    	String address = tfAddress.getText();
    	String faculty = cbFaculty.getValue();
    	String phone = tfPhone.getText();
    	String nrc = tfNrc.getText();
    	status="Updated";
	    	Teacher teacher = new Teacher(teacherID, firstName, lastName, email, phone, nrc, address, faculty, status);
	    	
	    	int rowEffected = teacherDAO.editTeacher(teacher);
	    	if(rowEffected >0) {
	    		Stage teacherStage = (Stage) ((Button)event.getSource()).getScene().getWindow();   	
	        	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherSectionUI.fxml"));
	    		Scene scene = new Scene(root);
	    		teacherStage.hide();
	    		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher/style/register.css").toExternalForm());
	    		teacherStage.setScene(scene);
	    		teacherStage.show();
	    		
	    		String title = "Update Student";
	    		String message = "Successfully update!";
	    		MyNotificationType notitype = MyNotificationType.SUCCESS;
	    		Duration dismissTime = Duration.seconds(3);
	    		myNotification.getNotification(title, message, notitype, dismissTime);
	    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> faulty = FXCollections.observableArrayList(
				"IT","Maths","English","Chemistry"
					);
		cbFaculty.setItems(faulty);
//		lblIdno.setText(String.valueOf(teacherInstance.getId()));
		tfTeacherID.setText(teacherInstance.getStaffId());
		if(teacherInstance.getStatus()=="Updated") {
			tfAddress.setText(teacherInstance.getAddress());
			tfEmail.setText(teacherInstance.getEmail());
			cbFaculty.setValue(teacherInstance.getFaculty());
			tfFname.setText(teacherInstance.getFirstName());
			tfLname.setText(teacherInstance.getLastName());
			tfNrc.setText(teacherInstance.getNrc());
			tfPhone.setText(teacherInstance.getPhone());
//			tfTeacherID.setText(teacherInstance.getStaffId());
		}
		
	}

}
