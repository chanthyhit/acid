package com.acid.acid;

import com.acid.acid.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcidApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AcidApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
