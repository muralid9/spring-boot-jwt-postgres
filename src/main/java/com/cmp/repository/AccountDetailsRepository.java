package com.cmp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmp.model.AccountDetailsDao;

public interface AccountDetailsRepository extends CrudRepository<AccountDetailsDao, Integer> {

	 @Query(value = "select bank_account_number, balance, customer_id, interest_rate, account_type\r\n"
	 		+ "	FROM bank.account_details where bank_account_number = ?1", nativeQuery = true)
	AccountDetailsDao findBankAccount(Long bankAccountNumber);

	 @Modifying
	 @Transactional
	 @Query(value = "Delete FROM bank.account_details where bank_account_number = ?1", nativeQuery = true)
	 int deleteBankAccount(long bankAccountNumber);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "Update bank.account_details set  balance =?1 where bank_account_number = ?2", nativeQuery = true)
	 int updateAccountBalance(double balance , long bankAccountNumber);

}
