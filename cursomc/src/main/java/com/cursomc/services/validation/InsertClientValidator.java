package com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.domain.Client;
import com.cursomc.domain.enums.ClientType;
import com.cursomc.dto.NewClientDTO;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.resources.exception.FieldMessage;
import com.cursomc.services.validation.utils.BR;

public class InsertClientValidator implements ConstraintValidator<InsertClient, NewClientDTO> {
	
	@Autowired
	private ClientRepository rep;
	
	@Override
	public void initialize(InsertClient ann) {
	}

	@Override
	public boolean isValid(NewClientDTO newClientDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(newClientDTO.getType().equals(ClientType.PESSOAFISICA.getId()) && !BR.isValidCPF(newClientDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF inválido."));
		}
		
		if(newClientDTO.getType().equals(ClientType.PESSOAJURIDICA.getId()) && !BR.isValidCNPJ(newClientDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido."));
		}
		
		Client client = rep.findByEmail(newClientDTO.getEmail());
		
		if(client != null) {
			list.add(new FieldMessage("email", "Email já existente."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}