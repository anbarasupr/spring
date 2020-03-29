package com.spring.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dao.exception.InsufficientAccountBalanceException;
import com.spring.model.Account;
import com.spring.service.BankService;
import com.spring.service.Impl.BankServiceImpl;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		BankService bankService = ctx.getBean("bankService", BankServiceImpl.class);
		Account fromAccount = new Account();
		fromAccount.setAccountNumber(1122330056L);
		
		Account toAccount = new Account();
		toAccount.setAccountNumber(3355330099L);
		
		try {
			bankService.transferFund(fromAccount, toAccount, 1000.00);
		} catch (InsufficientAccountBalanceException e) {
			e.printStackTrace();	
		}
		ctx.close();

	}

}
