package com.securitydemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.securitydemo.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

	// @PreAuthorize("hasRole('USER')")
	List<Loan> findByCustomerIdOrderByStartDtDesc(Long customerId);

}
