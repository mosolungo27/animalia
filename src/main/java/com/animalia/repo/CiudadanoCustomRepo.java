package com.animalia.repo;

import java.util.List;

import com.animalia.model.Ciudadano;

public interface CiudadanoCustomRepo {
	
	List<Ciudadano> consultarPorCriterios(String nombre, String especie);
}
