package com.springboot.files.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "account_name")
	private String accountName;
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "withdrawal_amount")
	private String withdrawalAmount;
	@Column(name = "file_type")
	private String fileType;

	@Transient
	private MultipartFile file;

	public Account() {
		super();
	}

	public Account(String accountName, String accountNumber, String withdrawalAmount, String fileType) {
		super();
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.withdrawalAmount = withdrawalAmount;
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(String withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
