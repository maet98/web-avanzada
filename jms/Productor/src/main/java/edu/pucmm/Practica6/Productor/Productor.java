package edu.pucmm.Practica6.Productor;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Productor {
    private Gson gson;
    public Productor() {
        this.gson = new Gson();
    }

    public String generateMessage(Data data) {
        return gson.toJson(data);
    }

    public void sendMessage(String cola, String message) throws JMSException {

        String IP = System.getenv("JMS_IP");

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://" + IP + ":61616");

        //Crea un nuevo hilo cuando hacemos a conexión, que no se detiene cuando
        // aplicamos el metodo stop(), para eso tenemos que cerrar la JVM o
        // realizar un close().
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        System.out.println("Connection created");

        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.
        MessageProducer producer =  null;

        Queue queue = session.createQueue(cola);
        producer = session.createProducer(queue);


        // Creando un simple mensaje de texto y enviando.
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);

        System.out.println("Message was send");
        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();

    }
}
