package com.hostmdy.attendance.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.hostmdy.attendance.model.Attendance;
import com.hostmdy.attendance.model.AttendanceDAO;
import com.hostmdy.attendance.utility.notification.MyNotification;
import com.hostmdy.attendance.utility.notification.MyNotificationType;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AttendanceController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFilter;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<String> cbCourse;

    @FXML
    private TableColumn<Attendance, String> course;

    @FXML
    private TableView<Attendance> attendanceTable;

    @FXML
    private DatePicker filterDate;

    @FXML
    private TableColumn<Attendance, Integer> id;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblNowDate;
    
    @FXML
    private Label lblShowTime;

    @FXML
    private TableColumn<Attendance, String> studentId;

    @FXML
    private TableColumn<Attendance, String> studentName;

    @FXML
    private TableColumn<Attendance, String> year;
    
    @FXML
    private TableColumn<Attendance, String> date;

    @FXML
    private TableColumn<Attendance, String> status;
    
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    
    private final Attendance attendanceInstance = Attendance.getInstance();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processBack(MouseEvent event){
    	Stage stage = (Stage) lblBack.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void processEdit(ActionEvent event) throws IOException {
    	Attendance selectedAttendance = attendanceTable.getSelectionModel().getSelectedItem();
    	if(selectedAttendance != null) {
    		int enrollmentId = attendanceDAO.getEnrollmentId(selectedAttendance.getStudentId());
    		attendanceInstance.setEnrollmentId(enrollmentId);
    		attendanceInstance.setStudentName(selectedAttendance.getStudentName());
    		attendanceInstance.setYear(selectedAttendance.getYear());
    		attendanceInstance.setDate(selectedAttendance.getDate());
    		attendanceInstance.setCourse(selectedAttendance.getCourse());
    		attendanceInstance.setStudentId(selectedAttendance.getStudentId());
    		attendanceInstance.setCourseCode(cbCourse.getValue());
    		System.out.println(selectedAttendance.getStatus());
    		if(selectedAttendance.getStatus().equalsIgnoreCase("Present"))
    			attendanceInstance.setPresent(true);
    		else
    			attendanceInstance.setPresent(false);
    		
    		AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/com/hostmdy/attendance/view/AttendanceEditUI.fxml"));
			Scene scene = new Scene(root);
			Stage updateStage= (Stage)((Button) event.getSource()).getScene().getWindow();
			updateStage.hide();
			scene.getStylesheets()
					.add(getClass().getResource("/com/hostmdy/attendance/style/register.css").toExternalForm());
			updateStage.setScene(scene);
			updateStage.setTitle("Attendance Update  UI");
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
    void processFilter(ActionEvent event) {
    	if(filterDate.getValue() != null && cbCourse.getValue() != null) {
    		int scheduleId = attendanceDAO.getScheduleId(filterDate.getValue().getDayOfWeek().toString(), cbCourse.getValue());
    		showTable(filterDate.getValue().toString(), scheduleId);
    		System.out.println("inside both are not null and schedule id is "+scheduleId);
    	}else if(filterDate.getValue() != null ) {
    		showTable(filterDate.getValue().toString());
    		System.out.println("inside filterDate is not null");
    	}
    }

    @FXML
    void processNew(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/view/AddAttendanceUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/style/register.css").toExternalForm());
		stage.setScene(scene);
	 	stage.hide();
		stage.setTitle("Add Attendance Form");
		stage.show();
    }

    @FXML
    void processRefresh(ActionEvent event) {
    	String latestdate = attendanceDAO.getLatestDate();
    	int scheduleId = attendanceDAO.getScheduleIdFromLatestAttendanceId(latestdate);
    	filterDate.setValue(null);
		cbCourse.getItems().clear();
		cbCourse.setPromptText("Choose Course");
		
		showTable(latestdate, scheduleId);
    }
    
    private void showTable(String input) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
    	course.setCellValueFactory(new PropertyValueFactory<>("course"));
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	attendanceTable.setItems(attendanceDAO.getAttendanceList(input));
    	
    }
    
    private void showTable(String input,int scheduleId) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
    	course.setCellValueFactory(new PropertyValueFactory<>("course"));
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));

    	attendanceTable.setItems(attendanceDAO.getAttendanceList(input,scheduleId));
    	
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		

		Thread clock = new Thread() {
		       public void run() {
		           while(true) {
		               DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		               
		               String now = dateFormat.format(new Date());
		               Platform.runLater(()->{
		                lblShowTime.setText(now);
		               });
		               try {
		                   sleep(1000);
		               } catch (InterruptedException e) {
		                    e.printStackTrace();
		               }
		           }
		       }
		   };
		   clock.start();
		
		
		String latestdate = attendanceDAO.getLatestDate();
		int scheduleId = attendanceDAO.getScheduleIdFromLatestAttendanceId(latestdate);
		showTable(latestdate, scheduleId);
		
		String today = LocalDate.now().toString();
		lblNowDate.setText(today);
		
		filterDate.setValue(LocalDate.now());
		cbCourse.setValue(attendanceDAO.getCourseCodeFromDB(scheduleId));
		
		filterDate.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				// TODO Auto-generated method stub
				if(filterDate.getValue() != null) {
					String day = filterDate.getValue().getDayOfWeek().toString();
					ObservableList<String> courseCodes = attendanceDAO.getCoursecodeList(day);
					cbCourse.setItems(courseCodes);
				}
			}

		
		});
		
		
	}

}
