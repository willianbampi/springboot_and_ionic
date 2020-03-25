package com.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.City;
import com.cursomc.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository rep;
	
	public List<City> findByFederativeUnity(Integer federativeUnityId){
		return rep.findCities(federativeUnityId);
	}
	
}