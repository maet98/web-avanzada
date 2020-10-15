package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.Order;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.utils.ImageProcessor;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EquimentService {

    @Autowired
    private EquimentRepository equimentRepository;


    public HashMap<Long,Equiment> getEquiment() {
        var equimentsAvailable = equimentRepository.findAll();
        HashMap<Long, Equiment> map = new HashMap<>();
        for(var actual: equimentsAvailable) {
            map.put(actual.getId(),actual);
        }
        return map;
    }

    public Boolean isPossibleRental(List<Order> orderList){
        var equimentAvailable = this.getEquiment();
        for(var actual: orderList) {
            if(equimentAvailable.containsKey(actual.getEquiment().getId())){
                var available = equimentAvailable.get(actual.getEquiment().getId());
                if(available.getQuantity() < actual.getQuantity()){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Equiment> rentalEquiment(List<Order> orders){
        List<Equiment> actualizar = new ArrayList<>();
        for(Order order: orders){
            order.getEquiment().setQuantity(order.getEquiment().getQuantity() + order.getQuantity());
            actualizar.add(order.getEquiment());
        }
        return (List<Equiment>) ListMaker.makeCollection(equimentRepository.saveAll(actualizar));
    }

    public List<Equiment> returnEquiment(List<Order> orders) {
        List<Equiment> actualizar = new ArrayList<>();
        for(Order order: orders){
            order.getEquiment().setQuantity(order.getEquiment().getQuantity() - order.getQuantity());
            actualizar.add(order.getEquiment());
        }
        return (List<Equiment>) ListMaker.makeCollection( equimentRepository.saveAll(actualizar));
    }

    public List<Equiment> getAll() {
        return (List<Equiment>) equimentRepository.findAll();
    }

    public Equiment createEquiment(Equiment equiment, MultipartFile image) throws IOException {
        equiment.setImage(ImageProcessor.byteToString(image));
        return equimentRepository.save(equiment);
    }
}
