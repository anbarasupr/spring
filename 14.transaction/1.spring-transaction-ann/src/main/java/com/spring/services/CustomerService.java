package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.spring.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	@Transactional
	// rollsback the RuntimeException but does not rollback the checked exception
	public void addCustomer(String customerName) {
		System.out.println("service : add customer starts :"+customerName);
		customerRepository.addCustomer(customerName);
		System.out.println("service : add customer ends :"+customerName);
		System.out.println("service : after add customer exception throws starts :"+customerName);
		// throw new RuntimeException(":( Service - "+customerName+" ):");
	}
}
