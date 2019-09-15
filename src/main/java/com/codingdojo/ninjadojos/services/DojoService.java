package com.codingdojo.ninjadojos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.ninjadojos.models.Dojo;
import com.codingdojo.ninjadojos.repositories.DojoRepository;

@Service
public class DojoService {
	private final DojoRepository dojoRepository;
	
	public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;
	}
	
	public List<Dojo> allDojos(){
		return (List<Dojo>) dojoRepository.findAll();
	}
	
	public List<Dojo> allDojosNullNinjas(){
		return dojoRepository.findByNinjaIsNull();
	}
	
	public Dojo addDojo(Dojo d) {
		return dojoRepository.save(d);
	}
	
	public Dojo findDojo(Long id) {
		Optional<Dojo> optionalDojo = dojoRepository.findById(id);
		if(optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
	
	
	
	
}
