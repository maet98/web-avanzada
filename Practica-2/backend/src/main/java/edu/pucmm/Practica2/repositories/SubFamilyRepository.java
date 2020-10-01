package edu.pucmm.Practica2.repositories;

import edu.pucmm.Practica2.entities.SubFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubFamilyRepository extends JpaRepository<SubFamily,Long> {
}
