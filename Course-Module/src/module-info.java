module com.hostmdy.attendance.course {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires com.hostmdy.attendance.database;
	requires javafx.graphics;
	requires com.hostmdy.attendance.utility;
	
	exports com.hostmdy.attendance.course.model;
	exports com.hostmdy.attendance.course.controller;
	exports com.hostmdy.attendance.course.view;
	exports com.hostmdy.attendance.course.style;
	
	opens com.hostmdy.attendance.course.model to javafx.base;
	opens com.hostmdy.attendance.course.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.course.main to javafx.graphics,javafx.controls,javafx.fxml;

}