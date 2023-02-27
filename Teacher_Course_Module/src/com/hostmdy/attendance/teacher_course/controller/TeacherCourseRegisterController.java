package com.hostmdy.attendance.teacher_course.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.teacher_course.model.TeacherCourse;
import com.hostmdy.attendance.teacher_course.model.TeacherCourseDAO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TeacherCourseRegisterController implements Initializable {

    @FXML
    private ComboBox<String> cbCourseCode;

    @FXML
    private ComboBox<String> cbTeacherId;
    
    @FXML
    private ComboBox<String> cbAcademicYear;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblCourseId;

    @FXML
    private Label lblTeacherName;
    
    private final TeacherCourseDAO teacherCourseDAO = new TeacherCourseDAO();

    @FXML
    void processAddTeacherCourse(ActionEvent event) {
    	String teacherId = cbTeacherId.getValue();
    	String courseCode = cbCourseCode.getValue();
    	String academicYear = cbAcademicYear.getValue();
    	int lecturerId = teacherCourseDAO.getDatabaseId("Select * from teacher where teacher.staff_id='"+teacherId+"';");
    	int courseId =teacherCourseDAO.getDatabaseId("Select * from course where course.code='"+courseCode+"';");
    	System.out.println("lecturer id ="+lecturerId);
    	System.out.println("course id = "+courseId);
    	if(teacherId != null && courseCode != null && academicYear != null) {
	    	TeacherCourse teacherCourse = new TeacherCourse(lecturerId, courseId, academicYear);
	    	int rowEffected = teacherCourseDAO.createTeacherCourse(teacherCourse);
	    	if(rowEffected >0) {
	    		cbAcademicYear.setPromptText("Choose Teacher ID");
	    		cbCourseCode.setPromptText("Choose Course Code");
	    		cbAcademicYear.setPromptText("Choose Academic Year");
	    	}
    	}
    }

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage studentStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher_course/view/TeacherCourseSectionUI.fxml"));
		Scene scene = new Scene(root);
		studentStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher_course/style/style.css").toExternalForm());
		studentStage.setScene(scene);
		studentStage.setTitle("Attendance Mangement System");
		studentStage.show();

    }

    @FXML
    void processClose(ActionEvent event) {

    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> years = FXCollections.observableArrayList(
				"2022-2023","2023-2024","2024-2025","2025-2026","2025-2026");
		cbAcademicYear.setItems(years);
		
		ObservableList<String> teacherIds = teacherCourseDAO.getTeacherIdList();
		cbTeacherId.setItems(teacherIds);
		
		lblCourseId.setVisible(false);
		
		cbTeacherId.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String teacherName = teacherCourseDAO.getTeacherName(newValue);
				lblTeacherName.setText(teacherName);		}
		});
		
		ObservableList<String> courseCodes = teacherCourseDAO.getCourseCodeList();
		cbCourseCode.setItems(courseCodes);
	}

}
