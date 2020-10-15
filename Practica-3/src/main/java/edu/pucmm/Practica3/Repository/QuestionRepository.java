package edu.pucmm.Practica3.Repository;

import edu.pucmm.Practica3.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {
}
