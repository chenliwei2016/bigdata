/**
 * 
 */
package win.chenliwei.simplespringmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import win.chenliwei.simplespringmvc.model.Product;
import win.chenliwei.simplespringmvc.service.ProductService;
import win.chenliwei.simplespringmvc.view.ProductForm;

@Controller
public class ProductController {
	@Autowired private ProductService ps; // Injected by the spring with the single instance
	private static final Log logger = LogFactory.getLog(ProductController.class);
	@RequestMapping(value="/product_input")
	public String inputProduct(){
		logger.info("product_input is called");
		return "ProductForm";
	}
	
	@RequestMapping(value="/product_save")
	//public String saveProduct(ProductForm productForm, Model model){
	public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes){
		logger.info("produt_save is called");
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		product.setPrice(Float.parseFloat(productForm.getPrice()));
		
		Product savedProduct = ps.add(product);
		
		redirectAttributes.addFlashAttribute("message", "The product have been added successfully");
		return "redirect:/product_view/" + savedProduct.getId();
	}
	
	@RequestMapping(value="/product_view/{id}")
	public String viewProduct(@PathVariable Long id, Model model){
		Product product = ps.get(id);
		model.addAttribute("product", product);
		return "ProductDetails";
	}

}
