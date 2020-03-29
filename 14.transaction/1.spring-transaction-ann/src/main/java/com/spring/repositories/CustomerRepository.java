package com.spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCustomer(String name) {
        String sql = "INSERT INTO customer VALUES (NULL, ?)";
        jdbcTemplate.update(sql, name);
		// throw new RuntimeException(":( Repository - "+name+" ):");
    }
}
