package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Category;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository rep;
	
	public Category findById(Integer id) {
		Optional<Category> category = rep.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return rep.save(category);
	}

}