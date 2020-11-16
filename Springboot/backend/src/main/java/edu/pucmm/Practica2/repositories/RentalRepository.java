package edu.pucmm.Practica2.repositories;

import edu.pucmm.Practica2.entities.Client;
import edu.pucmm.Practica2.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAllByIsActive(Boolean isActive);

    List<Rental> findByClient(Client client);
    List<Rental> findByIsActiveOrderByDateOfRental(Boolean isActive);
}