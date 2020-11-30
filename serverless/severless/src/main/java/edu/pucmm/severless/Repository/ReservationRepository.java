package edu.pucmm.severless.Repository;

import edu.pucmm.severless.models.Reservation;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ReservationRepository extends CrudRepository<Reservation, String> {
}
