package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner book(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("Catcher in the rye", "Some dude who likes the n word", 1969, "A1111", 19.99));
			repository.save(new Book("Tuntematon Sotilas", "Väinö Linna", 1948, "B1111", 40.00));
		};
	}

}
