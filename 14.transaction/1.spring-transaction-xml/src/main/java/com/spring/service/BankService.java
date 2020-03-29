package com.spring.service;

import com.spring.dao.exception.InsufficientAccountBalanceException;
import com.spring.model.Account;

public interface BankService {
	public abstract void transferFund(Account fromAccount,Account toAccount,Double amount)throws InsufficientAccountBalanceException;
}
