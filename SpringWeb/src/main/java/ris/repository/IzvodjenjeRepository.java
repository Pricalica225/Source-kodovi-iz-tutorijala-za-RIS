package ris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Izvodjenje;

public interface IzvodjenjeRepository extends JpaRepository<Izvodjenje, Integer>{
	
	@Query("select i from Izvodjenje i where i.predstava.naziv like :nazPredstave")
	public List<Izvodjenje> findIzvodjenja(@Param("nazPredstave") String nazivPredstave);

}
