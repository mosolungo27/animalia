package com.animalia.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.animalia.model.Ciudadano;
import com.animalia.repo.CiudadanoCustomRepo;
import com.animalia.repo.CiudadanoRepo;

@RestController
public class CiudadanoController {
	
	@Autowired
	private CiudadanoRepo ciudadanoRepo;
	
	@Autowired(required = true)
	private CiudadanoCustomRepo ciudadanoCustomRepo;
	
	@PostMapping("/guardar")
	public ResponseEntity<String> guardaCiudadano(@RequestBody Ciudadano ciudadano){
		try {
			ciudadanoRepo.save(ciudadano);
		} catch (Exception e) {
			return new ResponseEntity<String>("ALGO SUCEDIO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("CORRECTO!!", HttpStatus.OK);
	}
	
	@PostMapping("/guardarMultiple")
	public ResponseEntity<String> guardaCiudadanos(@RequestBody List<Ciudadano> ciudadanos){
		try {
			ciudadanos.stream().forEach(ciudadano ->{
				ciudadanoRepo.save(ciudadano);
			});		
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
	public  ResponseEntity<String> actualizarCiudadano(@RequestBody Ciudadano ciudadano) {
		try {
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
		} catch (Exception e) {
			return new ResponseEntity<String>("NO SE PUDO ACTULIZAR!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("SE ACTULIZO!!", HttpStatus.OK);
	}
	
	 @GetMapping("/busquedaMulti")
	    public ResponseEntity<List<Ciudadano>> getStudents( @RequestParam String nombre,	                                       
	                                        @RequestParam  String especie){
		 List<Ciudadano> ciudadanos = new ArrayList();
		 try {
			 ciudadanos = ciudadanoCustomRepo.consultarPorCriterios(nombre, especie);
		} catch (Exception e) {
			return new ResponseEntity<List<Ciudadano>>(ciudadanos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 	return new ResponseEntity<List<Ciudadano>>(ciudadanos, HttpStatus.OK); 
	    }
}
