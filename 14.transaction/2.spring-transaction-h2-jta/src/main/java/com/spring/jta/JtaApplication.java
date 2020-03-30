package com.spring.jta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class JtaApplication {

	private static final String DESTINATION = "messages";

	public static void main(String[] args) {
		SpringApplication.run(JtaApplication.class, args);
	}

	@Service
	public static class MessageNotificationListener {

		@JmsListener(destination = DESTINATION)
		public void onNewMessage(String id) {
			System.out.println("message id : " + id);
		}
	}

	@org.springframework.web.bind.annotation.RestController
	public static class RestController {

		private JmsTemplate jmsTemplate;
		private final JdbcTemplate jdbcTemplate;

		public RestController(JmsTemplate jmsTemplate, JdbcTemplate jdbcTemplate) {
			super();
			this.jmsTemplate = jmsTemplate;
			this.jdbcTemplate = jdbcTemplate;
		}

		@GetMapping
		public Collection<Map<String, String>> read() {

			return this.jdbcTemplate.query("SELECT * FROM MESSAGE", (resultSet, i) -> {
				Map<String, String> msg = new HashMap<>();
				msg.put("id", resultSet.getString("ID"));
				msg.put("message", resultSet.getString("MESSAGE"));
				return msg;
			});

		}

		@PostMapping
		@Transactional
		// rollback within single datasource
		public String write(@RequestBody Map<String, String> payload, @RequestParam Optional<Boolean> rollback) {
			String id = UUID.randomUUID().toString();

			String name = payload.get("name");
			String msg = "Hello " + name + " !";
			this.jdbcTemplate.update("INSERT INTO MESSAGE(ID,MESSAGE) VALUES (?,?)", id, msg);
			this.jmsTemplate.convertAndSend(DESTINATION, id);

			if (rollback.orElse(false)) {
				throw new RuntimeException("couldn't write the message! rollbacking... :)");
			}
			return "Successfully commited in database, name:" + name;
		}

	}
}
