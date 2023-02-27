package com.hostmdy.attendance.enrollment.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Enrollment {
	
	private final static Enrollment INSTANCE = new Enrollment();
	
	public static Enrollment getInstance() {
		return INSTANCE;
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty studentid;
	private SimpleStringProperty studentname;
	private SimpleStringProperty year;
	private SimpleStringProperty major;
	private SimpleStringProperty academicyear;
	
	public Enrollment() {}

	public Enrollment(String studentid, String studentname, String year, String major, String academicyear) {
		super();
		this.studentid = new SimpleStringProperty(studentid);
		this.studentname = new SimpleStringProperty(studentname);
		this.year = new SimpleStringProperty(year);
		this.major = new SimpleStringProperty(major);
		this.academicyear = new SimpleStringProperty(academicyear);
	}

	public Enrollment(Integer id, String studentid, String studentname, String year, String major, String academicyear) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.studentid = new SimpleStringProperty(studentid);
		this.studentname = new SimpleStringProperty(studentname);
		this.year = new SimpleStringProperty(year);
		this.major = new SimpleStringProperty(major);
		this.academicyear = new SimpleStringProperty(academicyear);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getStudentid() {
		return studentid.get();
	}

	public void setStudentid(String studentid) {
		this.studentid = new SimpleStringProperty(studentid);
	}

	public String getStudentname() {
		return studentname.get();
	}

	public void setStudentname(String studentname) {
		this.studentname = new SimpleStringProperty(studentname);
	}

	public String getYear() {
		return year.get();
	}

	public void setYear(String year) {
		this.year = new SimpleStringProperty(year);
	}

	public String getMajor() {
		return major.get();
	}

	public void setMajor(String major) {
		this.major = new SimpleStringProperty(major);
	}

	public String getAcademicyear() {
		return academicyear.get();
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = new SimpleStringProperty(academicyear);
	}
	
	
	
}
