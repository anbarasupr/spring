package com.spring.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement//(proxyTargetClass=true,mode=AdviceMode.PROXY)
@ComponentScan(basePackages = {"com.spring.repositories", "com.spring.services"})
public class ProjectConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/product_category");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        System.out.println("TransactionIsolation:"+dataSource.getConnection().getTransactionIsolation());
    	System.out.println("AutoCommit:"+dataSource.getConnection().getAutoCommit());

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
