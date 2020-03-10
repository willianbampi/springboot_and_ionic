package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Client;
import com.cursomc.dto.ClientDTO;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.services.exceptions.DataIntegrityException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository rep;
	
	public Client findById(Integer id) {
		Optional<Client> client = rep.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client insert(Client client) {
		client.setId(null);
		return rep.save(client);
	}
	
	public Client update(Client client) {
		Client newClient = findById(client.getId());
		updateData(newClient, client);
		return rep.save(newClient);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente pois existem relacionamentos vinculados.");
		}
	}
	
	public List<Client> findAll() {
		return rep.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
 	}
	
	public Client fromDto(ClientDTO clientDto) {
		return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}

}