package edu.pucmm.Service;

import edu.pucmm.Repository.EventoRepository;
import edu.pucmm.Repository.GerenteRepository;
import edu.pucmm.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Event> findAllByGerente(String email) {
        return this.eventoRepository.findAllByGerente(this.gerenteRepository.findByEmail(email));
    }

    public Event create(Event event) {
        return this.eventoRepository.save(event);
    }

    public List<Event> notSended() {
        return this.eventoRepository.findAllByStartBeforeAndSend(LocalDateTime.now(),false);
    }
}
