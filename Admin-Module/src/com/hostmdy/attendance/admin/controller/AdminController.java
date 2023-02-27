package com.hostmdy.attendance.admin.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.admin.model.AdminDAO;
import com.hostmdy.attendance.course.style.CourseStyleLoader;
import com.hostmdy.attendance.course.view.CourseViewLoader;
import com.hostmdy.attendance.enrollment.style.EnrollmentStyleLoader;
import com.hostmdy.attendance.enrollment.view.EnrollmentViewLoader;
import com.hostmdy.attendance.report.view.ReportViewLoader;
import com.hostmdy.attendance.schedule.style.ScheduleStyleLoader;
import com.hostmdy.attendance.schedule.view.ScheduleViewLoader;
import com.hostmdy.attendance.student.style.StudentStyleLoader;
import com.hostmdy.attendance.student.view.StudentViewLoader;
import com.hostmdy.attendance.style.AttendanceStyleLoader;
import com.hostmdy.attendance.teacher.style.TeacherStyleLoader;
import com.hostmdy.attendance.teacher.view.TeacherViewLoader;
import com.hostmdy.attendance.teacher_course.style.TeacherCourseStyleLoader;
import com.hostmdy.attendance.teacher_course.view.TeacherCourseViewLoader;
import com.hostmdy.attendance.view.AttendanceViewLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController implements Initializable{

    @FXML
    private Button btnAttendance;

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnEnrollemnt;

    @FXML
    private Button btnRelationship;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTeachers;

    @FXML
    private Label lblStcount;

    @FXML
    private Label lblTrcount;
    
    private AdminDAO adminDAO = new AdminDAO();
    
    private final AttendanceViewLoader attendanceViewLoader = new AttendanceViewLoader();
    
    private final AttendanceStyleLoader attendanceStyleLoader = new AttendanceStyleLoader();

    @FXML
    void processAttendance(ActionEvent event) {
    	Stage attendanceStage= new Stage();
    	attendanceStage.hide();
		Parent attendanceUI = attendanceViewLoader.getAttendanceUI();
		Scene scene = new Scene(attendanceUI);		
		scene.getStylesheets().add(attendanceStyleLoader.getStyleURL());
		attendanceStage.setScene(scene);
		
		attendanceStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		attendanceStage.getIcons().add(image);	
		
		attendanceStage.setResizable(false);
		attendanceStage.setMaximized(false);
		attendanceStage.show();
		
		
    }
    
    private final CourseViewLoader courseViewLoader = new CourseViewLoader();
    
    private final CourseStyleLoader courseStyleLoader = new CourseStyleLoader();

    @FXML
    void processCourse(ActionEvent event) {
    	
    	Stage courseStage= new Stage();
    	courseStage.hide();
		Parent courseUI = courseViewLoader.getCourseUI();
		Scene scene = new Scene(courseUI);		
		scene.getStylesheets().add(courseStyleLoader.getStyleURL());
		courseStage.setScene(scene);

		courseStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		courseStage.getIcons().add(image);	
		
		courseStage.setResizable(false);
		courseStage.setMaximized(false);
		courseStage.show();

    }
    
    private final EnrollmentViewLoader enrollmentViewLoader = new EnrollmentViewLoader();
    
    private final EnrollmentStyleLoader enrollmentStyleLoader = new EnrollmentStyleLoader();


    @FXML
    void processEnrollment(ActionEvent event) {
    	Stage enrollmentStage= new Stage();
    	enrollmentStage.hide();
		Parent enrollmentUI = enrollmentViewLoader.getEnrollmentUI();
		Scene scene = new Scene(enrollmentUI);		
		scene.getStylesheets().add(enrollmentStyleLoader.getStyleURL());
		enrollmentStage.setScene(scene);
		
		enrollmentStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		enrollmentStage.getIcons().add(image);
		
		enrollmentStage.setResizable(false);
		enrollmentStage.setMaximized(false);
		enrollmentStage.show();


    }
  
    private TeacherCourseViewLoader teacherCourseViewLoader = new TeacherCourseViewLoader();
    
    private TeacherCourseStyleLoader teacherCourseStyleLoader = new TeacherCourseStyleLoader();

    @FXML
    void processRelationship(ActionEvent event) {
    	Stage trCourseStage= new Stage();
    	trCourseStage.hide();
		Parent trCourseUI = teacherCourseViewLoader.getTeacherCourseUI();
		Scene scene = new Scene(trCourseUI);		
		scene.getStylesheets().add(teacherCourseStyleLoader.getStyleURL());
		trCourseStage.setScene(scene);

		trCourseStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		trCourseStage.getIcons().add(image);
		
		trCourseStage.setResizable(false);
		trCourseStage.setMaximized(false);
		trCourseStage.show();
    }
    
    private final ReportViewLoader reportViewLoader = new ReportViewLoader();

    @FXML
    void processReport(ActionEvent event) {
    	Stage reportStage= new Stage();
    	reportStage.hide();
		Parent trCourseUI = reportViewLoader.getReportUI();
		Scene scene = new Scene(trCourseUI);		
//		scene.getStylesheets().add(teacherCourseStyleLoader.getStyleURL());
		reportStage.setScene(scene);
		reportStage.setTitle("Attendance Management System");

		reportStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		reportStage.getIcons().add(image);
		
		reportStage.setResizable(false);
		reportStage.setMaximized(false);
		reportStage.show();
    }
    
    private final ScheduleViewLoader scheduleViewLoader = new ScheduleViewLoader();
    
    private final ScheduleStyleLoader scheduleStyleLoader = new ScheduleStyleLoader();

    @FXML
    void processSchedule(ActionEvent event) {
    	
    	Stage scheduleStage= new Stage();
    	scheduleStage.hide();
		Parent scheduleUI = scheduleViewLoader.getScheduleUI();
		Scene scene = new Scene(scheduleUI);		
		scene.getStylesheets().add(scheduleStyleLoader.getStyleURL());
		scheduleStage.setScene(scene);

		scheduleStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		scheduleStage.getIcons().add(image);
		
		scheduleStage.setResizable(false);
		scheduleStage.setMaximized(false);
		scheduleStage.show();

    }
        
    private final StudentViewLoader studentViewLoader =  new StudentViewLoader();
    
    private final StudentStyleLoader studentStyleLoader = new StudentStyleLoader();
    
    @FXML
    void processStudents(ActionEvent event) {
		Stage studentStage= new Stage();
		studentStage.hide();
		Parent studentUI = studentViewLoader.getStudentUI();
		Scene scene = new Scene(studentUI);		
		scene.getStylesheets().add(studentStyleLoader.getStyleURL());
		studentStage.setScene(scene);

		studentStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		studentStage.getIcons().add(image);
		
		studentStage.setResizable(false);
		studentStage.setMaximized(false);
		studentStage.show();

    }
    
    private TeacherViewLoader teacherViewLoader = new TeacherViewLoader();
    
    private TeacherStyleLoader teacherStyleLoader = new TeacherStyleLoader();

    @FXML
    void processTeachers(ActionEvent event) {
    	Stage teacherStage= new Stage();
    	teacherStage.hide();
		Parent teacherUI = teacherViewLoader.getTeacherUI();
		Scene scene = new Scene(teacherUI);		
		scene.getStylesheets().add(teacherStyleLoader.getStyleURL());
		teacherStage.setScene(scene);

		teacherStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("attendancelogo.png"));
		teacherStage.getIcons().add(image);
		
		teacherStage.setResizable(false);
		teacherStage.setMaximized(false);
		teacherStage.show();
    }
    
    @FXML
    void processStdRefresh(MouseEvent event) {
		lblStcount.setText(String.valueOf(this.adminDAO.getStdRowCount("student")));
    }
    
    @FXML
    void processTrRefresh(MouseEvent event) {
		lblTrcount.setText(String.valueOf(this.adminDAO.getRowCount("teacher")));
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lblTrcount.setText(String.valueOf(this.adminDAO.getRowCount("teacher")));
		lblStcount.setText(String.valueOf(this.adminDAO.getStdRowCount("student")));

		
	}

}
