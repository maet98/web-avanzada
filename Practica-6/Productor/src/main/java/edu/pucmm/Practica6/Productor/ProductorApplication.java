package edu.pucmm.Practica6.Productor;

import javax.jms.JMSException;
import java.util.concurrent.TimeUnit;

public class ProductorApplication {

	public static void main(String[] args) throws InterruptedException, JMSException {
		Productor productor = new Productor();
		if(args.length == 1) {
			Integer id = Integer.valueOf(args[0]);
			while(true) {
				Data new_data = new Data(id);
				System.out.println(new_data);
				productor.sendMessage("Temp",productor.generateMessage(new_data));
				TimeUnit.SECONDS.sleep(1);
			}
		} else {
			System.out.println("Just One parameter [clientId]");
		}
	}
}
