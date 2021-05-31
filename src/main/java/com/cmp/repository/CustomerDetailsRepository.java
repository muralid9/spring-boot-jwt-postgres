package com.cmp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmp.model.CustomerDetailsDao;

public interface CustomerDetailsRepository extends CrudRepository<CustomerDetailsDao, Integer> {
	Iterable<CustomerDetailsDao> findAll();

    @Query(value = "select id, customer_id, customer_name, customer_email, address, kyc_status, contact_details, kyc_id"
    		+ "	FROM bank.customer_details where kyc_id = ?1", nativeQuery = true)
	CustomerDetailsDao findByKyc(String kycId);

    @Query(value = "select id, customer_id, customer_name, customer_email, address, kyc_status, contact_details, kyc_id"
    		+ "	FROM bank.customer_details where customer_id = ?1", nativeQuery = true)
	CustomerDetailsDao findByCustId(int custId);
	
}

