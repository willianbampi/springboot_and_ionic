package com.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.FederativeUnity;

@Repository
public interface FederativeUnityRepository extends JpaRepository<FederativeUnity, Integer> {
	
	@Transactional(readOnly = true)
	public List<FederativeUnity> findAllByOrderByName();

}