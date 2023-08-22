package com.securitydemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.securitydemo.model.AccountTransaction;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransaction, Long> {
	
	List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(Long customerId);

}
