
package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;

	
	@RequestMapping("/index")
	public String bookpage() {
		return null;
		
	}
	
	@RequestMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", brepository.findAll());
		
		return "booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		brepository.deleteById(id);
		return "redirect:../booklist";
	}
	
	@RequestMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addBook";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", brepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editBook";
	}
	
	@RequestMapping(value="/saveEdit", method = RequestMethod.POST)
	public String saveEdit(Book book) {
		brepository.save(book);
		return "redirect:booklist";
		
	}
	
	@RequestMapping(value="books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}
	
	@RequestMapping(value="books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long id) {
		return brepository.findById(id);
	}

}
