package com.animalia.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animalia.model.Ciudadano;

@Service
public class CiudadanoCustomRepoImpl implements CiudadanoCustomRepo{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Ciudadano> consultarPorCriterios(String nombre, String especie) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ciudadano> criteriaQuery = builder.createQuery(Ciudadano.class);
		Root<Ciudadano> ciudadano = criteriaQuery.from(Ciudadano.class);
		
		Predicate nombrePredicate = builder.like(ciudadano.get("nombre"),"%" + nombre + "%");
		Predicate especiePredicate = builder.like(ciudadano.get("especie"),"%" + especie + "%");
		
		criteriaQuery.where(nombrePredicate, especiePredicate);
		TypedQuery<Ciudadano> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
	}
	
	
	

}
