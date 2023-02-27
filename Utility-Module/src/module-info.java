module com.hostmdy.attendance.utility {
	exports com.hostmdy.attendance.utility.test;
	exports com.hostmdy.attendance.utility.crypto;
	exports com.hostmdy.attendance.utility.notification;
	requires transitive TrayNotification;
	requires org.junit.jupiter.api;
	requires javafx.base;
	
	opens com.hostmdy.attendance.utility.notification to TrayNotification,javafx.base;  
}