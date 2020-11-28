package edu.pucmm.component;

import edu.pucmm.Repository.EventoRepository;
import edu.pucmm.Service.EmailSender;
import edu.pucmm.Service.EventoService;
import edu.pucmm.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendEmails {

    @Autowired
    private EmailSender sender;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @Value("${email}")
    private String emailSender;

    @Scheduled(fixedRate = 60000)
    public void send(){
        System.out.println("Send Email was call");
        for(Event event: eventoService.notSended()){
            try {
                sendEmail(event);
                event.setSend();
                eventoRepository.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendEmail(Event event) throws IOException {
        sender.sendEmail("no-reply@em9523.miguelestevez.xyz ",
                event.getGerente().getEmail(),
                event.getTitle(),
                "The is a event at: " + event.getStart().toString()
                );
        System.out.println("Email was sent to" + event.getGerente().getName());
    }
}
