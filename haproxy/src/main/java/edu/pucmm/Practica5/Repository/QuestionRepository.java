package edu.pucmm.Practica5.Repository;

import edu.pucmm.Practica5.Entities.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    List<Question> findQuestionByComment(boolean comment);
}
