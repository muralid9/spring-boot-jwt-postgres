package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_employee_details", schema = "bank")
public class EmployeeDetailsDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", updatable = false)
	private long emloyeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "contact_details")
	private String contactDetails;

	@Column(name = "login_username")
	private String username;

	@Column(name = "password")
	private String password;
	
	
	public long getEmloyeeId() {
		return emloyeeId;
	}

	public void setEmloyeeId(long emloyeeId) {
		this.emloyeeId = emloyeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
