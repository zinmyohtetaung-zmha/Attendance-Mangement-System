package com.hostmdy.attendance.teacher_course.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.teacher_course.model.TeacherCourse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TeacherCourseUpdateController implements Initializable{

    @FXML
    private ComboBox<?> cbAcademicYear;

    @FXML
    private ComboBox<?> cbCourseCode;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblTeacherID;

    @FXML
    private Label lblTeacherName;

    @FXML
    void processAddTeacherCourse(ActionEvent event) {

    }

    @FXML
    void processBack(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
//		TeacherCourse teacherCourse = TeacherCourse.getInstance();
//		if(teacherCourse != null) {
//			lblTeacherID.setText(String.valueOf(teacherCourse.getTeacherId()));
//			lblTeacherName.setText(teacherCourse.getTeacherName());
//			cbCourseCode.setValue(teacherCourse.getCourseCode());
//		}
		
		
	}

}
