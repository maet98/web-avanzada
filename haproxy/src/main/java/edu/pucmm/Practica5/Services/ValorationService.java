package edu.pucmm.Practica5.Services;

import edu.pucmm.Practica5.DTO.Average;
import edu.pucmm.Practica5.Repository.QuestionRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class ValorationService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public SessionFactory getCurrentSessionFromJPA() {
        var session = entityManager.unwrap(org.hibernate.Session.class);
        var factory = session.getSessionFactory();
        return factory;
    }

    @Transactional
    public List<HashMap<String, Integer>> getData() {
        var session = this.getCurrentSessionFromJPA().openSession();
        session.beginTransaction();
        List<?> result = session.createQuery( "select valoration.question.value , valoration.value, count(valoration.value)  from Valoration valoration where valoration.question.comment=false group by valoration.question.value , valoration.value ").list();
        List<HashMap<String,Integer>> ans = new ArrayList<>();
        var questions = questionRepository.findQuestionByComment(false);
        for(int i = 0;i < 5;i++) {
            HashMap<String,Integer> init = new HashMap<>();
            for(var question: questions) {
                init.put(question.getValue(),0);
            }
            ans.add(init);
        }

        for(int i = 0;i < result.size();i++) {
            Object[] row = (Object[]) result.get(i);
            Long raw = (Long) row[2];
            Integer value = raw.intValue();
            ans.get((Integer) row[1] -1).put((String) row[0], value );
        }
        return ans;
    }

    @Transactional
    public ArrayList<Average> getAverage() {
        var session = this.getCurrentSessionFromJPA().openSession();
        session.beginTransaction();
        List<?> result = session.createQuery("select valoration.question.value, avg(valoration.value) from Valoration valoration where valoration.question.comment=false group by valoration.question.value").list();

        ArrayList<Average> arr = new ArrayList<>();
        for(int i=0; i<result.size(); i++) {
            Object[] row = (Object[]) result.get(i);
            Average average = new Average();
            average.setQuestion((String) row[0]);
            average.setAverage((Double) row[1]);
            arr.add(average);
        }
        session.close();
        return arr;
    }
}
