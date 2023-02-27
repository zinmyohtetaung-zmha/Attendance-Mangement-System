package com.hostmdy.attendance.course.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hostmdy.attendance.course.model.Course;
import com.hostmdy.attendance.course.model.CourseDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CourseController implements Initializable{

    @FXML
    private Button btnView;

    @FXML
    private TableColumn<Course, String> code;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, Integer> credit;

    @FXML
    private TableColumn<Course, Integer> id;

    @FXML
    private Label lblBack;

    @FXML
    private TableColumn<Course, String> name;
    
    @FXML
    private TableColumn<Course, String> semester;

    @FXML
    private TextField tfSearch;
    
    private final CourseDAO courseDAO = new CourseDAO();
 
    private final MyNotification myNotification = new MyNotification();
    
    

    @FXML
    void processBack(MouseEvent event) {
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();

    }

    @FXML
    void processDelete(ActionEvent event) {
    	
		Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

		if (selectedCourse != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure to delete!");
			Optional<ButtonType> action = alert.showAndWait();

			if (action.get() == ButtonType.OK) {
				int rowEffected = this.courseDAO.deleteByCourseCode(selectedCourse.getCode());
				Course storedCourse = this.courseDAO.getCourse("code", String.valueOf(selectedCourse.getCode()));

				if (rowEffected > 0) {
					showTable(" select * from course;");

					String title = "Delete course";
					String message = "Successfully delete!";
					MyNotificationType notitype = MyNotificationType.SUCCESS;
					Duration dismissTime = Duration.seconds(3);
					myNotification.getNotification(title, message, notitype, dismissTime);

				} else {

				}
			}
		} else {
			String title = "Not selected course";
			String message = "Please select course!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}

    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	
    	Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedCourse != null) {
    		Course storedCourse = this.courseDAO.getCourse("code", String.valueOf(selectedCourse.getCode()));
    		Course course = Course.getInstance();
    		
    		course.setId(storedCourse.getId());
    		course.setName(storedCourse.getName());
    		course.setCode(storedCourse.getCode());
    		course.setCredit(storedCourse.getCredit());
    		course.setSemester(storedCourse.getSemester());
    		
    		
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseUpdateUI.fxml"));
			Scene scene = new Scene(root);
			Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/course/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Course Update  UI");
			updateStage.show();
    	}else {
    		String title = "Not selected course";
			String message = "Please select course!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}

    }

    @FXML
    void processNew(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseRegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/course/style/register.css").toExternalForm());
		stage.setScene(scene);
	 	stage.hide();
		stage.setTitle("Course Register Form");
		stage.show();

    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from course;");
    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
    	
//    	String searchText = tfSearch.getText().trim();
//    	ObservableList<Course> coursetList = this.courseDAO.getCourseList(searchText);
//    	ObservableList<Course> searchCourses = FXCollections.observableArrayList();
//    	
//    	for(Course course : coursetList) {
//    		if(searchText.equalsIgnoreCase(String.valueOf(course.getId()))
//    			|| searchText.equalsIgnoreCase(course.getName())
//    			|| searchText.equalsIgnoreCase(course.getCode())
//    			|| searchText.equalsIgnoreCase(String.valueOf(course.getCredit()))) {
//    			searchCourses.add(course);
//    			
//    		}
//    	}
//    	courseTable.setItems(searchCourses);

    }

    @FXML
    void processView(ActionEvent event) throws IOException {
    	
    	 Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
    	 
    	 if(selectedCourse != null) {
    		 Course storedCourse = this.courseDAO.getCourse("code", String.valueOf(selectedCourse.getCode()));
    		 Course course = Course.getInstance();
    		 course.setId(storedCourse.getId());
    		 course.setName(storedCourse.getName());
    		 course.setCode(storedCourse.getCode());
    		 course.setCredit(storedCourse.getCredit());
    		 course.setSemester(storedCourse.getSemester());
    		 
    		Stage stage = new Stage();
 			stage.hide();
 			AnchorPane root = (AnchorPane) FXMLLoader
 					.load(getClass().getResource("/com/hostmdy/attendance/course/view/CourseViewUI.fxml"));
 			Scene scene = new Scene(root);
 			scene.getStylesheets()
 					.add(getClass().getResource("/com/hostmdy/attendance/course/style/register.css").toExternalForm());
 			stage.setScene(scene);
 			stage.setTitle("Course View UI");
 			stage.show();
    		 
    	 }else {
    		String title = "Not selected course";
 			String message = "Please select course!";
 			MyNotificationType notitype = MyNotificationType.WARNING;
 			Duration dismissTime = Duration.seconds(3);
 			myNotification.getNotification(title, message, notitype, dismissTime);
		}

    }
    
    private void showTable(String sql) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	code.setCellValueFactory(new PropertyValueFactory<>("code"));
    	credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    	semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
    
    	courseTable.setItems(this.courseDAO.getCourseList(sql));
    	

		ObservableList<Course> courses = FXCollections
				.observableArrayList(courseDAO.getCourseList(sql));
		FilteredList<Course> filterCourses = new FilteredList<>(
				FXCollections.observableArrayList(courses));

		courseTable.setItems(filterCourses);
		tfSearch.textProperty().addListener((observable, oldValue, newValue) -> courseTable
				.setItems(filterList(courses, newValue.toLowerCase())));
    }
    
    private ObservableList<Course>filterList(List<Course>list,String searchText){
    	List<Course> searchedList = new ArrayList<>();
    	for(Course course : list) {
    		if(searchResult(course,searchText)) {
    			searchedList.add(course);
    		}
    	}
    	return FXCollections.observableArrayList(searchedList);
    }
    
    private boolean searchResult(Course course,String searchText) {
    	return (course.getName().toLowerCase().contains(searchText) ||
    			course.getCode().toLowerCase().contains(searchText)||
    			String.valueOf(course.getCredit()).equals(searchText)||
    			String.valueOf(course.getId()).equals(searchText)||
    			course.getSemester().toLowerCase().contains(searchText));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showTable("select * from course;");
		
	}

}
