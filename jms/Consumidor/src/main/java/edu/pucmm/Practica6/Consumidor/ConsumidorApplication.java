package edu.pucmm.Practica6.Consumidor;

import edu.pucmm.Practica6.Consumidor.Controllers.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.jms.JMSException;


@SpringBootApplication
public class ConsumidorApplication {
	public static void main(String[] args) throws JMSException {
		ApplicationContext applicationContext = SpringApplication.run(ConsumidorApplication.class, args);
		Consumer consumer = (Consumer) applicationContext.getBean("consumer");
		consumer.conectar();
	}
}
