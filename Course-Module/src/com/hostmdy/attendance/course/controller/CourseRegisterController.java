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

public class CourseRegisterController implements Initializable{

    @FXML
    private Button btnAddBook;

    @FXML
    private Label lblBack;

    @FXML
    private TextField tfBookcode;

    @FXML
    private TextField tfBookcredit;

    @FXML
    private TextField tfBookname;
    
    @FXML
    private ComboBox<String> cobSemester;
    
    private final CourseDAO courseDAO = new CourseDAO();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processAddBook(ActionEvent event) {
    	if(tfBookcredit.getLength() == 0 || tfBookcode.getLength() == 0 || tfBookname.getLength() ==0 || cobSemester.getValue()==null ) {
    		String title = "Required data";
    		String message = "Please fill out empty fields!";
    		MyNotificationType notitype = MyNotificationType.ERROR;
    		Duration dismissTime = Duration.seconds(3);
    		myNotification.getNotification(title, message, notitype, dismissTime);
    	}else {
    	String coursename = tfBookname.getText().trim();
    	String coursecode = tfBookcode.getText().trim();
    	int credit = Integer.parseInt(tfBookcredit.getText().trim());
    	String semester = cobSemester.getValue();
    	
    	Course course = new Course(coursename, coursecode, credit, semester);
    	int rowEffected = this.courseDAO.createCourse(course);
    	
    	clear();
    	
    	String title = "Add Course";
		String message = "Successfully add course!";
		MyNotificationType notitype = MyNotificationType.SUCCESS;
		Duration dismissTime = Duration.seconds(3);
		myNotification.getNotification(title, message, notitype, dismissTime);
    	
    	}

    }

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide(); 
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/course/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.show();
    	

    }
    
   private void clear() {
	   tfBookname.clear();
	   tfBookcode.clear();
	   tfBookcredit.clear();
	   //cobSemester.getSelectionModel().selectedItemProperty().se;
	   cobSemester.setPromptText(null);
   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ObservableList<String> Semester =FXCollections.observableArrayList(
				"First Semester","Second Semester"
				);
		cobSemester.setItems(Semester);
		
	}

}
