package com.hostmdy.attendance.enrollment.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hostmdy.attendance.enrollment.model.Enrollment;
import com.hostmdy.attendance.enrollment.model.EnrollmentDAO;
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

public class EnrollmentController implements Initializable{

    @FXML
    private TableColumn<Enrollment, String> academicyear;

    @FXML
    private TableView<Enrollment> enrollmentTable;

    @FXML
    private TableColumn<Enrollment, Integer> id;

    @FXML
    private TableColumn<Enrollment, String> major;

    @FXML
    private TableColumn<Enrollment, Integer> studentid;
    
    @FXML
    private TableColumn<Enrollment, String> studentname;
    
    @FXML
    private Label lblBack;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableColumn<Enrollment, String> year;
    
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    private final MyNotification myNotification = new MyNotification();
    
    @FXML
    void processBack(MouseEvent event) {
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();
    }

    
    @FXML
    void processDelete(ActionEvent event) {
    	
		Enrollment selectedEnrollment = enrollmentTable.getSelectionModel().getSelectedItem();

		if (selectedEnrollment != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure to delete!");
			Optional<ButtonType> action = alert.showAndWait();

			if (action.get() == ButtonType.OK) {
				int rowEffected = this.enrollmentDAO.deleteByStdID(selectedEnrollment.getStudentid());
				Enrollment storedEnrollment = this.enrollmentDAO.getEnrollment("studentid",
						String.valueOf(selectedEnrollment.getStudentid()));

				if (rowEffected > 0) {
					showTable(" select * from enrollment;");

					String title = "Delete Student";
					String message = "Successfully delete!";
					MyNotificationType notitype = MyNotificationType.SUCCESS;
					Duration dismissTime = Duration.seconds(3);
					myNotification.getNotification(title, message, notitype, dismissTime);
				} else {

				}
			}
		} else {
			String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}

    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	Enrollment selectedEnrollment = enrollmentTable.getSelectionModel().getSelectedItem();
    	if(selectedEnrollment != null) {
    		Enrollment storedEnrollment = this.enrollmentDAO.getEnrollment("studentid", String.valueOf(selectedEnrollment.getStudentid()));
    		Enrollment enrollment = Enrollment.getInstance();
    		enrollment.setId(storedEnrollment.getId());
    		enrollment.setStudentid(storedEnrollment.getStudentid());
    		enrollment.setStudentname(storedEnrollment.getStudentname());
    		enrollment.setYear(storedEnrollment.getYear());
    		enrollment.setMajor(storedEnrollment.getMajor());
    		enrollment.setAcademicyear(storedEnrollment.getAcademicyear());

    		Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentUpdateUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.hide();
			updateStage.setTitle("Enrollment Update  UI");
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
    void processView(ActionEvent event) throws IOException {
    	
    	Enrollment selectedEnrollment = enrollmentTable.getSelectionModel().getSelectedItem();
    	if(selectedEnrollment != null) {
    		Enrollment storedEnrollment = this.enrollmentDAO.getEnrollment("studentid", String.valueOf(selectedEnrollment.getStudentid()));
    		Enrollment enrollment = Enrollment.getInstance();
    		enrollment.setId(storedEnrollment.getId());
    		enrollment.setStudentid(storedEnrollment.getStudentid());
    		enrollment.setStudentname(storedEnrollment.getStudentname());
    		enrollment.setYear(storedEnrollment.getYear());
    		enrollment.setMajor(storedEnrollment.getMajor());
    		enrollment.setAcademicyear(storedEnrollment.getAcademicyear());

    		Stage viewStage = (Stage)((Button) event.getSource()).getScene().getWindow();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentViewUI.fxml"));
    		Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/register.css").toExternalForm());
    		viewStage.setScene(scene);
    		viewStage.hide();
    		viewStage.setTitle("Enrollment View UI");
    		viewStage.show();
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
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/enrollment/view/EnrollmentRegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/enrollment/style/register.css").toExternalForm());
		stage.setScene(scene);
		stage.hide();
		stage.setTitle("Student Enrollment");
		stage.show();

    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from enrollment");

    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
    	
//		String searchText = tfSearch.getText().trim();
//		ObservableList<Enrollment> enrollmentList = this.enrollmentDAO.getEnrollmentList(searchText);
//		ObservableList<Enrollment> searchEnrollments = FXCollections.observableArrayList();
//		for (Enrollment enrollment : enrollmentList) {
//			if (searchText.equalsIgnoreCase(String.valueOf(enrollment.getId()))
//					||searchText.equalsIgnoreCase(enrollment.getStudentid())
//					||searchText.equalsIgnoreCase(enrollment.getStudentname())
//					||searchText.equalsIgnoreCase(enrollment.getYear())
//					||searchText.equalsIgnoreCase(enrollment.getMajor())
//					||searchText.equalsIgnoreCase(enrollment.getAcademicyear())
//
//					){
//				searchEnrollments.add(enrollment);
//			}
//		}
//		enrollmentTable.setItems(searchEnrollments);

    }
    
    private void showTable(String sql) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
		studentid.setCellValueFactory(new PropertyValueFactory<>("studentid"));
		studentname.setCellValueFactory(new PropertyValueFactory<>("studentname"));
		year.setCellValueFactory(new PropertyValueFactory<>("year"));
		major.setCellValueFactory(new PropertyValueFactory<>("major"));
		academicyear.setCellValueFactory(new PropertyValueFactory<>("academicyear"));

		
		enrollmentTable.setItems(this.enrollmentDAO.getEnrollmentList(sql));
	

		ObservableList<Enrollment> enrollments = FXCollections
				.observableArrayList(enrollmentDAO.getEnrollmentList(sql));
		FilteredList<Enrollment> filterEnrollments = new FilteredList<>(
				FXCollections.observableArrayList(enrollments));

		enrollmentTable.setItems(filterEnrollments);
		tfSearch.textProperty().addListener((observable, oldValue, newValue) -> enrollmentTable
				.setItems(filterList(enrollments, newValue.toLowerCase())));
    	
    }
    
    private ObservableList<Enrollment>filterList(List<Enrollment>list,String searchText){
    	List<Enrollment> searchedList = new ArrayList<>();
    	for(Enrollment enrollment : list) {
    		if(searchResult(enrollment,searchText)) {
    			searchedList.add(enrollment);
    		}
    	}
    	return FXCollections.observableArrayList(searchedList);
    }
    
    private boolean searchResult(Enrollment enrollment,String searchText) {
    	return (enrollment.getStudentname().toLowerCase().contains(searchText) ||
    			enrollment.getAcademicyear().toLowerCase().contains(searchText)||
    			enrollment.getYear().toLowerCase().contains(searchText)||
    			String.valueOf(enrollment.getId()).equals(searchText)||
    			enrollment.getMajor().toLowerCase().contains(searchText));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	showTable("select * from enrollment");
	}

}
