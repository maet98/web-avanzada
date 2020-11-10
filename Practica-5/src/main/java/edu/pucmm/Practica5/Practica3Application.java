package edu.pucmm.Practica5;

import edu.pucmm.Practica5.Entities.Question;
import edu.pucmm.Practica5.Repository.QuestionRepository;
import edu.pucmm.Practica5.Services.SeguridadServices;
import edu.pucmm.Practica5.Services.ValorationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica3Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext =  SpringApplication.run(Practica3Application.class, args);

		SeguridadServices seguridadServices = (SeguridadServices) applicationContext.getBean("seguridadServices");
		seguridadServices.crearUsuarioAdmin();

		ValorationService valorationService = (ValorationService) applicationContext.getBean("valorationService");
		valorationService.getData();
		valorationService.getAverage();


		QuestionRepository questionRepository = (QuestionRepository) applicationContext.getBean("questionRepository");
		if(questionRepository.count() == 0){
			Question q1 = new Question("¿ Las charlas donde usted participó cumplieron con sus expectativas?.", false);
			Question q2 = new Question("¿Los expositores mostraron tener dominio del tema?.", false);
			Question q3 = new Question("¿Las instalaciones del evento fueron confortables para usted?.", false);
			Question q4 = new Question("¿ Las charlas donde usted participó cumplieron con sus expectativas?.", true);
			questionRepository.save(q1);
			questionRepository.save(q2);
			questionRepository.save(q3);
			questionRepository.save(q4);
		}
	}

}
