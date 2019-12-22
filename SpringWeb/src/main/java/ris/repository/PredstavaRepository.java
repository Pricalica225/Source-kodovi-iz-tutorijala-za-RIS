package ris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Predstava;
import model.Reziser;

public interface PredstavaRepository extends JpaRepository<Predstava, Integer>{
	
	public List<Predstava> findByReziser(Reziser r);

}
