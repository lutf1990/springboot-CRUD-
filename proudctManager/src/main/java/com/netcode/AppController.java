package com.netcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
//@RestController
@CrossOrigin
@Controller
public class AppController {

	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product>listProducts = service.listAll();
		model.addAttribute("listProducts",listProducts);
		return "index";
	}
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("nProduct", product);
		return "new_product";
	}
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("nProduct")Product nProduct) {
		service.save(nProduct);
		return "redirect:/";
		
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editProduct(@PathVariable(name = "id")long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product",product);
		return mav;
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id")long id) {
		System.out.println("@@@@ inside Delete API");
	    service.delete(id);
		return "redirect:/";
		
	}
}
