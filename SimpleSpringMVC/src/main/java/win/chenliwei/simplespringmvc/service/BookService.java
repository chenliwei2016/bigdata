package win.chenliwei.simplespringmvc.service;

import java.util.List;

import win.chenliwei.simplespringmvc.model.Book;
import win.chenliwei.simplespringmvc.model.Category;

public interface BookService {
	List<Category> getAllCategories();
	Category getCategory(int id);
	List<Book> getAllBooks();
	Book get(Long id);
	Book update(Book book);
	Book add(Book book);
	Book delete(Long id);
}
