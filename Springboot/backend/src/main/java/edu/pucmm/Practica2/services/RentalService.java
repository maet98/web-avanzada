package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.OrderDTO;
import edu.pucmm.Practica2.DTO.RentalDTO;
import edu.pucmm.Practica2.DTO.ReturnDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                new_rental.addOrder(orderRepository.save(order1));
            }
            equimentService.rentalEquiment(rental.getOrderList());
            return rentalRepository.save(new_rental);
        } else {
            return null;
        }
    }

    @Transactional
    public String returnRental(ReturnDTO returnDTO) {
        if(isGoodReturn(returnDTO)){
            orderService.returnOrders(returnDTO.getOrderList());
            equimentService.returnEquiment(returnDTO.getOrderList());
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
