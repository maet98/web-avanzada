package edu.pucmm.Practica6.Consumidor.Controllers;

import com.google.gson.Gson;
import edu.pucmm.Practica6.Consumidor.DTO.Data;
import edu.pucmm.Practica6.Consumidor.DTO.GraphData;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class Consumer {

    @Autowired
    private MainController mainController;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Value("${JMS_IP}")
    private String IP;

    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Queue queue;
    MessageConsumer consumer;
    String cola = "Temp";
    Gson gson = new Gson();

    public void conectar() throws JMSException {
        System.out.println("Conectando Consumidor");
        factory = new ActiveMQConnectionFactory("admin", "admin", "failover:tcp://"+ IP +":61616");

        connection = factory.createConnection();

        connection.start();

        session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);

        queue = session.createQueue(cola);
        consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            try{
                TextMessage textMessage = (TextMessage) message;
                Data data = gson.fromJson(textMessage.getText(), Data.class);
                messagingTemplate.convertAndSend("/topic/data",data);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
