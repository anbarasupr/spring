package com.spring.distributed.jta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.XADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class JtaDistributedApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtaDistributedApplication.class, args);
	}

	// used to wrap a datasource with jta transaction for distributed transaction.
	/*
	 * JTA Transaction manager is responsbile for handling transactions and rollback with distributed which means different datasources.
	 * Jdbc Transaction manager which is by default enabled and can handle transactions and rollback within single datasource only
	 */
	private final XADataSourceWrapper dataSourceWrapper;
	
	
	public JtaDistributedApplication(XADataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	@Bean
	DataSource ds1() throws Exception {
		// return datasource("ds1");
		return this.dataSourceWrapper.wrapDataSource(datasource("ds1"));
	}

	@Bean
	DataSource ds2() throws Exception {
		// return datasource("ds2");
		return this.dataSourceWrapper.wrapDataSource(datasource("ds2"));
	}

	@Bean
	DataSourceInitializer ds1Init(@Qualifier("ds1") DataSource ds) {
		return init(ds, "ds1");
	}

	@Bean
	DataSourceInitializer ds2Init(@Qualifier("ds2") DataSource ds) {
		return init(ds, "ds2");
	}

	private DataSourceInitializer init(DataSource dataSource, String schemaFileName) {
		System.out.println("DataSourceInitializer schemaFileName ::::::::::::::::::: "+schemaFileName);

		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer
				.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource(schemaFileName + ".sql")));
		return dataSourceInitializer;
	}

	private JdbcDataSource datasource(String ds) {
		System.out.println("datasource creating ::::::::::::::::::: "+ds);
		JdbcDataSource jdbcDataSource = new JdbcDataSource();
		jdbcDataSource.setURL("jdbc:h2:./" + ds);
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		return jdbcDataSource;
	}

	@org.springframework.web.bind.annotation.RestController
	public static class RestController {

		private final JdbcTemplate jdbcTemplate1, jdbcTemplate2;

		public RestController(DataSource ds1, DataSource ds2) {
			this.jdbcTemplate1 = new JdbcTemplate(ds1);
			this.jdbcTemplate2 = new JdbcTemplate(ds2);
		}

		@GetMapping("/pets")
		public Collection<Map<String, String>> petRead() {
			return this.jdbcTemplate1.query("SELECT * FROM PET", (resultSet, i) -> {
				Map<String, String> msg = new HashMap<>();
				msg.put("id", resultSet.getString("ID"));
				msg.put("nickname", resultSet.getString("NICKNAME"));
				return msg;
			});
		}
		
		@GetMapping("/messages")
		public Collection<Map<String, String>> messageRead() {
			return this.jdbcTemplate2.query("SELECT * FROM MESSAGE", (resultSet, i) -> {
				Map<String, String> msg = new HashMap<>();
				msg.put("id", resultSet.getString("ID"));
				msg.put("message", resultSet.getString("MESSAGE"));
				return msg;
			});
		}

		@PostMapping
		@Transactional
		// rollback within distributed (different) datasources
		public String write(@RequestBody Map<String, String> payload, @RequestParam Optional<Boolean> rollback) {
			String id = UUID.randomUUID().toString();

			String name = payload.get("name");
			String msg = "Hello " + name + " !";
			// below are 2 different datasources
			this.jdbcTemplate1.update("INSERT INTO PET (ID,NICKNAME) VALUES (?,?)", id, name);

			this.jdbcTemplate2.update("INSERT INTO MESSAGE (ID,MESSAGE) VALUES (?,?)", id, msg);

			if (rollback.orElse(false)) {
				throw new RuntimeException("couldn't write the messages in distributed databases! rollbacking... :)");
			}
			
			return "Successfully commited in distributed database, name:"+name;

		}

	}
}
