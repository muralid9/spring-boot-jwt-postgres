package com.cmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmp.model.TansactionDetailsDao;

public interface TransactionDetailsRepository extends CrudRepository<TansactionDetailsDao, Integer> {

	 @Query(value = "SELECT id, source_account, destination_account, source_type, destination_type, amount, transaction_date"
	 		+ "	FROM bank.transactions_details where "
	 		+ "	destination_account =?1 or source_account =?2", nativeQuery = true)
	 List<TansactionDetailsDao> getTransactionDetails(long accountNumber, long accountNumber2);

}
