module Teacher_Course_Module {
	requires java.sql;
	requires javafx.base;
	requires com.hostmdy.attendance.database;
	requires javafx.fxml;
	requires javafx.controls;
	requires Teacher_Module;
	requires com.hostmdy.attendance.course;
	
	exports com.hostmdy.attendance.teacher_course.controller;
	exports com.hostmdy.attendance.teacher_course.model;
	exports com.hostmdy.attendance.teacher_course.view;
	exports com.hostmdy.attendance.teacher_course.style;
	
	opens com.hostmdy.attendance.teacher_course.model to javafx.base;
	opens com.hostmdy.attendance.teacher_course.controller to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.teacher_course.main to javafx.graphics,javafx.controls,javafx.fxml;
}