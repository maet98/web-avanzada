package edu.pucmm.severless.Controller;

import edu.pucmm.severless.Repository.ReservationRepository;
import edu.pucmm.severless.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public Iterable<Reservation> getAll() {
        return this.reservationRepository.findAll();
    }

    @PostMapping
    public Reservation insert(@RequestBody Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }

}
