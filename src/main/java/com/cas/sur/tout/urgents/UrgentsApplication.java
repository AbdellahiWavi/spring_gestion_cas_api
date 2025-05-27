package com.cas.sur.tout.urgents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UrgentsApplication {

	public static void main(String[] args) {

		SpringApplication.run(UrgentsApplication.class, args);
	}



}
