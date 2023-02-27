module com.hostmdy.attendance.enrollment {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires com.hostmdy.attendance.database;
	requires com.hostmdy.attendance.student;
	requires com.hostmdy.attendance.utility;
	
	exports com.hostmdy.attendance.enrollment.model;
	exports com.hostmdy.attendance.enrollment.controller;
	exports com.hostmdy.attendance.enrollment.style;
	exports com.hostmdy.attendance.enrollment.view;
	
	opens com.hostmdy.attendance.enrollment.model to javafx.base;
	opens com.hostmdy.attendance.enrollment.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.enrollment.main to javafx.graphics,javafx.controls,javafx.fxml;

}