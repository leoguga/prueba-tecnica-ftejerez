package com.ftejerez.prueba;

import com.ftejerez.prueba.model.Event;
import com.ftejerez.prueba.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PruebaApplication {

	private static final Logger log = LoggerFactory.getLogger(PruebaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EventRepository repository) {
		return (args) -> {
			// fetch all events
			log.info("Events stored by default:");
			log.info("-------------------------------");
			for (Event event : repository.findAll()) {
				log.info(event.toString());
			}
			log.info("");
		};
	}

}
