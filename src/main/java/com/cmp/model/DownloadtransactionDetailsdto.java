package com.cmp.model;

import java.time.LocalDate;

public class DownloadtransactionDetailsdto {
	
	private long accountNumber;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	} 
	
	

}
