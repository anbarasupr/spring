package com.springboot.files.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.files.entities.Account;

public interface ReadFileService {
	String test(String name);

	List<Account> findAll();

	boolean saveDataFromUploadFile(MultipartFile file);

	void store(MultipartFile file);
}
