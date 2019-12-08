package com.springboot.files.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.files.entities.Account;

@Repository
public interface ReadFileRepository extends CrudRepository<Account, Long> {


}
