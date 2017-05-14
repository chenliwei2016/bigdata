package win.chenliwei.simplespringmvc.model;

import java.io.Serializable;

public class Category implements Serializable,Comparable<Category> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3478984588080603011L;
	private int id;
	private String name;
	public Category(){}
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Category o) {
		// TODO Auto-generated method stub
		return o.getId() - id;
	}
}
