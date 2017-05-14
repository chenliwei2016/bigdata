package win.chenliwei.simplespringmvc.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import win.chenliwei.simplespringmvc.model.Book;
import win.chenliwei.simplespringmvc.model.Category;
import win.chenliwei.simplespringmvc.service.BookService;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	
	private static final Log logger = LogFactory.getLog(BookController.class);
	
	@RequestMapping(value="/book_input")
	public String inputBook(Model model){
		logger.info("/book_input");
		List<Category> categories = bookService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("book", new Book());
		return "BookForm";
	}
	
	@RequestMapping(value="/book_add")
	public String addBook(Book book, Model model){
		logger.info("/book_add");
		Category cate = bookService.getCategory(book.getCategory().getId());
		book.setCategory(cate);
		if(bookService.get(book.getId()) == null){ 
			bookService.add(book);
			model.addAttribute("book",book);
			return "redirect:/book_list";
		} else {
			//already exists
			model.addAttribute("book",book);
			model.addAttribute("message","Book already exists");
			return "BookForm";
		}
	}
	
	@RequestMapping(value="/book_list")
	public String listBook(Model model){
		logger.info("/book_list");
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books",books);
		return "BookList";
	}
	

	@RequestMapping(value="/book_delete/{id}")
	public String deleteBook(@ModelAttribute Book book, @PathVariable Long id){
		logger.info("/book_delete");
		bookService.delete(id);
		return "redirect:/book_list";
	}
	
	@RequestMapping(value="/book_edit/{id}")
	public String updateBook( Model model,@PathVariable Long id){
		logger.info("/book_edit/" + id);
		Book book = bookService.get(id);
		List<Category> categories = bookService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("book", book);
		return "BookEditForm";
	}
	
	@RequestMapping(value="/book_save")
	public String saveBook(Book book){
		logger.info("/book_save");
		bookService.update(book);
		return "redirect:/book_list";
	}
}
