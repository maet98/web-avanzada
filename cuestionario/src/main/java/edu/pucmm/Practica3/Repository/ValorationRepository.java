package edu.pucmm.Practica3.Repository;

import edu.pucmm.Practica3.DTO.GraphData;
import edu.pucmm.Practica3.Entities.Valoration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ValorationRepository extends CrudRepository<Valoration, Long> {
}
