package win.chenliwei.simplespringmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import win.chenliwei.simplespringmvc.model.Book;
import win.chenliwei.simplespringmvc.model.Category;

@Service
public class BookServiceImpl implements BookService {
	private ConcurrentSkipListSet<Category> categories = new ConcurrentSkipListSet<Category>();
	private ConcurrentSkipListSet<Book> books = new ConcurrentSkipListSet<Book>();
	private AtomicLong generator = new AtomicLong();
	
	public BookServiceImpl() {
		
		Category category1 = new Category(1,"Computer");
		Category category2 = new Category(2,"Novel");
		Category category3 = new Category(3,"Poem");
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		
		books.add(new Book(1L,"1111111111","Expert Oracle one on one",category1,"Tomas Kyte"));
		books.add(new Book(2L,"2222222222","Two Cites Tale",category2,"Charles Dickens"));
		books.add(new Book(3L,"3333333333","Stray Birds",category3,"Rabindranath Tagore"));
		generator.set(4);
	}

	@Override
	public List<Category> getAllCategories() {
		return new ArrayList<Category>(categories);
	}

	@Override
	public Category getCategory(int id) {
		for(Category cate : categories) {
			if(cate.getId() == id) return cate;
		}
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		return new ArrayList<Book>(books);
	}

	@Override
	public Book get(Long id) {
		for(Book book : books) {
			if(book.getId() == id) return book;
		}
		return null;
	}


	@Override
	public Book update(Book book) {
		
		Iterator<Book> it = books.iterator();
		while(it.hasNext()){
			Book bookOld = it.next();
			if(bookOld.getId() == book.getId()){
				it.remove();
				books.add(book);
				return book;
			}
		}

		return null;
	}

	@Override
	public Book add(Book book) {
		Long newId = generator.incrementAndGet();
		book.setId(newId);
		books.add(book);
		return book;
	}

	@Override
	public Book delete(Long id) {
		
		Iterator<Book> it = books.iterator();
		while(it.hasNext()){
		Book bookOld = it.next();
		if(bookOld.getId() == id){
			it.remove();
			return bookOld;
		}
	}

	return null;
	}

}
