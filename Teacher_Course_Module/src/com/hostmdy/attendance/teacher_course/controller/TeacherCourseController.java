package com.hostmdy.attendance.teacher_course.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import com.hostmdy.attendance.teacher.model.Teacher;
import com.hostmdy.attendance.teacher_course.model.TeacherCourse;
import com.hostmdy.attendance.teacher_course.model.TeacherCourseDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TeacherCourseController implements Initializable {

    @FXML
    private TableColumn<TeacherCourse, String> academicYear;


    @FXML
    private TableColumn<TeacherCourse, String> courseName;

    @FXML
    private TableColumn<TeacherCourse, Integer> id;

    @FXML
    private Label lblBack;

    @FXML
    private TableView<TeacherCourse> teacherCourseTable;

    @FXML
    private TableColumn<TeacherCourse, String> teacherName;
    
    @FXML
    private TableColumn<TeacherCourse, String> courseCode;


    @FXML
    private TextField tfSearch;
    
    private final TeacherCourseDAO teacherCourseDAO = new TeacherCourseDAO();
    
    private final TeacherCourse tCinstance = TeacherCourse.getInstance();
    

    @FXML
    void processBack(MouseEvent event) {
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void processDelete(ActionEvent event) {
    	TeacherCourse selectedRow = teacherCourseTable.getSelectionModel().getSelectedItem();
    	int rowEffected = 0;
    	if(selectedRow != null) {
    		int courseId = teacherCourseDAO.getDatabaseId("Select id from course where "
    				+ "course.code='"+selectedRow.getCourseCode()+"'");
    		rowEffected = teacherCourseDAO.deleteBycourseId(courseId);
    	}
    	if(rowEffected >0) 
    		showTable();
    	else
    		System.out.println("Error");
    	
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	TeacherCourse selectedRow = teacherCourseTable.getSelectionModel().getSelectedItem();
    	if(selectedRow != null) {
    		tCinstance.setId(selectedRow.getId());
    		tCinstance.setCourseCode(selectedRow.getCourseCode());
    		tCinstance.setYear(selectedRow.getAcademicYear());
    		int courseId = teacherCourseDAO.getDatabaseId("Select id from course where course.code='"+selectedRow.getCourseCode()+"'");
    		int selectedTeacherId = teacherCourseDAO.getTeacherId(courseId);
    		tCinstance.setTeacherId(selectedTeacherId);
    		System.out.println(tCinstance.toString());
    		
    		Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/teacher_course/view/TeacherCourseUpdateUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/teacher_course/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Attendance Management System");
			updateStage.show();
    	}
    }

    @FXML
    void processNew(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher_course/view/TeacherCourseRegister.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher_course/style/register.css").toExternalForm());
		stage.setScene(scene);
	 	stage.hide();
		stage.setTitle("Attendance Management System");
		stage.show();

    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable();
    }
    
    private void showTable() {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
    	courseName.setCellValueFactory(new PropertyValueFactory<>("course"));
    	courseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
    	academicYear.setCellValueFactory(new PropertyValueFactory<>("academicYear"));
    	
    	teacherCourseTable.setItems(teacherCourseDAO.getTeacherCourseData());
    	
    	ObservableList<TeacherCourse>teacherCourse = FXCollections.observableArrayList(teacherCourseDAO.getTeacherCourseData());
    	FilteredList<TeacherCourse> filterTeacherCourse = new FilteredList<>(FXCollections.observableArrayList(teacherCourse));
    	
    	teacherCourseTable.setItems(filterTeacherCourse);
    	tfSearch.textProperty().addListener(
    			(observable, oldValue, newValue) -> teacherCourseTable.setItems(filterList(teacherCourse, newValue.toLowerCase())));
    	
    }
    
    private ObservableList<TeacherCourse>filterList(List<TeacherCourse>list,String searchText){
    	List<TeacherCourse> searchedList = new ArrayList<>();
    	for(TeacherCourse teacherCourse : list) {
    		if(searchResult(teacherCourse,searchText)) {
    			searchedList.add(teacherCourse);
    		}
    	}
    	return FXCollections.observableArrayList(searchedList);
    }
    
    private boolean searchResult(TeacherCourse teacherCourse,String searchText) {
    	return (teacherCourse.getTeacherName().toLowerCase().contains(searchText) ||
    			teacherCourse.getCourse().toLowerCase().contains(searchText)||
    			teacherCourse.getCourseCode().toLowerCase().contains(searchText)||
    			String.valueOf(teacherCourse.getId()).equals(searchText)||
    			teacherCourse.getAcademicYear().toLowerCase().contains(searchText));
    }

    @FXML
    void processSearch(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showTable();
	}

}
