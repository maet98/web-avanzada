package edu.pucmm.Practica6.Consumidor.Controllers;

import edu.pucmm.Practica6.Consumidor.DTO.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {


    @GetMapping
    public String showGraph(Model model) {
        return "Home";
    }

    @GetMapping("/greetings")
    public String greetings(Model model) {
        return "Greetings";
    }


    @MessageMapping("/practica6")
    @SendTo("/topic/data")
    public Data sendData( Data data){
        System.out.println("Message is send: " + data.toString());
        return data;
    }

}
