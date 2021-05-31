package com.cmp.model;

public class CustomerDetailsDto {
	
	private long customerId;
	private String customername;
	private String customerEmail;
	private String contactDetails;
	private String address;
	private double balance;
	private double interestRate;
	private String accountType;
	private String kycId;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getKycId() {
		return kycId;
	}
	public void setKycId(String kycId) {
		this.kycId = kycId;
	}
	

}
