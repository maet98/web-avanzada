package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.Order;
import edu.pucmm.Practica2.repositories.OrderRepository;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public HashMap<Long, Order> getOrder() {
        var Orders = orderRepository.findAll();
        HashMap<Long, Order> map = new HashMap<>();
        for(var actual: Orders) {
            map.put(actual.getId(),actual);
        }
        return map;
    }

    public List<Order> returnOrders(List<Order> orders){
        var ordersAvailable = getOrder();
        List<Order> actualizar = new ArrayList<>();
        for(Order order: orders){
            if(ordersAvailable.containsKey(order.getId())){
                var actual = ordersAvailable.get(order.getId());
                actual.setQuantity(actual.getQuantity() - order.getQuantity());
                actualizar.add(actual);
            }
        }
        return (List<Order>) ListMaker.makeCollection(orderRepository.saveAll(actualizar));
    }
}
