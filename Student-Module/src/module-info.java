module com.hostmdy.attendance.student {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires com.hostmdy.attendance.database;
	requires transitive javafx.graphics;
	requires com.hostmdy.attendance.utility;
	
	exports com.hostmdy.attendance.student.test;
	exports com.hostmdy.attendance.student.model;
	exports com.hostmdy.attendance.student.controller;
	exports com.hostmdy.attendance.student.style;
	exports com.hostmdy.attendance.student.view;
	
	
	opens com.hostmdy.attendance.student.model to javafx.base;
	opens com.hostmdy.attendance.student.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.student.main to javafx.graphics,javafx.controls,javafx.fxml;

}