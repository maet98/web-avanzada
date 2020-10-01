package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private EquimentService equimentService;

    /*
    public Rental createRental(Rental rental) throws Exception {
        if(equimentService.isPossibleRental(rental.getOrdersList())){
            var answer = rentalRepository.save(rental);
            return answer;
        } else {
            throw new Exception("There are not equiment available");
        }
    }
     */

    public Rental getRentalById(Long Id) {
        return rentalRepository.findById(Id).orElse(null);
    }

   private long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public long calculateCost(Long rentalId, Date received)  {
        Rental rental = getRentalById(rentalId);
        return getDifferenceDays(rental.getDateOfRental(), received);
    }

}
