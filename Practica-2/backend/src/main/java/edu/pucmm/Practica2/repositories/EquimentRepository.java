package edu.pucmm.Practica2.repositories;

import edu.pucmm.Practica2.entities.Equiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquimentRepository extends JpaRepository<Equiment,Long> {
}
