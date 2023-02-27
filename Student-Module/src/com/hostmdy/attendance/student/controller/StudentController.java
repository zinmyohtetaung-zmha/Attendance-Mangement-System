package com.hostmdy.attendance.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hostmdy.attendance.student.model.Student;
import com.hostmdy.attendance.student.model.StudentDAO;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentController implements Initializable{

    @FXML
    private TableColumn<Student, String> address;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> email;

    @FXML
    private TableColumn<Student, String> firstname;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> lastname;

    @FXML
    private TableColumn<Student, String> nrc;

    @FXML
    private TableColumn<Student, String> phone;

    @FXML
    private TableColumn<Student, String> studentid;
    
    @FXML
    private Label lblBack;


    @FXML
    private TextField tfSearch;
    
    private final MyNotification mynoti = new MyNotification();
    
    @FXML
    void processBack(MouseEvent event) {
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();

    }

    
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    void processDelete(ActionEvent event) {
    	
    	
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

		if (selectedStudent != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure to delete!");
			Optional<ButtonType> action = alert.showAndWait();

			if (action.get() == ButtonType.OK) {

				int rowEffected = this.studentDAO.deleteByStdID(selectedStudent.getStudentid());
				Student storedStudent = this.studentDAO.getStudent("studentid",
						String.valueOf(selectedStudent.getStudentid()));

				if (rowEffected > 0) {
					showTable(" select * from student;");

					String title = "Delete Student";
					String message = "Successfully delete!";
					MyNotificationType notitype = MyNotificationType.SUCCESS;
					Duration dismissTime = Duration.seconds(3);
					mynoti.getNotification(title, message, notitype, dismissTime);
				} else {

				}
			}

		} else {
			String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			mynoti.getNotification(title, message, notitype, dismissTime);
		}
    	
    	
    }
    

    @FXML
    void processEdit(ActionEvent event) throws IOException { 
    	
    	Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedStudent != null) {
    		Student storedStudent = this.studentDAO.getStudent("studentid", String.valueOf(selectedStudent.getStudentid()));
    		Student student = Student.getInstance();
    		student.setId(storedStudent.getId());
    		student.setStudentid(storedStudent.getStudentid());
    		student.setFristname(storedStudent.getFirstname());
    		student.setLastname(storedStudent.getLastname());
    		student.setEmail(storedStudent.getEmail());
    		student.setPhone(storedStudent.getPhone());
    		student.setNrc(storedStudent.getNrc());
    		student.setAddress(storedStudent.getAddress());
    		
    		Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/student/view/StudentUpdateUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/student/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Student Update  UI");
			updateStage.show();
    		}else {
    			String title = "Not selected student";
    			String message = "Please select student!";
    			MyNotificationType notitype = MyNotificationType.WARNING;
    			Duration dismissTime = Duration.seconds(3);
    			mynoti.getNotification(title, message, notitype, dismissTime);
			}

    }
    
	@FXML
	void processView(ActionEvent event) throws IOException {

		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

		if (selectedStudent != null) {
			Student storedStudent = this.studentDAO.getStudent("studentid", String.valueOf(selectedStudent.getStudentid()));
			Student student = Student.getInstance();
			student.setId(storedStudent.getId());
			student.setStudentid(storedStudent.getStudentid());
			student.setFristname(storedStudent.getFirstname());
			student.setLastname(storedStudent.getLastname());
			student.setEmail(storedStudent.getEmail());
			student.setPhone(storedStudent.getPhone());
			student.setNrc(storedStudent.getNrc());
			student.setAddress(storedStudent.getAddress());

			Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
			stage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/student/view/StudentViewUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/student/style/register.css").toExternalForm());
			stage.setScene(scene);
			stage.hide();
			stage.setTitle("Student View UI");
			stage.show();
		}else {
			String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			mynoti.getNotification(title, message, notitype, dismissTime);
		}

	}

    @FXML
    void processNew(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/student/view/StudentRegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/student/style/register.css").toExternalForm());
		stage.setScene(scene);
	 	stage.hide();
		stage.setTitle("Student Register Form");
		stage.show();

    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from student where active=true;");

    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
    	
//		String searchText = tfSearch.getText().trim();
//		ObservableList<Student> studentList = this.studentDAO.getStudentList(searchText);
//		ObservableList<Student> searchStudent = FXCollections.observableArrayList();
//		for (Student student : studentList) {
//			if (searchText.equalsIgnoreCase(String.valueOf(student.getId()))
//					|| searchText.equalsIgnoreCase(String.valueOf(student.getStudentid()))
//					|| searchText.equalsIgnoreCase(student.getFirstname())
//					|| searchText.equalsIgnoreCase(student.getLastname())
//					|| searchText.equalsIgnoreCase(student.getEmail())
//					|| searchText.equalsIgnoreCase(student.getPhone()) 
//					|| searchText.equalsIgnoreCase(student.getNrc())
//					|| searchText.equalsIgnoreCase(student.getAddress())) {
//				searchStudent.add(student);
//			}
//		}
//		studentTable.setItems(searchStudent);

    }
    
    private void showTable(String sql) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	studentid.setCellValueFactory(new PropertyValueFactory<>("studentid"));
		firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		nrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		studentTable.setItems(this.studentDAO.getStudentList(sql));
		

		ObservableList<Student> courses = FXCollections
				.observableArrayList(studentDAO.getStudentList(sql));
		FilteredList<Student> filterStudents = new FilteredList<>(
				FXCollections.observableArrayList(courses));

		studentTable.setItems(filterStudents);
		tfSearch.textProperty().addListener((observable, oldValue, newValue) -> studentTable
				.setItems(filterList(courses, newValue.toLowerCase())));
		
    }
    
    private ObservableList<Student>filterList(List<Student>list,String searchText){
    	List<Student> searchedList = new ArrayList<>();
    	for(Student student : list) {
    		if(searchResult(student,searchText)) {
    			searchedList.add(student);
    		}
    	}
    	return FXCollections.observableArrayList(searchedList);
    }
    
    private boolean searchResult(Student student,String searchText) {
    	return (student.getFirstname().toLowerCase().contains(searchText) ||
    			student.getLastname().toLowerCase().contains(searchText)||
    			student.getEmail().toLowerCase().contains(searchText) ||
    			student.getPhone().toLowerCase().contains(searchText)||
    			student.getNrc().toLowerCase().contains(searchText) ||
    			student.getAddress().toLowerCase().contains(searchText)||
    			String.valueOf(student.getStudentid()).equals(searchText)
    			);
    }

    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    	showTable("select * from student where active=true;");
		
	}

}
