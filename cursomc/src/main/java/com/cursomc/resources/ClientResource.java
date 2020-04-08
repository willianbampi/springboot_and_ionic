package com.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.domain.Client;
import com.cursomc.dto.ClientDTO;
import com.cursomc.dto.NewClientDTO;
import com.cursomc.services.ClientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	ClientService service;
	
	@ApiOperation(value="Busca cliente por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer id) {
		Client client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@ApiOperation(value="Busca cliente por email")
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Client> findByEmail(@RequestParam(value = "email") String email) {
		Client client = service.findByEmail(email);
		return ResponseEntity.ok().body(client);
	}
	
	@ApiOperation(value="Insere cliente")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewClientDTO newClientDto){
		Client client = service.fromDto(newClientDto);
		client = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Atualiza cliente por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> update(@Valid @RequestBody ClientDTO clientDto, @PathVariable Integer id){
		Client client = service.fromDto(clientDto);
		client.setId(id);
		client = service.update(client);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Remove cliente por id")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir um clinte que possui pedidos"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Retorna todos clientes")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> listClient = service.findAll();
		List<ClientDTO> listClientDto = listClient.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listClientDto);
	}
	
	@ApiOperation(value="Retorna todos clientes com paginação")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Client> pageClient = service.findPage(page, linesPerPage, direction, orderBy);
		Page<ClientDTO> pageClientDto = pageClient.map(client -> new ClientDTO(client));
		return ResponseEntity.ok().body(pageClientDto);
	}
	
	@ApiOperation(value="Salva foto de profile")
	@RequestMapping(value = "/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile multipartFile){
		URI uri = service.uploadProfilePicture(multipartFile);
		return ResponseEntity.created(uri).build();
	}

}