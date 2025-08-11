package com.kartaguez.bluepot.infrastructure.up.dummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kartaguez.bluepot")
public class DummyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyApplication.class, args);
	}

}
