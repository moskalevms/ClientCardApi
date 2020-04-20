package ru.sberbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource("file:src/main/resources/private.properties")
@EnableTransactionManagement
public class ClientCardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientCardApiApplication.class, args);
	}

}
