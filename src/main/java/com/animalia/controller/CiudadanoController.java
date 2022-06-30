package com.animalia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Ciudadano> buscarCiudadano(@PathVariable("id") int id){
		Optional<Ciudadano> temp;
		Ciudadano ciudadano = new Ciudadano();
		try {
			 temp = ciudadanoRepo.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Ciudadano>(ciudadano, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ciudadano = temp.get();
		return new ResponseEntity<Ciudadano>(ciudadano, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminiarCiudadano(@PathVariable("id") int id){	
		try {
			ciudadanoRepo.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("NO SE PUDO ELIMINAR!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("SE ELIMINO!!", HttpStatus.OK);
	}
	
	@PutMapping("/actualizar")
	public String actualizarCiudadano(@RequestBody Ciudadano ciudadano) {
		Optional<Ciudadano> temp = ciudadanoRepo.findById(ciudadano.getId());
		Ciudadano ciuda=temp.get();
		ciuda.setNombre(ciudadano.getNombre());
		ciuda.setEspecie(ciudadano.getEspecie());
		ciuda.setDescripcion(ciudadano.getDescripcion());
		ciuda.setInd_mascota(ciudadano.isInd_mascota());
		ciuda.setPeso(ciudadano.getPeso());
		ciuda.setAltura(ciudadano.getAltura());
		ciuda.setUrl(ciudadano.getUrl());
		ciudadanoRepo.save(ciuda);
		return "SE ACTULIZO CORRECTAMENTE!!!";
	}
}
