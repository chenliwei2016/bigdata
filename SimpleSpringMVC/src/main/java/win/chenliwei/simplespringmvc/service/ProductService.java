package win.chenliwei.simplespringmvc.service;

import win.chenliwei.simplespringmvc.model.Product;

public interface ProductService {
	public Product add(Product product);
	public Product get(Long id);

}
