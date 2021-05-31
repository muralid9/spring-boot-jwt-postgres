package com.cmp.model;

public class AccountDetailsDto {

	private Long bankAccountNumber;
	
	private double balance;
	
	private double interestRate;
	
	private Integer custmoreId;
	private String accountType;

	public Long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(Long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
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

	public Integer getCustmoreId() {
		return custmoreId;
	}

	public void setCustmoreId(Integer custmoreId) {
		this.custmoreId = custmoreId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
}
