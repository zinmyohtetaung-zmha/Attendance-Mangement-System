module com.hostmdy.attendance.attendance {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	
	exports com.hostmdy.attendance.attendance.controller;
	
	
//	opens com.hostmdy.attendance.attendance.model to javafx.base;
	opens com.hostmdy.attendance.attendance.controller to javafx.graphics, javafx.controls, javafx.fxml;
	opens com.hostmdy.attendance.attendance.main to javafx.graphics, javafx.controls, javafx.base;
	
	
}