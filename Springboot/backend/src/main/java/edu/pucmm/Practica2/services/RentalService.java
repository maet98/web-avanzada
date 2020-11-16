package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.NotReturnedDTO;
import edu.pucmm.Practica2.DTO.OrderDTO;
import edu.pucmm.Practica2.DTO.RentalDTO;
import edu.pucmm.Practica2.entities.MyOrder;
import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.repositories.ClientRepository;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.repositories.OrderRepository;
import edu.pucmm.Practica2.repositories.RentalRepository;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
    private EquimentRepository equimentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Rental> getAll() {
        return (List<Rental>) ListMaker.makeCollection(this.rentalRepository.findAll());
    }

    public Rental getById(Long id) {
        return this.rentalRepository.findById(id).orElse(null);
    }

    public List<NotReturnedDTO> getNonReturned() {
        List<NotReturnedDTO> notReturnedDTOS = new ArrayList<>();
        for( var rental : this.rentalRepository.findByIsActiveOrderByDateOfRental(true)){

            var today = LocalDate.now();
            Long days = rental.getDateOfRental().until(today, ChronoUnit.DAYS);
            for(var orders: rental.getOrderList()){
                notReturnedDTOS.add(
                        new NotReturnedDTO(
                                orders.getEquiment().getId(),
                                orders.getQuantity(),
                                orders.getEquiment().getName(),
                                rental.getClient().getFirstName() + " " + rental.getClient().getLastName(),
                                days
                        )
                );
            }
        }
        return notReturnedDTOS;
    }

    @Transactional
    public Rental createRental(RentalDTO rental) throws Exception {
        if(equimentService.isPossibleRental(rental.getOrderList())){
            Rental new_rental = new Rental(rental.getDateOfRental(), clientRepository.findById(rental.getClientId()).get(), rental.getExpectedReturnDate(), true);
            List<MyOrder>myOrderList = new ArrayList<>();

            for(OrderDTO order: rental.getOrderList()){
                MyOrder order1 = new MyOrder();
                order1.setEquiment(this.equimentRepository.findById(order.getId()).get());
                order1.setQuantity(order.getQuantity());
                order1.setRental(new_rental);
                order1.setOriginalQuantity(order.getQuantity());
                new_rental.addOrder(orderRepository.save(order1));
            }
            equimentService.rentalEquiment(rental.getOrderList());
            return rentalRepository.save(new_rental);
        } else {
            return null;
        }
    }

    @Transactional
    public String returnRental(Rental returnDTO) {
        if(isGoodReturn(returnDTO)){
            orderService.returnOrders(returnDTO.getOrderList());
            equimentService.returnEquiment(returnDTO.getOrderList());
            var rental = rentalRepository.findById(returnDTO.getId()).get();
            if(receivedAll(rental)){
                rental.setActive(false);
            }
            rentalRepository.save(rental);
            return "All good";
        }
        else {
            return "There is a problem";
        }
    }

    public List<Rental> getAllActive() {
        return this.rentalRepository.findByIsActiveOrderByDateOfRental(true);
    }

    private boolean receivedAll(Rental rental) {
        for(MyOrder order: rental.getOrderList()){
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
            for(int i = 0;i < rental.getOrderList().size();i++){
                if(rental.getOrderList().get(i).getQuantity() > value.getOrderList().get(i).getQuantity()){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Rental> findByClient(String id) {
        return this.rentalRepository.findByClient(this.clientRepository.findById(id).get());
    }

    public Rental getRentalById(Long Id) {
        return rentalRepository.findById(Id).orElse(null);
    }

   private long getDifferenceDays(LocalDate d1, LocalDate d2) {
        return Duration.between(d2,d1).toDays();
    }
    public long calculateCost(Long rentalId, LocalDate received)  {
        Rental rental = getRentalById(rentalId);
        return getDifferenceDays(rental.getDateOfRental(), received);
    }

}
