package org.software.open.source.spring.template;

import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		log.info("Project start at: [{}]", ZonedDateTime.now());
		SpringApplication.run(Application.class, args);
	}

}
