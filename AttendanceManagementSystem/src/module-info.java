module com.hostmdy.attendance.common {
	requires com.hostmdy.attendance.admin;
	requires com.hostmdy.attendance.database;
	requires com.hostmdy.attendance.student;
	requires com.hostmdy.attendance.utility;
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens com.hostmdy.attendance.common.register to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.common.main to javafx.graphics,javafx.controls,javafx.fxml;
	opens com.hostmdy.attendance.common.main_login to javafx.graphics,javafx.controls,javafx.fxml;

}