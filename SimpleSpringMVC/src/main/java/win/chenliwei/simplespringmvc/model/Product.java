package win.chenliwei.simplespringmvc.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -62804463289279934L;
	private String name;
	private String description;
	private float price;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
