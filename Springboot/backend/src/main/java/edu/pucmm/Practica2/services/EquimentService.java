package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.DTO.OrderDTO;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.MyOrder;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.repositories.OrderRepository;
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

    public List<Equiment> returnEquiment(List<MyOrder> orders) {
        List<Equiment> actualizar = new ArrayList<>();
        for(MyOrder order: orders){
            order.getEquiment().setQuantity(order.getEquiment().getQuantity() + order.getQuantity());
            actualizar.add(order.getEquiment());
        }
        return (List<Equiment>) ListMaker.makeCollection( equimentRepository.saveAll(actualizar));
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
}
