package org.software.open.source.database.migrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DatabaseMigrationsApplication {

	public static void main(String[] args) {
		log.info("Test CI");
		SpringApplication.run(DatabaseMigrationsApplication.class, args);
	}

}
