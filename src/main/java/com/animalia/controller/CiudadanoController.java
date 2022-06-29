package com.animalia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animalia.model.Ciudadano;
import com.animalia.repo.CiudadanoRepo;

@RestController
public class CiudadanoController {
	
	@Autowired
	private CiudadanoRepo ciudadanoRepo;
	
	@PostMapping("/guardar")
	public ResponseEntity<String> guardaCiudadano(@RequestBody Ciudadano ciudadano){
		try {
			ciudadanoRepo.save(ciudadano);
		} catch (Exception e) {
			return new ResponseEntity<String>("ALGO SUCEDIO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("CORRECTO!!", HttpStatus.OK);
	}

}
