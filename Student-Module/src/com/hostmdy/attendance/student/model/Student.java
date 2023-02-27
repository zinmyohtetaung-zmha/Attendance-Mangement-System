package com.hostmdy.attendance.student.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	
	private final static Student INSTANCE = new Student();
	
	public static Student getInstance() {
		return INSTANCE;
	}
	private SimpleIntegerProperty id;
	private SimpleStringProperty studentid;
	private SimpleStringProperty firstname;
	private SimpleStringProperty lastname;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	private SimpleStringProperty nrc;
	private SimpleStringProperty address;
	
	public Student() {}
	
	public Student(String studentid, String firstname, String lastname, String email, String phone, String nrc,
			String address) {
		super();
		this.studentid = new SimpleStringProperty(studentid);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.nrc = new SimpleStringProperty(nrc);
		this.address = new SimpleStringProperty(address);
	}
	public Student(Integer id, String studentid, String fristname, String lastname, String email, String phone,
			String nrc, String address) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.studentid = new SimpleStringProperty(studentid);
		this.firstname = new SimpleStringProperty(fristname);
		this.lastname = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.nrc = new SimpleStringProperty(nrc);
		this.address = new SimpleStringProperty(address);
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
	public String getFirstname() {
		return firstname.get();
	}
	public void setFristname(String firstname) {
		this.firstname = new SimpleStringProperty(firstname);
	}
	public String getLastname() {
		return lastname.get();
	}
	public void setLastname(String lastname) {
		this.lastname = new SimpleStringProperty(lastname);
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
	
	

}
