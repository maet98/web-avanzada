package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.ReturnDTO;
import edu.pucmm.Practica2.entities.Order;
import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.repositories.OrderRepository;
import edu.pucmm.Practica2.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RentalService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private EquimentService equimentService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Rental createRental(Rental rental) throws Exception {
        if(equimentService.isPossibleRental(rental.getOrdersList())){
            var answer = rentalRepository.save(rental);
            for(Order order: rental.getOrdersList()){
                order.setRental(answer);
            }
            equimentService.rentalEquiment(rental.getOrdersList());
            orderRepository.saveAll(rental.getOrdersList());
            return answer;
        } else {
            throw new Exception("There are not equiment available");
        }
    }

    @Transactional
    public String returnRental(ReturnDTO returnDTO) {
        if(isGoodReturn(returnDTO)){
            orderService.returnOrders(returnDTO.getOrdersList());
            equimentService.returnEquiment(returnDTO.getOrdersList());
            if(receivedAll(returnDTO)){
                var rental = rentalRepository.findById(returnDTO.getId()).get();
                rental.setActive(false);
                rentalRepository.save(rental);
            }
            return "All good";
        }
        else {
            return "There is a problem";
        }
    }

    private boolean receivedAll(ReturnDTO rental) {
        for(Order order: rental.getOrdersList()){
            if(order.getQuantity() > 0){
                return false;
            }
        }
        return true;
    }

    public boolean isGoodReturn(Rental rental) {
        Optional<Rental> actual = rentalRepository.findById(rental.getId());
        if(actual.isPresent()){
            Rental value = actual.get();
            for(int i = 0;i < rental.getOrdersList().size();i++){
                if(rental.getOrdersList().get(i).getQuantity() > value.getOrdersList().get(i).getQuantity()){
                    return false;
                }
            }
        }
        return true;
    }

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
