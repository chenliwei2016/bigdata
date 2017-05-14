package win.chenliwei.simplespringmvc.model;

import java.io.Serializable;

public class Book implements Serializable,Comparable<Book> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6867886682452623367L;
	private Long id;
	private String isbn;
	private String title;
	private Category category;
	private String author;
	public Book() {
	}
	public Book(Long id, String isbn, String title, Category category, String author) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return (int)(o.getId()- id);
	}
	

}
