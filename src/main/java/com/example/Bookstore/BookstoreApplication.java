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
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner book(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("War"));
			brepository.save(new Book("Catcher in the rye", "Some dude who likes the n word", 1969, "A1111", 19.99, crepository.findByName("Fiction").get(0)));
			brepository.save(new Book("Tuntematon Sotilas", "Väinö Linna", 1948, "B1111", 40.00, crepository.findByName("War").get(0)));
		
			User user1 = new User("user", "$2y$12$Z82xuqrw.SWLcnc/NAJZPeLY9IZDs.NNMXpT9hG7tV91vLlWvXeru", "user@email.com", "USER");
			User user2 = new User("admin", "$2y$12$Vh1mFFnzjglTzkuVWjfXhOC1FW5HUes2mrJbPMLcuZHxIJqplFgrG", "admin@email.com", "ADMIN");
			
			urepository.save(user1);
			urepository.save(user2);
		};
	}

}
