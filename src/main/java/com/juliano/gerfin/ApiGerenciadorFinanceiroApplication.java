package com.juliano.gerfin;

import com.juliano.gerfin.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiGerenciadorFinanceiroApplication implements CommandLineRunner {

	@Autowired
	private ContaRepository contaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiGerenciadorFinanceiroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
