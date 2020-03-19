package com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.cursomc.domain.Client;
import com.cursomc.dto.ClientDTO;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.resources.exception.FieldMessage;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {
	
	@Autowired
	private ClientRepository rep;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(UpdateClient ann) {
	}

	@Override
	public boolean isValid(ClientDTO clientDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client client = rep.findByEmail(clientDTO.getEmail());
		
		if(client != null && !clientDTO.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email already exist."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}