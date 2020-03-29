package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CustomerController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/customer",method = RequestMethod.GET)
	public String addCustomer(@RequestParam(value = "name", required = true) String customerName) {
		customerService.addCustomer(customerName);
		return "Add customer : " + customerName + " process is success";
	}
	
	@RequestMapping(value="/error",method = RequestMethod.GET)
	public String error() {
		return "Internal Error Occurred";
	}
}
