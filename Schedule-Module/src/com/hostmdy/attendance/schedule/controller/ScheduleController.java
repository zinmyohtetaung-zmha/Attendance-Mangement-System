package com.hostmdy.attendance.schedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hostmdy.attendance.schedule.model.Schedule;
import com.hostmdy.attendance.schedule.model.ScheduleDAO;
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

public class ScheduleController implements Initializable{

    @FXML
    private Button btnView;
    
    @FXML
    private TableColumn<Schedule, String> coursecode;

    @FXML
    private TableColumn<Schedule, String> coursename;

    @FXML
    private TableColumn<Schedule, String> day;

    @FXML
    private TableColumn<Schedule, Integer> id;

    @FXML
    private Label lblBack;

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableColumn<Schedule, String> timeslot;
    
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processBack(MouseEvent event) {
    	
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();

    }

    @FXML
    void processDelete(ActionEvent event) {
    	
    	Schedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedSchedule != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure to delete!");
			Optional<ButtonType> action = alert.showAndWait();
    	
    	
    	if(action.get() == ButtonType.OK) {
    		int selectedId = scheduleDAO.getIdfromDB(String.valueOf(selectedSchedule.getCoursecode()), String.valueOf(selectedSchedule.getDay()));
    
    		Schedule storedSchedule = this.scheduleDAO.getSchedule("id",String.valueOf(selectedId));
    		
    		int rowEffected = this.scheduleDAO.deleteByID(selectedId);
    		
    		
    		if(rowEffected >0) {
    			showTable(" select * from schedule;");
    			
    			String title = "Delete Student";
				String message = "Successfully delete!";
				MyNotificationType notitype = MyNotificationType.SUCCESS;
				Duration dismissTime = Duration.seconds(3);
				myNotification.getNotification(title, message, notitype, dismissTime);
    		}else {
				
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
    	Schedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
    	if(selectedSchedule != null) {

    		int selectedId = scheduleDAO.getIdfromDB(String.valueOf(selectedSchedule.getCoursecode()), String.valueOf(selectedSchedule.getDay()));
    		System.out.println(selectedId);
    
    		Schedule storedSchedule = this.scheduleDAO.getSchedule("id",String.valueOf(selectedId));

    		Schedule schedule = Schedule.getInstance();

    		schedule.setId(storedSchedule.getId());
    		schedule.setCoursecode(storedSchedule.getCoursecode());
    		schedule.setCoursename(selectedSchedule.getCoursename());
    		schedule.setDay(storedSchedule.getDay());
    		schedule.setTimeslot(storedSchedule.getTimeslot());
    		
    		Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/schedule/view/ScheduleUpdateUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/schedule/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Schedule Update  UI");
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
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/schedule/view/ScheduleRegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/schedule/style/register.css").toExternalForm());
		stage.setScene(scene);
		stage.hide();
		stage.setTitle("Student Enrollment");
		stage.show();

    }

    @FXML
    void processRefresh(ActionEvent event) {

		showTable("select * from schedule;");
		
    	tfSearch.clear();

    }

    @FXML
    void processSearch(ActionEvent event) {
//    	String searchText = tfSearch.getText().trim();
//    	ObservableList<Schedule> scheduleList = this.scheduleDAO.getScheduleList(searchText);
//    	ObservableList<Schedule> searchSchedules = FXCollections.observableArrayList();
//    	for(Schedule schedule : scheduleList) {
//    		if(searchText.equalsIgnoreCase(String.valueOf(schedule.getId()))
//    				||searchText.equalsIgnoreCase(String.valueOf(schedule.getCoursename()))
//    				||searchText.equalsIgnoreCase(schedule.getDay())
//    				||searchText.equalsIgnoreCase(schedule.getTimeslot())
//    				
//    				){
//    			
//    			searchSchedules.add(schedule);
//    		}
//    	}
//
//    	scheduleTable.setItems(searchSchedules);
    }

    @FXML
    void processView(ActionEvent event) throws IOException {
    	
    	Schedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
    	if(selectedSchedule != null) {
    		int selectedId = scheduleDAO.getIdfromDB(String.valueOf(selectedSchedule.getCoursecode()), String.valueOf(selectedSchedule.getDay()));
    		System.out.println(selectedId);
    
    		Schedule storedSchedule = this.scheduleDAO.getSchedule("id",String.valueOf(selectedId));
    		
    		Schedule schedule = Schedule.getInstance();
    		schedule.setCoursecode(storedSchedule.getCoursecode());
    		schedule.setCoursename(selectedSchedule.getCoursename());
    		schedule.setDay(storedSchedule.getDay());
    		schedule.setTimeslot(storedSchedule.getTimeslot());
    		
    		Stage viewStage = (Stage)((Button) event.getSource()).getScene().getWindow();
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/schedule/view/ScheduleViewUI.fxml"));
    		Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/schedule/style/register.css").toExternalForm());
    		viewStage.setScene(scene);
    		viewStage.hide();
    		viewStage.setTitle("Schedule View UI");
    		viewStage.show();
    	}else {
    		String title = "Not selected student";
			String message = "Please select student!";
			MyNotificationType notitype = MyNotificationType.WARNING;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);

		}

    }
    
    private void showTable(String sql) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	coursecode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
    	coursename.setCellValueFactory(new PropertyValueFactory<>("coursename"));
    	day.setCellValueFactory(new PropertyValueFactory<>("day"));
    	timeslot.setCellValueFactory(new PropertyValueFactory<>("timeslot"));
    	
    	scheduleTable.setItems(this.scheduleDAO.getScheduleList(sql));
    	
    	ObservableList<Schedule> schedules = FXCollections
				.observableArrayList(scheduleDAO.getScheduleList(sql));
		FilteredList<Schedule> filterSchedules = new FilteredList<>(
				FXCollections.observableArrayList(schedules));

		scheduleTable.setItems(filterSchedules);
		tfSearch.textProperty().addListener((observable, oldValue, newValue) -> scheduleTable
				.setItems(filterList(schedules, newValue.toLowerCase())));
    }
    
    private ObservableList<Schedule>filterList(List<Schedule>list,String searchText){
    	List<Schedule> searchedList = new ArrayList<>();
    	for(Schedule student : list) {
    		if(searchResult(student,searchText)) {
    			searchedList.add(student);
    		}
    	}
    	return FXCollections.observableArrayList(searchedList);
    }
    
    private boolean searchResult(Schedule schedule,String searchText) {
    	return (schedule.getCoursecode().toLowerCase().contains(searchText) ||
    			schedule.getCoursename().toLowerCase().contains(searchText)||
    			schedule.getTimeslot().toLowerCase().contains(searchText) 
    			
    			);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showTable("select * from schedule;");
		
	}

}
