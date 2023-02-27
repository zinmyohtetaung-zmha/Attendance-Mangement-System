module Teacher_Module {
	requires java.sql;
	requires com.hostmdy.attendance.database;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires com.hostmdy.attendance.utility;
	
	exports com.hostmdy.attendance.teacher.model;
	exports com.hostmdy.attendance.teacher.controller;
	exports com.hostmdy.attendance.teacher.view;
	exports com.hostmdy.attendance.teacher.style;
	
	opens com.hostmdy.attendance.teacher.model to javafx.base;
	opens com.hostmdy.attendance.teacher.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.teacher.main to javafx.graphics,javafx.controls,javafx.fxml;
}