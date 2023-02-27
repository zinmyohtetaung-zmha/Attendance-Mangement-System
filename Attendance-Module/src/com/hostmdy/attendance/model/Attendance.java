package com.hostmdy.attendance.model;

import java.time.LocalDate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;

public class Attendance {
	private SimpleIntegerProperty id;
	private SimpleStringProperty studentId;
	private SimpleIntegerProperty enrollmentId;
	private SimpleIntegerProperty scheduleId;
	private SimpleStringProperty date;
	private SimpleStringProperty studentName;
	private SimpleStringProperty year;
	private SimpleStringProperty course;
	private RadioButton present;
	private RadioButton absent;
	private SimpleStringProperty semester;
	private SimpleStringProperty status;
	private SimpleBooleanProperty isPresent;
	private SimpleStringProperty courseCode;
	
	private static final Attendance INSTANCE = new Attendance();
	
	public Attendance() {};
	
	
	public Attendance(int id, int enrollmentId, int scheduleId, LocalDate date) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.enrollmentId = new SimpleIntegerProperty(enrollmentId);
		this.scheduleId = new SimpleIntegerProperty(scheduleId);
		this.date = new SimpleStringProperty(date.toString());
	}

	public Attendance(int enrollmentId, int scheduleId, LocalDate date,boolean isPresent) {
		super();
		this.enrollmentId = new SimpleIntegerProperty(enrollmentId);
		this.scheduleId = new SimpleIntegerProperty(scheduleId);
		this.date = new SimpleStringProperty(date.toString());
		this.isPresent = new SimpleBooleanProperty(isPresent);
	}

	public Attendance(int id, String studentName, String year, String course) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.studentName = new SimpleStringProperty(studentName);
		this.year = new SimpleStringProperty(year);
		this.course = new SimpleStringProperty(course);
	}

	public Attendance(String studentName, String year, String course) {
		super();
		this.studentName = new SimpleStringProperty(studentName);
		this.year = new SimpleStringProperty(year);
		this.course = new SimpleStringProperty(course);	}
	
	

	public Attendance(int id, String studentName, RadioButton present,
			RadioButton absent,String studentId,String year) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.studentName = new SimpleStringProperty(studentName);
		this.present = present;
		this.absent = absent;
		this.studentId = new SimpleStringProperty(studentId);
		this.year = new SimpleStringProperty(year);
	}
	
	
	
	public Attendance(int id, String studentName, String year,
			String course, String studentId, LocalDate date, String status) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.studentName = new SimpleStringProperty(studentName);
		this.year = new SimpleStringProperty(year);
		this.course = new SimpleStringProperty(course);
		this.studentId = new SimpleStringProperty(studentId);
		this.date = new SimpleStringProperty(date.toString());
		this.status = new SimpleStringProperty(status);
	}

	public String getStudentId() {
		return studentId.get();
	}

	public void setStudentId(String studentId) {
		this.studentId = new SimpleStringProperty(studentId);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public int getEnrollmentId() {
		return enrollmentId.get();
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = new SimpleIntegerProperty(enrollmentId);
	}

	public int getScheduleId() {
		return scheduleId.get();
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = new SimpleIntegerProperty(scheduleId);
	}

	public LocalDate getDate() {
		return LocalDate.parse(date.get());
	}

	public void setDate(LocalDate date) {
		this.date = new SimpleStringProperty(date.toString());
	}

	public String getStudentName() {
		return studentName.get();
	}

	public void setStudentName(String studentName) {
		this.studentName = new SimpleStringProperty(studentName);
	}

	public String getYear() {
		return year.get();
	}

	public void setYear(String year) {
		this.year = new SimpleStringProperty(year);
	}

	public String getCourse() {
		return course.get();
	}

	public void setCourse(String course) {
		this.course = new SimpleStringProperty(course);
	}

	public RadioButton getPresent() {
		return present;
	}

	public void setPresent(RadioButton present) {
		this.present = present;
	}

	public RadioButton getAbsent() {
		return absent;
	}

	public void setAbsent(RadioButton absent) {
		this.absent = absent;
	}

	public String getSemester() {
		return semester.get();
	}

	public void setSemester(String semester) {
		this.semester = new SimpleStringProperty(semester);
	}


	public static Attendance getInstance() {
		return INSTANCE;
	}


	public String getStatus() {
		return status.get();
	}


	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
	}


	public boolean isPresent() {
		return isPresent.get();
	}


	public void setPresent(boolean isPresent) {
		this.isPresent = new SimpleBooleanProperty(isPresent);
	}


	public String getCourseCode() {
		return courseCode.get();
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = new SimpleStringProperty(courseCode);
	}
	
	
	
	
}
