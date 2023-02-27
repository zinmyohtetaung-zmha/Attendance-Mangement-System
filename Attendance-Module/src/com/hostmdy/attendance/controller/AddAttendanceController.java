package com.hostmdy.attendance.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddAttendanceController implements Initializable{

    @FXML
    private TableColumn<Attendance, RadioButton> absentCol;

    @FXML
    private ComboBox<String> cbCourse;

    @FXML
    private TableView<Attendance> enrollmentTableView;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblSemester;

    @FXML
    private Label lblTimeSlot;
    
    @FXML
    private Label lblWarning;
    
    @FXML
    private Label lblShowTime;
    
    @FXML
    private Label lblCourseName;

    @FXML
    private TableColumn<Attendance, Integer> number;

    @FXML
    private TableColumn<Attendance, RadioButton> presentCol;

    @FXML
    private TableColumn<Attendance, String> studentId;

    @FXML
    private TableColumn<Attendance, String> studentName;
    
    @FXML
    private TableColumn<Attendance, String> year;
    
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    
    private final MyNotification myNotification = new MyNotification();

    @FXML
    void processAdd(ActionEvent event) {
    	
		if (cbCourse.getValue() != null && enrollmentTableView.getItems() != null) {
			LocalDate todayDate = LocalDate.now();
			String day = todayDate.getDayOfWeek().toString();
			int scheduleId = attendanceDAO.getScheduleId(day, cbCourse.getValue());
			int rowEffected = 0;
			System.out.println("Schedule id is " + scheduleId);
			ObservableList<Attendance> list = enrollmentTableView.getItems();
			if (list != null) {
				for (Attendance attendanceObj : list) {
					RadioButton present = attendanceObj.getPresent();
					int enrollmentId = attendanceDAO.getEnrollmentId(attendanceObj.getStudentId());
					if (present.isSelected()) {
						attendanceObj.setPresent(true);
					} else {
						attendanceObj.setPresent(false);
					}
					Attendance createAttendance = new Attendance(enrollmentId, scheduleId, todayDate,
							attendanceObj.isPresent());
					rowEffected = attendanceDAO.createAttendance(createAttendance);
					if (rowEffected > 0) {
						enrollmentTableView.setItems(null);
						cbCourse.setPromptText("Choose Course");
					}
				}
			}
			
			String title = "Add Attendance";
			String message = "Successfully add attendance!";
			MyNotificationType notitype = MyNotificationType.SUCCESS;
			Duration dismissTime = Duration.seconds(3);
			myNotification.getNotification(title, message, notitype, dismissTime);
		}
    	
    }

    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage updateStage = (Stage) ((Label)event.getSource()).getScene().getWindow();   	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/view/AttendanceUI.fxml"));
		Scene scene = new Scene(root);
		updateStage.hide();
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/style/style.css").toExternalForm());
		updateStage.setScene(scene);
		updateStage.show();

    }
    
    private void showTable(String sql) {
    	number.setCellValueFactory(new PropertyValueFactory<>("id"));
    	studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    	presentCol.setCellValueFactory(new PropertyValueFactory<>("present"));
    	absentCol.setCellValueFactory(new PropertyValueFactory<>("absent"));

    	studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	
    	enrollmentTableView.setItems(attendanceDAO.getaddTableView(sql));
    	System.out.println(enrollmentTableView.getItems().size());
    }
    
    private String getSql(String input) {
    	String lastWord = input.substring(input.length()-1);
		String sql="";
		switch (lastWord) {
		case "1":
			sql="Select * from enrollment where enrollment.year='1st Year'";
			break;
		case "2":
			sql="Select * from enrollment where enrollment.year='2nd Year'";
			break;
		case "3":
			sql="Select * from enrollment where enrollment.year='3th Year'";
			break;
		case "4":
			sql="Select * from enrollment where enrollment.year='4th Year'";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + lastWord);
		}
		return sql;
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
		
		
		LocalDate today = LocalDate.now();
		String dayOftheWeek = today.getDayOfWeek().toString();
		lblDate.setText(today.toString());
		
		ObservableList<String> courseCodes = attendanceDAO.getCoursecodeList(dayOftheWeek);
		cbCourse.setItems(courseCodes);
		
		cbCourse.valueProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//TODO Auto-generated method stub
				int dataExistChecker = 0;
				String response = attendanceDAO.getComboChangeValue(newValue);
				String[]split = response.split("_");
				lblSemester.setText(split[0]);
				lblTimeSlot.setText(split[1]);
				lblCourseName.setText(split[2]);
				int scheduleId = attendanceDAO.getScheduleId(dayOftheWeek, newValue);
				dataExistChecker = attendanceDAO.idDoesExist(today.toString(), scheduleId);
				System.out.println(dataExistChecker);
				if(dataExistChecker >0) {
					lblWarning.setVisible(true);
					lblWarning.setText("Attendance had been recorded for "+today+" "+newValue);
					enrollmentTableView.getItems().clear();
				}
				else {
					lblWarning.setText("");
					lblWarning.setVisible(false);
					String sql = getSql(cbCourse.getValue());
					showTable(sql);
				}
				
			}
		});
		
	}

}
