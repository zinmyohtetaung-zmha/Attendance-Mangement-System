package com.hostmdy.attendance.course.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.course.model.Course;
import com.hostmdy.attendance.course.model.CourseDAO;
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

public class CourseUpdateController implements Initializable{

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblCourseCode;

    @FXML
    private TextField tfBookcredit;

    @FXML
    private TextField tfBookname;
    
    @FXML
    private ComboBox<String> cobSemester;
    
    private final CourseDAO courseDAO = new CourseDAO();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage updateStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseUI.fxml"));
		Scene scene = new Scene(root);
		updateStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/course/style/style.css").toExternalForm());
		updateStage.setScene(scene);
		updateStage.show();
		 

    }

    @FXML
    void processConfirm(ActionEvent event) throws IOException{
  
    	String coursecode = lblCourseCode.getText().trim();
    	String coursename = tfBookname.getText().trim();
    	int coursecredit = Integer.parseInt(tfBookcredit.getText().trim());
    	String semester = cobSemester.getValue();
    	
    	Course course = new Course(coursename, coursecode, coursecredit, semester);
    	int rowEffect = this.courseDAO.updateCourse(course);
    	
    		
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseUI.fxml"));
    	Scene scene = new Scene(root);
    	Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
    	updateStage.hide(); 
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/course/style/style.css").toExternalForm());
		updateStage.setScene(scene);
    	updateStage.show();
    	
    	String title = "Update course";
		String message = "Successfully update!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ObservableList<String> Semester =FXCollections.observableArrayList(
				"First Semester","Second Semester"
				);
		cobSemester.setItems(Semester);
		
		Course course = Course.getInstance();
		if(course != null) {
			lblCourseCode.setText(String.valueOf(course.getCode()));
			tfBookname.setText(String.valueOf(course.getName()));
			tfBookcredit.setText(String.valueOf(course.getCredit()));
			cobSemester.setValue(String.valueOf(course.getSemester()));
		}
		
	}

}
