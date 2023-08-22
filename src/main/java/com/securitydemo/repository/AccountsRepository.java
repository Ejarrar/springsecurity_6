package com.securitydemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.securitydemo.model.Account;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {
	
	Account findByCustomerId(Long customerId);

}
