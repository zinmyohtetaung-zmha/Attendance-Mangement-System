package com.hostmdy.attendance.teacher.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Teacher {
	private static final Teacher INSTANCE = new Teacher();
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty staffId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	private SimpleStringProperty nrc;
	private SimpleStringProperty address;
	private SimpleStringProperty faculty;
	private SimpleStringProperty status;
	
	public static Teacher getInstance() {
		return INSTANCE;
	}

	public Teacher() {};
	
	public Teacher(int id, String staffId, String firstName, String lastName, String email,
			String phone, String nrc, String address, String faculty,String status) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.staffId = new SimpleStringProperty(staffId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.nrc = new SimpleStringProperty(nrc);
		this.address = new SimpleStringProperty(address);
		this.faculty = new SimpleStringProperty(faculty);
		this.status = new SimpleStringProperty(status);
	}
	public Teacher(String staffId, String firstName, String lastName, String email, String phone,
			String nrc,String address, String faculty,String status) {
		super();
		this.staffId = new SimpleStringProperty(staffId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.nrc = new SimpleStringProperty(nrc);
		this.address = new SimpleStringProperty(address);
		this.faculty = new SimpleStringProperty(faculty);
		this.status = new SimpleStringProperty(status);
	}
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public String getStaffId() {
		return staffId.get();
	}
	public void setStaffId(String staffId) {
		this.staffId = new SimpleStringProperty(staffId);
	}
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	public String getNrc() {
		return nrc.get();
	}
	public void setNrc(String nrc) {
		this.nrc = new SimpleStringProperty(nrc);
	}
	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	public String getFaculty() {
		return faculty.get();
	}
	public void setFaculty(String faculty) {
		this.faculty = new SimpleStringProperty(faculty);
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
	}
	

	
}
