package com.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Product;
import com.cursomc.dto.ProductDTO;
import com.cursomc.resources.utils.URL;
import com.cursomc.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@ApiOperation(value="Busca produto por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@ApiOperation(value="Retorna todos produtos com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String catogories,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		List<Integer> categoryIds = URL.decodeIntList(catogories);
		String decodedName = URL.decodeParam(name);
		Page<Product> pageProduct = service.search(decodedName, categoryIds, page, linesPerPage, direction, orderBy);
		Page<ProductDTO> pageProductDto = pageProduct.map(product -> new ProductDTO(product));
		return ResponseEntity.ok().body(pageProductDto);
	}

}