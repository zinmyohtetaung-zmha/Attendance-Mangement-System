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

public class TeacherRegisterController implements Initializable{

    @FXML
    private Button btnAddTeacher;

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
    private TextField tfTeacherID;
    
    private final MyNotification myNotification = new MyNotification();
    
    private final TeacherDAO teacherDAO = new TeacherDAO();
    
    String status;
    
    Teacher teacherInstance = Teacher.getInstance();
    
    @FXML
    void processAddTeacher(ActionEvent event) throws IOException {
    	if(tfTeacherID.getLength() == 0 || tfFname.getLength() == 0 || tfEmail.getLength() == 0 || tfPhone.getLength() == 0 || tfNrc.getLength() == 0 || tfAddress.getLength() == 0 || cbFaculty.getValue() == null) {
    		String title = "Required data";
    		String message = "Please fill out empty fields!";
    		MyNotificationType notitype = MyNotificationType.ERROR;
    		Duration dismissTime = Duration.seconds(3);
    		myNotification.getNotification(title, message, notitype, dismissTime);
    	}else {
    	
    	String teacherID= tfTeacherID.getText().trim();
    	String firstName = tfFname.getText().trim();
    	String lastName = tfLname.getText().trim();
    	String email = tfEmail.getText().trim();
    	String address = tfAddress.getText().trim();
    	String faculty = cbFaculty.getValue().trim();
    	String phone = tfPhone.getText().trim();
    	String nrc = tfNrc.getText().trim();
    	status="Normal";
	    	Teacher teacher = new Teacher(teacherID, firstName, lastName, email, phone, nrc, address, faculty, status);
	    	
	    	int rowEffected=teacherDAO.createTeacher(teacher);
	    	if(rowEffected>0) {
	    		System.out.println("Success");
	    		tfTeacherID.clear();
	    		tfFname.clear();
	    		tfEmail.clear();
	    		tfAddress.clear();
	    		tfLname.clear();
	    		tfNrc.clear();
	    		tfPhone.clear();
	    		cbFaculty.setPromptText("Choose a faculty");
//	    		
//	    		BorderPane mainRoot = FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherSectionUI.fxml"));
//				Scene mainScene = new Scene(mainRoot);
//				//Stage mainStage = new Stage();
//				Stage mainStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
//				mainStage.hide();
//				mainStage.setScene(mainScene);
//				mainStage.setTitle("Teacher Section UI");
//				mainStage.show();
	    		
    	}
	    	String title = "Add Teacher";
			String message = "Successfully add teacher!";
			MyNotificationType notitype = MyNotificationType.SUCCESS;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
    	}
    	
    }

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage teacherStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherSectionUI.fxml"));
		Scene scene = new Scene(root);
		teacherStage.hide(); 
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher/style/style.css").toExternalForm());
		teacherStage.setScene(scene);
		teacherStage.show();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> faulty = FXCollections.observableArrayList(
				"Civil","Mechanical","Electrical","Chemical","Systems"
					);
		cbFaculty.setItems(faulty);
	}

}
