module com.hostmdy.attendance.admin {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires com.hostmdy.attendance.database;
	requires com.hostmdy.attendance.student;
	requires com.hostmdy.attendance.enrollment;
	requires com.hostmdy.attendance.course;
	requires com.hostmdy.attendance.schedule;
	requires com.hostmdy.attendance;
	requires Teacher_Course_Module;
	requires Teacher_Module;
	requires com.hostmdy.attendance.report;

	exports com.hostmdy.attendance.admin.test;
	exports com.hostmdy.attendance.admin.model;
	exports com.hostmdy.attendance.admin.controller;
	exports com.hostmdy.attendance.admin.style;
	exports com.hostmdy.attendance.admin.view;
	
	opens com.hostmdy.attendance.admin.model to javafx.base;
	opens com.hostmdy.attendance.admin.main to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.admin.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.admin.view to javafx.graphics,javafx.controls,javafx.fxml;

	
	
}