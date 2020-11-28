package edu.pucmm.Repository;

import edu.pucmm.model.Event;
import edu.pucmm.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Event, Long> {
    public List<Event> findAllByGerente(Gerente gerente);
    public List<Event> findAllByStartBeforeAndSend(LocalDateTime localDateTime, Boolean send);
}
