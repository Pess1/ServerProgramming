package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepoTest {
		
		@Autowired
		private BookRepository brepository;
		
		@Autowired
		private CategoryRepository crepository;
		
		@Autowired
		private UserRepository urepository;
		
		@Test
		public void findByTitleShouldReturnBook() {
			List<Book> books = brepository.findByTitle("Tuntematon Sotilas");
			assertThat(books).hasSize(1);
			assertThat(books.get(0).getAuthor()).isEqualTo("Väinö Linna");
		}
		
		@Test
		public void findByNameShouldReturnCategory() {
			List<Category> categories = crepository.findByName("War");
			assertThat(categories).hasSize(1);
			assertThat(categories.get(0).getName()).isEqualTo("War");
		}
		
		@Test
		public void findByUsernameShouldReturnUser() {
			User user = urepository.findByUsername("user");
			List<User> users = new ArrayList<>();
			users.add(user);
			assertThat(users).hasSize(1);
			assertThat(users.get(0).getUsername()).isEqualTo("user");
		}
		
		@Test
		public void createNewBookTest() {
			Book book = new Book("Wakanda Forever Bitch", "Black dude", 2020, "Lmao", 20, crepository.findByName("War").get(0));
			brepository.save(book);
			assertThat(book.getId()).isNotNull();
		}
		
		@Test
		public void createNewCategoryTest() {
			Category category = new Category("Yeet");
			crepository.save(category);
			assertThat(category.getCategoryId()).isNotNull();
		}
		
		@Test
		public void createNewUserTest() {
			User user = new User("yeeter", "$2y$12$90gXnhdrGXYVzMa8m5S3DuwRk/jMy.ceVwWDiZEBPN0C8Bpl5iTry", "yeeter@email.com", "USER");
			urepository.save(user);
			assertThat(user.getId()).isNotNull();
		}
		
		@Test
		public void deleteBookTest() {
			List<Book> books = brepository.findByTitle("Tuntematon Sotilas");
			brepository.deleteById(books.get(0).getId());
			List<Book> book = brepository.findByTitle("Tuntematon Sotilas");
			assertThat(book).hasSize(0);
		}
		
		@Test
		public void deleteCategoryTest() {
			List<Category> categories = crepository.findByName("War");
			crepository.deleteById(categories.get(0).getCategoryId());
			List<Category> category = crepository.findByName("War");
			assertThat(category).hasSize(0);
		}
		
		@Test
		public void deleteUserTest() {
			User user = urepository.findByUsername("user");
			urepository.deleteById(user.getId());
			List<User> users = new ArrayList<>();
			users.add(urepository.findByUsername("user"));
			assertThat(users.get(0)).isNull();
		}

}

