package edu.pucmm.Practica5.Services;

import edu.pucmm.Practica5.Entities.Question;
import edu.pucmm.Practica5.Entities.Questionary;
import edu.pucmm.Practica5.Entities.Valoration;
import edu.pucmm.Practica5.Repository.QuestionRepository;
import edu.pucmm.Practica5.Repository.QuestionaryRepository;
import edu.pucmm.Practica5.Repository.UserRepository;
import edu.pucmm.Practica5.Repository.ValorationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private QuestionaryRepository questionaryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ValorationRepository valorationRepository;

    @Autowired
    private UserRepository userRepository;

    private HashMap<Long,Question> getQuestions() {
        HashMap<Long,Question> map = new HashMap<>();
        for(Question question: questionRepository.findAll()){
            map.put(question.getId(), question);
        }
        return map;
    }

    @Transactional
    public Questionary postQuestionary(List<Valoration> valorations, String email) {
        Questionary questionary = new Questionary(userRepository.findByEmail(email));
        questionary = questionaryRepository.save(questionary);
        int id = 0;
        for(Question question : questionRepository.findAll()){
            valorations.get(id).setQuestion(question);
            valorations.get(id).setQuestionary(questionary);
            id++;
        }
        valorations = (List<Valoration>) valorationRepository.saveAll(valorations);
        questionary.setValorations(valorations);
        return questionaryRepository.save(questionary);
    }

}
