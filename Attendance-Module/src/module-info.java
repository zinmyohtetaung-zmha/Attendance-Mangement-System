module com.hostmdy.attendance {

	requires java.sql;
	requires javafx.base;
	requires transitive javafx.controls;
	requires com.hostmdy.attendance.database;
	requires javafx.fxml;
	requires com.hostmdy.attendance.course;
	requires com.hostmdy.attendance.utility;
	requires javafx.graphics;
	
	exports com.hostmdy.attendance.view;
	exports com.hostmdy.attendance.style;
	exports com.hostmdy.attendance.controller;
	exports com.hostmdy.attendance.model;
	
	opens com.hostmdy.attendance.model to javafx.base;
	opens com.hostmdy.attendance.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.main to javafx.graphics,javafx.controls,javafx.fxml;
}