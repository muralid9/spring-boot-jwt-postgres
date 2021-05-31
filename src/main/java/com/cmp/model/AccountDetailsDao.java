package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account_details", schema = "bank")
public class AccountDetailsDao {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_account_number", updatable = false)
	private long bankAccountNumber;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="interest_rate")
	private double interestRate;
	
	
	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
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
	
	

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private CustomerDetailsDao customerDetailsDao;

	public CustomerDetailsDao getCustomerDetailsDao() {
		return customerDetailsDao;
	}

	public void setCustomerDetailsDao(CustomerDetailsDao customerDetailsDao) {
		this.customerDetailsDao = customerDetailsDao;
	}	
	
}
