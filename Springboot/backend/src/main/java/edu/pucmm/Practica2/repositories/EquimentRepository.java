package edu.pucmm.Practica2.repositories;

import edu.pucmm.Practica2.entities.Equiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquimentRepository extends CrudRepository<Equiment,Long> {
}
