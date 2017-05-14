package win.chenliwei.simplespringmvc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import win.chenliwei.simplespringmvc.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private Map<Long,Product> products = new HashMap<Long,Product>();
	private AtomicLong generator = new AtomicLong();
	public ProductServiceImpl() {
		//add a product into it no matter what is it
		Product product = new Product();
		product.setName("WestData Desktop Hard Disk");
		product.setDescription("Super large storage to save your yellow movies");
		product.setPrice(412);
	}
	@Override
	public Product add(Product product) {
		product.setId(generator.incrementAndGet());
		products.put(product.getId(), product);
		return product;
	}
	@Override
	public Product get(Long id){
		return products.get(id);
	}
	
}
