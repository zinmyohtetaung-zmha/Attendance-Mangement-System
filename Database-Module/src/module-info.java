module com.hostmdy.attendance.database {
	exports com.hostmdy.attendance.database.connection;
	requires transitive java.sql;
	requires org.junit.jupiter.api;
}