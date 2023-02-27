module com.hostmdy.attendance.report {
	requires javafx.base;
	requires javafx.fxml;
	requires transitive javafx.controls;
	requires java.sql;
	requires com.hostmdy.attendance;
	requires com.hostmdy.attendance.database;
	
	exports com.hostmdy.attendance.report.controller;
	exports com.hostmdy.attendance.report.view;
	exports com.hostmdy.attendance.report.model;
	
	
	opens com.hostmdy.attendance.report.model to javafx.base;
	opens com.hostmdy.attendance.report.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.report.main to javafx.graphics,javafx.controls,javafx.fxml;
}