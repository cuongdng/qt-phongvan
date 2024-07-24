package com.berserker.qtpv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QtpvApplication {

	public static void main(String[] args) {
		SpringApplication.run(QtpvApplication.class, args);
	}

}
