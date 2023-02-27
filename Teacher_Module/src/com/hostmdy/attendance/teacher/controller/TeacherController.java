package com.hostmdy.attendance.teacher.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hostmdy.attendance.teacher.model.Teacher;
import com.hostmdy.attendance.teacher.model.TeacherDAO;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class TeacherController implements Initializable {

    @FXML
    private TableColumn<Teacher, String> address;

    @FXML
    private Button btnView;

    @FXML
    private TableColumn<Teacher, String> email;
    
    @FXML
    private TableColumn<Teacher, String> faculty;

    @FXML
    private TableColumn<Teacher, String> firstname;

    @FXML
    private TableColumn<Teacher, Integer> id;

    @FXML
    private TableColumn<Teacher, String> lastname;

    @FXML
    private Label lblBack;

    @FXML
    private TableColumn<Teacher, String> nrc;

    @FXML
    private TableColumn<Teacher, String> phone;

    @FXML
    private TableColumn<Teacher, String> teacherid;

    @FXML
    private TableView<Teacher> teacherTable;
    
    private final TeacherDAO teacherDAO = new TeacherDAO();

    @FXML
    private TextField tfSearch;
    
    private final MyNotification myNotification = new MyNotification();
    

    @FXML
    void processBack(MouseEvent event) {
    		Stage stage = (Stage) lblBack.getScene().getWindow();
    	    stage.close();
    }

    @FXML
    void processDelete(ActionEvent event) {
    	
    	Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTeacher != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure to delete!");
			Optional<ButtonType> action = alert.showAndWait();
    	
    	if(action.get() == ButtonType.OK) {
    		int rowEffected = teacherDAO.deleteById(selectedTeacher.getStaffId());
    		if(rowEffected >0) {
    			showTable("Select * from teacher where teacher.status!='deleted';");
    			
    			String title = "Delete Teacher";
				String message = "Successfully deleted!";
				MyNotificationType notitype = MyNotificationType.SUCCESS;
				Duration dismissTime = Duration.seconds(3);
				myNotification.getNotification(title, message, notitype, dismissTime);
    		}
    	}
    	}else {
			String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	if(selectedTeacher != null) {
    		Teacher teacher = Teacher.getInstance();
    		//teacher.setId(selectedTeacher.getId());
      		teacher.setStaffId(selectedTeacher.getStaffId());
    		
    		teacher.setAddress(selectedTeacher.getAddress());
    		teacher.setEmail(selectedTeacher.getEmail());
    		teacher.setFaculty(selectedTeacher.getFaculty());
    		teacher.setFirstName(selectedTeacher.getFirstName());
    		teacher.setLastName(selectedTeacher.getLastName());
    		teacher.setNrc(selectedTeacher.getNrc());
    		teacher.setPhone(selectedTeacher.getPhone());
  
    		teacher.setStatus("Updated");
    		
    		Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherUpdateUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/teacher/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Teacher Update  UI");
			updateStage.show();
    		
    	}else {
			String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}
    	
    }

    @FXML
    void processNew(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherRegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/teacher/style/register.css").toExternalForm());
		stage.setScene(scene);
	 	stage.hide();
		stage.setTitle("Teacher Register Form");
		stage.show();
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("Select * from teacher where teacher.status!='deleted'");
    	
    	tfSearch.clear();
    }

    @FXML
    void processSearch(ActionEvent event) {

    }

    @FXML
    void processView(ActionEvent event) throws IOException {
    	
    	Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTeacher != null) {
    		Teacher storedTeacher = teacherDAO.getTeacherID("staff_id", selectedTeacher.getStaffId());
    		Teacher teacher = Teacher.getInstance();
//    		teacher.setId(storedTeacher.getId());
    		teacher.setFirstName(storedTeacher.getFirstName());
    		teacher.setLastName(storedTeacher.getLastName());
    		teacher.setEmail(storedTeacher.getEmail());
    		teacher.setPhone(storedTeacher.getPhone());
    		teacher.setNrc(storedTeacher.getNrc());
    		teacher.setAddress(storedTeacher.getAddress());
    		teacher.setFaculty(storedTeacher.getFaculty());
    		teacher.setStaffId(storedTeacher.getStaffId());
    		
    		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
			stage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/teacher/view/TeacherViewUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/teacher/style/register.css").toExternalForm());
			stage.setScene(scene);
			stage.hide();
			stage.setTitle("Student View UI");
			stage.show();

    		
    	}else {
    		String title = "Not selected teacher";
			String message = "Please select teacher!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}

    }
    
    private void showTable(String sql) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	teacherid.setCellValueFactory(new PropertyValueFactory<>("staffId"));
    	firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    	nrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
    	
    	teacherTable.setItems(teacherDAO.getTeacherList(sql));
    	
    	ObservableList<Teacher> teacherList = FXCollections.observableArrayList(teacherDAO.getTeacherList(sql));
    	FilteredList<Teacher> filteredTeacher = new FilteredList<>(FXCollections.observableList(teacherList));
    	teacherTable.setItems(filteredTeacher);
    	
    	tfSearch.textProperty().addListener(
				(observable, oldValue, newValue) -> teacherTable.setItems(filterList(teacherList, newValue.toLowerCase())));
    	
    }
    
    private ObservableList<Teacher>filterList(List<Teacher>list,String searchText){
    	List<Teacher> searchedList = new ArrayList<>();
    	for (Teacher teacher : list) {
			if(searchTeacher(teacher,searchText)) {
				searchedList.add(teacher);
			}
		}
		return FXCollections.observableList(searchedList);
    }
    
    private boolean searchTeacher(Teacher teacher,String searchText) {
    	return(Integer.valueOf(teacher.getId()).equals(searchText)||
    		teacher.getStaffId().toLowerCase().contains(searchText)||
    		teacher.getFirstName().toLowerCase().contains(searchText)||
    		teacher.getLastName().toLowerCase().contains(searchText)||
    		teacher.getNrc().toLowerCase().contains(searchText)||
    		teacher.getPhone().contains(searchText)||
    		teacher.getFaculty().toLowerCase().contains(searchText)
    			);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showTable("Select * from teacher where teacher.status!='deleted'");
	}

}
