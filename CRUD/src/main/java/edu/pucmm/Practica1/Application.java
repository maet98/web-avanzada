package edu.pucmm.Practica1;

import edu.pucmm.Practica1.Entity.Student;
import edu.pucmm.Practica1.Repository.StudentRepository;
import edu.pucmm.Practica1.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		Student n1 = new Student();
		n1.setMatricula(20170200);
		n1.setApellido("Estvez");
		n1.setNombre("Miguel");
		n1.setTelefono("809-829-2798");
		StudentRepository studentRepository = (StudentRepository) applicationContext.getBean("studentRepository");
		studentRepository.save(n1);
	}

}
