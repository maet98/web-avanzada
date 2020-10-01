package edu.pucmm.Practica2;

import edu.pucmm.Practica2.entities.Family;
import edu.pucmm.Practica2.entities.SubFamily;
import edu.pucmm.Practica2.repositories.FamilyRepository;
import edu.pucmm.Practica2.repositories.SubFamilyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		Family n1 = new Family();
		n1.setName("Audio");
		FamilyRepository familyRepository = (FamilyRepository) applicationContext.getBean("familyRepository");
		var f1 = familyRepository.save(n1);
		SubFamily n2 = new SubFamily("Bocinas",f1);
		SubFamilyRepository subFamilyRepository = (SubFamilyRepository) applicationContext.getBean("subFamilyRepository");
		subFamilyRepository.save(n2);
	}

}
