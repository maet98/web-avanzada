package edu.pucmm.Practica2;

import edu.pucmm.Practica2.entities.Client;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.Role;
import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.repositories.ClientRepository;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.repositories.UserRepository;
import edu.pucmm.Practica2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

		Equiment n1 = new Equiment("martillo",20, (float) 30.0, null);
		EquimentRepository equimentRepository = (EquimentRepository) applicationContext.getBean("equimentRepository");
		ClientRepository clientRepository = (ClientRepository) applicationContext.getBean("clientRepository");
		equimentRepository.save(n1);
		Client client = new Client("adsf","miguel","estevez","a;dsflja;@;aljfd","");
		clientRepository.save(client);
	}

	@Override
	public void run(String... args) throws Exception {

		User admin = new User();
		admin.setEmail("admin");
		admin.setPassword("admin");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.signup(admin);

		User client = new User();
		client.setEmail("client");
		client.setPassword("client");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

		userService.signup(client);
	}
}
