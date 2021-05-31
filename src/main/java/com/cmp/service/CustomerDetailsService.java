package com.cmp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmp.model.AccountDetailsDao;
import com.cmp.model.AccountDetailsDto;
import com.cmp.model.CustomerDetailsDao;
import com.cmp.model.CustomerDetailsDto;
import com.cmp.model.CustomerResponseDto;
import com.cmp.model.DeleteAccountDetailsDto;
import com.cmp.model.DownloadtransactionDetailsdto;
import com.cmp.model.KycStatus;
import com.cmp.model.TansactionDetailsDao;
import com.cmp.model.TransactionDetailsDto;
import com.cmp.model.TransactionType;
import com.cmp.repository.AccountDetailsRepository;
import com.cmp.repository.CustomerDetailsRepository;
import com.cmp.repository.TransactionDetailsRepository;

@Service
public class CustomerDetailsService {

	@Autowired
	CustomerDetailsRepository custDetailsRepo;

	@Autowired
	AccountDetailsRepository accDetailsRepo;

	@Autowired
	TransactionDetailsRepository transactionRepo;

	private static Logger logger = Logger.getLogger(CustomerDetailsService.class);

	public Iterable<CustomerDetailsDao> getAllCustomers() {
		Iterable<CustomerDetailsDao> customerDetailsLst = custDetailsRepo.findAll();
		return customerDetailsLst;

	}

	public CustomerDetailsDao save(CustomerDetailsDto custDetails, CustomerResponseDto response) {
		CustomerDetailsDao newcustomer = null;
		try {
			newcustomer = custDetailsRepo.findByKyc(custDetails.getKycId());
			if (newcustomer == null) {
				newcustomer = new CustomerDetailsDao();
				newcustomer.setAddress(custDetails.getAddress());
				newcustomer.setContactDetails(custDetails.getContactDetails());
				newcustomer.setKycStatus(KycStatus.PENDING.toString());
				newcustomer.setCustomerEmail(custDetails.getCustomerEmail());
				newcustomer.setCustomername(custDetails.getCustomername());
				newcustomer.setKycId(custDetails.getKycId());
				custDetailsRepo.save(newcustomer);
				AccountDetailsDao accDetails = new AccountDetailsDao();
				accDetails.setBalance(custDetails.getBalance());
				accDetails.setInterestRate(custDetails.getInterestRate());
				accDetails.setAccountType(custDetails.getAccountType());
				accDetails.setCustomerDetailsDao(newcustomer);
				accDetailsRepo.save(accDetails);
			} else {
				logger.info("Customer Already Exist in the Bank");
				response.setErrorMessage("Customer Already Exist in the Bank");
			}
		} catch (Exception e) {
			logger.error("creating customer details ", e);
		}
		return newcustomer;
	}

	public AccountDetailsDao saveAccount(AccountDetailsDto accountDetails) {
		CustomerDetailsDao newcustomer = null;
		AccountDetailsDao accDetails = new AccountDetailsDao();
		try {
			newcustomer = custDetailsRepo.findByCustId(accountDetails.getCustmoreId());
			accDetails.setBalance(accountDetails.getBalance());
			accDetails.setInterestRate(accountDetails.getInterestRate());
			accDetails.setAccountType(accountDetails.getAccountType());
			accDetails.setCustomerDetailsDao(newcustomer);
			accDetailsRepo.save(accDetails);
			custDetailsRepo.save(newcustomer);
		} catch (Exception e) {
			logger.error("creating account details ", e);
		}
		return accDetails;
	}

	public void deleteAccount(DeleteAccountDetailsDto deleteAccountDetails) {
		try {
			AccountDetailsDao accDetail1s = accDetailsRepo.findBankAccount(deleteAccountDetails.getBankAccountNumber());
			if (accDetail1s == null) {
				deleteAccountDetails.setErrorMessage("Account Number Does not exit ");
			} else if (accDetail1s.getBalance() > 0) {
				deleteAccountDetails
						.setErrorMessage("Account Number has Balance in account " + accDetail1s.getBalance());
			} else {
				int status = accDetailsRepo.deleteBankAccount(deleteAccountDetails.getBankAccountNumber());
				if (status >= 1) {
					deleteAccountDetails.setErrorMessage("Account Deleted successfully ");
				}
			}
		} catch (Exception e) {
			logger.error("delete account details ", e);
		}
	}

	@Transactional
	public void transferAmount(TransactionDetailsDto transactionDetails) {
		
		
		try {
			AccountDetailsDao fromAccDetail1s = accDetailsRepo.findBankAccount(transactionDetails.getSourceAccount());
			AccountDetailsDao toAccDetail1s = accDetailsRepo.findBankAccount(transactionDetails.getDestinationAccount());
			if(fromAccDetail1s==null || toAccDetail1s==null) {
				transactionDetails.setErrorMessage("Source or Destination accounts not available");
			}else if(transactionDetails.getAmount()>fromAccDetail1s.getBalance()) {
				transactionDetails.setErrorMessage("Insufficient Balanace to Transfer");
			}else {
				TansactionDetailsDao transactionDao = new TansactionDetailsDao();
				transactionDao.setSourceAccount(transactionDetails.getSourceAccount());
				transactionDao.setSourceType(TransactionType.Cr.toString());
				transactionDao.setDestinationAccount(transactionDetails.getDestinationAccount());
				transactionDao.setDestinationType(TransactionType.Dr.toString());
				transactionDao.setAmount(transactionDetails.getAmount());
				transactionDao.setTransactionDate(LocalDate.now());
				transactionRepo.save(transactionDao);
				accDetailsRepo.updateAccountBalance(fromAccDetail1s.getBalance()-transactionDetails.getAmount(),fromAccDetail1s.getBankAccountNumber());
				accDetailsRepo.updateAccountBalance(toAccDetail1s.getBalance()+transactionDetails.getAmount(),toAccDetail1s.getBankAccountNumber());
			}
		} catch (Exception e) {
			logger.error("transfer money details ", e);
		}
		
	}

	public List<TansactionDetailsDao> getTransactionDetails(DownloadtransactionDetailsdto accountDetails) {
		List<TansactionDetailsDao> responseVal = new ArrayList<>();
		try {
			responseVal = transactionRepo.getTransactionDetails(accountDetails.getAccountNumber(),
					accountDetails.getAccountNumber());
			System.out.println(responseVal);
		} catch (Exception e) {
			logger.error("Transaction  Details details ", e);
		}
		return responseVal;
	}



}
