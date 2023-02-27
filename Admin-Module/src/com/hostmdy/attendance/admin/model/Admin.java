package com.hostmdy.attendance.admin.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin{
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty firstnmae;
	private SimpleStringProperty lastname;
	private SimpleStringProperty middlename;
	private SimpleStringProperty email;
	private SimpleStringProperty code;
	private SimpleStringProperty password;
	private SimpleStringProperty nrc;
	private SimpleStringProperty phone;
	private SimpleStringProperty address;
	private SimpleStringProperty role;
	public Admin(String firstnmae, String lastname, String middlename, String email, String code, String password,
			String nrc, String phone, String address, String role) {
		super();
		this.firstnmae = new SimpleStringProperty(firstnmae);
		this.lastname = new SimpleStringProperty(lastname);
		this.middlename = new SimpleStringProperty(middlename);
		this.email = new SimpleStringProperty(email);
		this.code = new SimpleStringProperty(code);
		this.password = new SimpleStringProperty(password);
		this.nrc = new SimpleStringProperty(nrc);
		this.phone = new SimpleStringProperty(phone);
		this.address = new SimpleStringProperty(address);
		this.role = new SimpleStringProperty(role);
	}
	public Admin(Integer id, String firstnmae, String lastname, String middlename, String email, String code,
			String password, String nrc, String phone, String address, String role) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.firstnmae = new SimpleStringProperty(firstnmae);
		this.lastname = new SimpleStringProperty(lastname);
		this.middlename = new SimpleStringProperty(middlename);
		this.email = new SimpleStringProperty(email);
		this.code = new SimpleStringProperty(code);
		this.password = new SimpleStringProperty(password);
		this.nrc = new SimpleStringProperty(nrc);
		this.phone = new SimpleStringProperty(phone);
		this.address = new SimpleStringProperty(address);
		this.role = new SimpleStringProperty(role);
	}
	public Integer getId() {
		return id.get();
	}
	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public String getFirstnmae() {
		return firstnmae.get();
	}
	public void setFirstnmae(String firstnmae) {
		this.firstnmae = new SimpleStringProperty(firstnmae);
	}
	public String getLastname() {
		return lastname.get();
	}
	public void setLastname(String lastname) {
		this.lastname = new SimpleStringProperty(lastname);
	}
	public String getMiddlename() {
		return middlename.get();
	}
	public void setMiddlename(String middlename) {
		this.middlename = new SimpleStringProperty(middlename);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	public String getCode() {
		return code.get();
	}
	public void setCode(String code) {
		this.code = new SimpleStringProperty(code);
	}
	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password = new SimpleStringProperty(password);
	}
	public String getNrc() {
		return nrc.get();
	}
	public void setNrc(String nrc) {
		this.nrc = new SimpleStringProperty(nrc);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	public String getRole() {
		return role.get();
	}
	public void setStatus(String role) {
		this.role = new SimpleStringProperty(role);
	}
	
	
	

}
