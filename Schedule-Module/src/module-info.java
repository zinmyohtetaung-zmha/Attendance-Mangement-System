module com.hostmdy.attendance.schedule {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires com.hostmdy.attendance.database;
	requires com.hostmdy.attendance.course;
	requires javafx.fxml;
	requires com.hostmdy.attendance.utility;
	
	exports com.hostmdy.attendance.schedule.model;
	exports com.hostmdy.attendance.schedule.controller;
	exports com.hostmdy.attendance.schedule.view;
	exports com.hostmdy.attendance.schedule.style;
	
	opens com.hostmdy.attendance.schedule.model to javafx.base;
	opens com.hostmdy.attendance.schedule.controller to javafx.graphics, javafx.controls, javafx.fxml;
	opens com.hostmdy.attendance.schedule.main to javafx.graphics, javafx.controls, javafx.fxml;

	
}