package com.hostmdy.attendance.report.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.hostmdy.attendance.model.AttendanceDAO;
import com.hostmdy.attendance.report.model.ReportDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ReportController implements Initializable {

    @FXML
    private ComboBox<String> cbDailyYear;
    
    @FXML
    private ComboBox<String> cbMonthly;

    @FXML
    private BarChart<String, Integer> dailyBarChart;
    
    @FXML
    private BarChart<String, Double> monthlyBarChart;

    @FXML
    private DatePicker dailyDatePicker;
    
    @FXML
    private DatePicker monthlyDatePicker;

    @FXML
    private Label lblBack;
    
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private CategoryAxis xAxisMonthly;

    @FXML
    private NumberAxis yAxisMonthly;
    
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    
    private final ReportDAO reportDAO = new ReportDAO();
    
    @FXML
    void processBack(MouseEvent event) {

    }

    @FXML
    void processDailyShow(ActionEvent event) {
    	
    	Series<String,Integer> series1 = new Series<>(); 
    	Series<String,Integer> series2 = new Series<>();
    	series1.setName("Number of Total Students");
    	series2.setName("Number of Present Students");
	    dailyBarChart.getData().clear();
	    	
    	if(dailyDatePicker.getValue() != null && cbDailyYear.getValue() != null) {
    		System.out.println("inside 2nd if block");
	    	String dayofWeek = dailyDatePicker.getValue().getDayOfWeek().toString();
	    	ObservableList<String> courseCodes = attendanceDAO.getCoursecodeList(dayofWeek);
	    	String year = cbDailyYear.getValue();
	    	ObservableList<String> filterCodes = getFilterCodes(year, courseCodes);
	    	ObservableList<Integer> scheduleIds = FXCollections.observableArrayList();
	    	for(String code : filterCodes) {
	    		int scheduleId = attendanceDAO.getScheduleId(dayofWeek, code);
	    		scheduleIds.add(scheduleId);
	    		System.out.println("Schedule id is "+scheduleId);
	    	}
	    	
	    	for(int i = 0; i<= scheduleIds.size()-1; i++) {
	    		
	    		int noOfStudents = reportDAO.getNoOfStudents
	    				(dailyDatePicker.getValue().toString(), scheduleIds.get(i));
	    		int noOfPresentStudents = reportDAO.getNoOfPresentStudents
	    				(dailyDatePicker.getValue().toString(), scheduleIds.get(i));
	    		series1.getData().add(new Data<String, Integer>(filterCodes.get(i), noOfStudents));
	    		series2.getData().add(new Data<String, Integer>(filterCodes.get(i), noOfPresentStudents));	
	    	}
	    	dailyBarChart.getData().addAll(series1,series2);
    	}
    }
    
    @FXML
    void processMonthlyShow(ActionEvent event) {
    	Series<String,Double> series1 = new Series<>();
    	series1.setName("Present Attendance Percent");
    	monthlyBarChart.getData().clear();
    	if(monthlyDatePicker.getValue() != null && cbMonthly.getValue() != null) {
    		
    		LocalDate selectedDate = monthlyDatePicker.getValue();
    		LocalDate endDate = selectedDate.with(TemporalAdjusters.lastDayOfMonth());
    		LocalDate startDate = selectedDate.with(TemporalAdjusters.firstDayOfMonth());
    		
    		ObservableList<String> courseCodeList = attendanceDAO.getCoursecodeList();
    		String year = cbMonthly.getValue();
    		
    		ObservableList<String> filterCodes = getFilterCodes(year, courseCodeList);
        	ObservableList<Double> attendancePerCourses = FXCollections.observableArrayList();
    		for(String code : filterCodes) {
    			double courseAttendancePercent = getcourseAttendancePercentage(code, 
    					startDate, endDate);
    			System.out.println(code + "attendance percent is "+courseAttendancePercent);
    			attendancePerCourses.add(courseAttendancePercent);
    			series1.getData().add(new Data<String, Double>(code, courseAttendancePercent));
        	}
    		monthlyBarChart.getData().add(series1);
    		System.out.println(filterCodes.size()+","+attendancePerCourses.size());
    		
    	}
    }
    
    private ObservableList<String> getFilterCodes(String year,ObservableList<String> codes){
    	ObservableList<String> filterCodes = FXCollections.observableArrayList();
    	char filterYear = year.charAt(0);
    	System.out.println(filterYear);
    	for(String code : codes) {
    		char lastIndex = code.charAt(code.length()-1);
    		if(lastIndex == filterYear) 
    			filterCodes.add(code);
    	}
    	return filterCodes;
    }
    
    private double getcourseAttendancePercentage(String code,LocalDate startDate, LocalDate endDate) {
    	double courseAttendancePercentage = 0;
    	ObservableList<String> daysOfWeek = FXCollections.observableArrayList(
    			"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
    	double presentAttendancePerMonth = 0;
    	double attendancePerMonth = 0;
    	for(String day : daysOfWeek) {
    		int scheduleId = attendanceDAO.getScheduleId(day, code);
    		if(scheduleId >0) {
	    		attendancePerMonth = attendancePerMonth + 
	    				reportDAO.getNoOfStudentsPerMonth(startDate, endDate, scheduleId);
	    		presentAttendancePerMonth = presentAttendancePerMonth +
	    				reportDAO.getNoOfPresentStudentsPerMonth(startDate, endDate, scheduleId);
    		}
    	}
    	if(attendancePerMonth>0)
    		courseAttendancePercentage = (presentAttendancePerMonth/attendancePerMonth)*100;
    	else
    		courseAttendancePercentage=0;
    	return courseAttendancePercentage;
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> years = FXCollections.observableArrayList(
				"1st Year","2nd Year","3rd Year","4th Year","5th Year");
		cbDailyYear.setItems(years);
		cbMonthly.setItems(years);
	}

}
