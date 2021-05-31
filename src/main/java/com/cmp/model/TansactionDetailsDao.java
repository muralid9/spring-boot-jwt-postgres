package com.cmp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "transactions_details", schema = "bank")
public class TansactionDetailsDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id", updatable = false)
	private long Id;
	
	
	@Column(name ="source_account")
	private long sourceAccount;
	@Column(name = "destination_account")
	private long destinationAccount;
	@Column(name = "source_type")
	private String sourceType;
	@Column(name = "destination_type")
	private String destinationType;
	@Column(name = "amount")
	private double amount;
	//mm-dd-yyyy
	@Column(name = "transaction_date")
	private LocalDate transactionDate;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(long sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public long getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(long destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate localDate) {
		this.transactionDate = localDate;
	}

	

}
