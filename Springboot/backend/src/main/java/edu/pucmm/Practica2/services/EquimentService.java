package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.DTO.OrderDTO;
import edu.pucmm.Practica2.DTO.UsedEquiment;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.MyOrder;
import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.repositories.OrderRepository;
import edu.pucmm.Practica2.repositories.RentalRepository;
import edu.pucmm.Practica2.utils.ImageProcessor;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EquimentService {

    @Autowired
    private EquimentRepository equimentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RentalRepository rentalRepository;


    public HashMap<Long,Equiment> getEquiment() {
        var equimentsAvailable = equimentRepository.findAll();
        HashMap<Long, Equiment> map = new HashMap<>();
        for(var actual: equimentsAvailable) {
            map.put(actual.getId(),actual);
        }
        return map;
    }

    @Transactional
    public Boolean isPossibleRental(List<OrderDTO> orderList){
        var equimentAvailable = this.getEquiment();
        for(var actual: orderList) {
            if(equimentAvailable.containsKey(actual.getId())){
                var available = equimentAvailable.get(actual.getId());
                if(available.getQuantity() < actual.getQuantity()){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public List<Equiment> rentalEquiment(List<OrderDTO> orders){
        List<Equiment> actualizar = new ArrayList<>();
        for(OrderDTO order: orders){
            Equiment equiment = equimentRepository.findById(order.getId()).get();
            equiment.setQuantity(equiment.getQuantity() - order.getQuantity());
            actualizar.add(equiment);
        }
        return (List<Equiment>) ListMaker.makeCollection(equimentRepository.saveAll(actualizar));
    }

    @Transactional
    public List<Equiment> returnEquiment(List<MyOrder> orders) {
        List<Equiment> actualizar = new ArrayList<>();
        for(MyOrder order: orders){
            order.getEquiment().setQuantity(order.getEquiment().getQuantity() + order.getQuantity());
            actualizar.add(order.getEquiment());
        }
        return (List<Equiment>) ListMaker.makeCollection( equimentRepository.saveAll(actualizar));
    }

    public List<UsedEquiment> getNotReceivedEquiment() {
        List<Rental> rentals = this.rentalRepository.findAllByIsActive(true);
        HashMap<Long,UsedEquiment> ans = new HashMap<Long, UsedEquiment>();
        for(var rental: rentals) {
            for(var order: rental.getOrderList()){
                if(ans.containsKey(order.getEquiment().getId())){
                    ans.get(order.getEquiment().getId()).setQuantity(order.getQuantity());
                } else {
                    ans.put(order.getEquiment().getId(), new UsedEquiment(order.getQuantity(),order.getEquiment().getName(),order.getEquiment().getId()));
                }
            }
        }
        return (List<UsedEquiment>) ans.values();
    }

    public List<Equiment> getAll() {
        return (List<Equiment>) equimentRepository.findAll();
    }

    public Equiment createEquiment(EquimentForm equiment){
        String image = null;
        System.out.println(equiment.getImage());
        if(equiment.getImage() != null) {
            try {
                image = ImageProcessor.byteToString(equiment.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Equiment equiment1 = new Equiment(
                equiment.getName(),
                equiment.getQuantity(),
                equiment.getCost(),
                image
        );
        return equimentRepository.save(equiment1);
    }

    public Equiment updateEquiment(Long id, EquimentForm equiment) throws IOException {
        var old = this.equimentRepository.findById(id).get();
        old.setImage(ImageProcessor.byteToString(equiment.getImage()) );
        old.setQuantity(equiment.getQuantity());
        old.setCost(equiment.getCost());
        old.setName(equiment.getName());
        return this.equimentRepository.save(old);
    }
}
