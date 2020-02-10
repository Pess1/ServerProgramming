package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner book(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("War"));
			brepository.save(new Book("Catcher in the rye", "Some dude who likes the n word", 1969, "A1111", 19.99, crepository.findByName("Fiction").get(0)));
			brepository.save(new Book("Tuntematon Sotilas", "Väinö Linna", 1948, "B1111", 40.00, crepository.findByName("War").get(0)));
		};
	}

}
