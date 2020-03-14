package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Category;
import com.cursomc.domain.Product;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.repositories.ProductRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository rep;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product findById(Integer id) {
		Optional<Product> order = rep.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return rep.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}

}