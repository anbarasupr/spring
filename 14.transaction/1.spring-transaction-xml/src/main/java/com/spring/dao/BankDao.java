package com.spring.dao;

import com.spring.dao.exception.InsufficientAccountBalanceException;
import com.spring.model.Account;

public interface BankDao {
	public abstract void deposit(Account fromAccount,Account toAccount,Double amount);
	public abstract void withdraw(Account fromAccount,Account toAccount,Double amount) throws InsufficientAccountBalanceException;
}
