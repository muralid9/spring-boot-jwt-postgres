package com.cmp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_details", schema = "bank")
public class CustomerDetailsDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "customer_id", updatable = false)
	private long customerId;
	
	@Column(name = "customer_name")
	private String customername;
	@Column(name = "customer_email")
	private String customerEmail;
	@Column(name = "contact_details")
	private String contactDetails;
	@Column(name = "address")
	private String address;
	
	@Column(name = "kyc_id")
	private String kycId;
	
	@Column(name = "kyc_status")
	private String kycStatus;
	
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
	public String getKycStatus() {
		return kycStatus;
	}
	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public String getKycId() {
		return kycId;
	}
	public void setKycId(String kycId) {
		this.kycId = kycId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="customerDetailsDao")
	@JsonIgnore
	private List<AccountDetailsDao> accountDetailsDao;
	public List<AccountDetailsDao> getAccountDetailsDao() {
		return accountDetailsDao;
	}
	public void setAccountDetailsDao(List<AccountDetailsDao> accountDetailsDao) {
		this.accountDetailsDao = accountDetailsDao;
	}
		
}
