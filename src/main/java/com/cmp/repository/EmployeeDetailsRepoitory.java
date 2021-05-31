package com.cmp.repository;

import org.springframework.data.repository.CrudRepository;

import com.cmp.model.EmployeeDetailsDao;

public interface EmployeeDetailsRepoitory extends CrudRepository<EmployeeDetailsDao, Integer> {
	EmployeeDetailsDao findByUsername(String username);
}
