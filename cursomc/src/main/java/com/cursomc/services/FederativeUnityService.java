package com.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.FederativeUnity;
import com.cursomc.repositories.FederativeUnityRepository;

@Service
public class FederativeUnityService {
	
	@Autowired
	private FederativeUnityRepository rep;
	
	public List<FederativeUnity> findAll(){
		return rep.findAllByOrderByName();
	}
	
}