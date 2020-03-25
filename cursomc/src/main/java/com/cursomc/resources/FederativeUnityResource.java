package com.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.City;
import com.cursomc.domain.FederativeUnity;
import com.cursomc.dto.CityDTO;
import com.cursomc.dto.FederativeUnityDTO;
import com.cursomc.services.CityService;
import com.cursomc.services.FederativeUnityService;

@RestController
@RequestMapping(value = "/federativeunities")
public class FederativeUnityResource {
	
	@Autowired
	private FederativeUnityService federativeUnityService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FederativeUnityDTO>> findAll(){
		List<FederativeUnity> FederativeUnityList = federativeUnityService.findAll();
		List<FederativeUnityDTO> FederativeUnityListDTO = FederativeUnityList.stream().map(federativeUnity -> new FederativeUnityDTO(federativeUnity)).collect(Collectors.toList());
		return ResponseEntity.ok().body(FederativeUnityListDTO);
	}
	
	@RequestMapping(value = "/{federativeUnityId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer federativeUnityId){
		List<City> cityList = cityService.findByFederativeUnity(federativeUnityId);
		List<CityDTO> cityListDTO = cityList.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cityListDTO);
	}

}