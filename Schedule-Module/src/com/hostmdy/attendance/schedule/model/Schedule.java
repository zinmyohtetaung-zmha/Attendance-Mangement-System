package com.hostmdy.attendance.schedule.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Schedule {
	
	private final static Schedule INSTANCE = new Schedule();
	
	public static Schedule getInstance() {
		return INSTANCE;
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty coursecode;
	private SimpleStringProperty coursename;
	private SimpleStringProperty day;
	private SimpleStringProperty timeslot;
	
	public Schedule() {}

	public Schedule(String coursecode,String coursename, String day, String timeslot) {
		super();
		this.coursecode = new SimpleStringProperty(coursecode);
		this.coursename = new SimpleStringProperty(coursename);
		this.day = new SimpleStringProperty(day);
		this.timeslot = new SimpleStringProperty(timeslot);
	}
	
	public Schedule(Integer id, String coursecode,String day, String timeslot) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.coursecode = new SimpleStringProperty(coursecode);
		//this.coursename = new SimpleStringProperty(coursename);
		this.day = new SimpleStringProperty(day);
		this.timeslot = new SimpleStringProperty(timeslot);
	}


	public Schedule(Integer id, String coursecode,String coursename, String day, String timeslot) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.coursecode = new SimpleStringProperty(coursecode);
		this.coursename = new SimpleStringProperty(coursename);
		this.day = new SimpleStringProperty(day);
		this.timeslot = new SimpleStringProperty(timeslot);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getCoursecode() {
		return coursecode.get();
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = new SimpleStringProperty(coursecode);
	}
	
	public String getCoursename() {
		return coursename.get();
	}

	public void setCoursename(String coursecode) {
		this.coursename = new SimpleStringProperty(coursecode);
	}

	public String getDay() {
		return day.get();
	}

	public void setDay(String day) {
		this.day = new SimpleStringProperty(day);
	}

	public String getTimeslot() {
		return timeslot.get();
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = new SimpleStringProperty(timeslot);
	}
	
	

}
