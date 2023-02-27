package com.hostmdy.attendance.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.hostmdy.attendance.model.Attendance;
import com.hostmdy.attendance.model.AttendanceDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AttendanceUpdateController implements Initializable {

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblCourseCode;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblYear;

    @FXML
    private Label lblstudentID;

    @FXML
    private RadioButton rbAbsent;

    @FXML
    private RadioButton rbPresent;
    
    private final Attendance attendanceInstance = Attendance.getInstance();
    
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage updateStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/view/AttendanceUI.fxml"));
		Scene scene = new Scene(root);
		updateStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/style/style.css").toExternalForm());
		updateStage.setScene(scene);
		updateStage.show();
    }

    @FXML
    void processConfirm(ActionEvent event) throws IOException {
    	int enrollmentId = attendanceDAO.getEnrollmentId(lblstudentID.getText());
    	LocalDate calendarDate = LocalDate.parse(lblDate.getText());
    	String day = calendarDate.getDayOfWeek().toString();
    	int scheduleId = attendanceDAO.getScheduleId(day, lblCourseCode.getText());    	
    	boolean isPresent;
    	if(rbPresent.isSelected())
    		isPresent = true;
    	else
    		isPresent = false;
    	LocalDate date = LocalDate.parse(lblDate.getText());
    	int rowEffected = 0;
    	Attendance updateAttendance = new Attendance(enrollmentId, scheduleId, date, isPresent);
    	rowEffected = attendanceDAO.updateAttendance(updateAttendance);
    	
    	if(rowEffected > 0) {
    		BorderPane mainRoot = FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/view/AttendanceUI.fxml"));
    		Scene mainScene = new Scene(mainRoot);
    		//Stage mainStage = new Stage();
    		Stage mainStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
    		mainScene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/style/style.css").toExternalForm());
    		mainStage.hide();
    		mainStage.setScene(mainScene);
    		mainStage.show();
    	}else {
    		System.out.println("update Failed");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblCourseCode.setText(attendanceInstance.getCourseCode());
		lblDate.setText(attendanceInstance.getDate().toString());
		lblstudentID.setText(attendanceInstance.getStudentId());
		lblStudentName.setText(attendanceInstance.getStudentName());
		lblYear.setText(attendanceInstance.getYear());
		System.out.println(attendanceInstance.isPresent());
		if(attendanceInstance.isPresent() == true)
			rbPresent.setSelected(true);
		else 
			rbAbsent.setSelected(true);
		
	}

}
