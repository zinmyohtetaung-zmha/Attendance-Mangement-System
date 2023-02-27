package com.hostmdy.attendance.course.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
	
	private final static Course INSTANCE = new Course();

	public static Course getInstance() {
		return INSTANCE;
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty code;
	private SimpleIntegerProperty credit;
	private SimpleStringProperty semester;
	
	public Course() {}

	public Course(String name, String code, Integer credit, String semester) {
		super();
		this.name = new SimpleStringProperty(name);
		this.code = new SimpleStringProperty(code);
		this.credit = new SimpleIntegerProperty(credit);
		this.semester = new SimpleStringProperty(semester);
	}

	public Course(Integer id, String name, String code, Integer credit, String semester) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.code = new SimpleStringProperty(code);
		this.credit = new SimpleIntegerProperty(credit);
		this.semester = new SimpleStringProperty(semester);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getCode() {
		return code.get();
	}

	public void setCode(String code) {
		this.code = new SimpleStringProperty(code);
	}

	public Integer getCredit() {
		return credit.get();
	}

	public void setCredit(Integer credit) {
		this.credit = new SimpleIntegerProperty(credit);
	}
	
	public String getSemester() {
		return semester.get();
	}

	public void setSemester(String semester) {
		this.semester = new SimpleStringProperty(semester);
	}
	
	
}
