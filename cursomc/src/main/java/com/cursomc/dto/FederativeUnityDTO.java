package com.cursomc.dto;

import java.io.Serializable;

import com.cursomc.domain.FederativeUnity;

public class FederativeUnityDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public FederativeUnityDTO() {
		
	}

	public FederativeUnityDTO(FederativeUnity federativeUnity) {
		this.id = federativeUnity.getId();
		this.name = federativeUnity.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}