package com.securitydemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.securitydemo.model.Card;

@Repository
public interface CardsRepository extends CrudRepository<Card, Long> {
	
	List<Card> findByCustomerId(Long customerId);

}
