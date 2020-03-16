package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Category;
import com.cursomc.dto.CategoryDTO;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.services.exceptions.DataIntegrityException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository rep;
	
	public Category findById(Integer id) {
		Optional<Category> category = rep.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	@Transactional
	public Category insert(Category category) {
		category.setId(null);
		return rep.save(category);
	}
	
	public Category update(Category category) {
		Category newCategory = findById(category.getId());
		updateData(newCategory, category);
		return rep.save(newCategory);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que contém produtos.");
		}
	}
	
	public List<Category> findAll() {
		return rep.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
 	}
	
	public Category fromDto(CategoryDTO categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getName());
	}
	
	private void updateData(Category newCategory, Category category) {
		newCategory.setName(category.getName());
	}
	
}